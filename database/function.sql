select v.id_marque, sum(commission) montant
from v_annonce_vendu a
join v_voiture_marque v on a.id_voiture = v.id_voiture
where extract(month from a.date_vente) = 1 and extract(year from a.date_vente) = 2019
group by id_marque;

create function f_benefice_par_marque(mois INTEGER, annee INTEGER) RETURNS TABLE (id_marque INTEGER, nom_marque varchar, montant numeric) as 
$$  
    select id_marque, nom_marque, sum(montant) from 
    (
        (select v.id_marque, v.nom_marque, sum(commission) montant
            from v_vente a
            join v_voiture_marque v on a.id_voiture = v.id_voiture
            where extract(month from a.date_vente) = $1 and extract(year from a.date_vente) = $2
            group by id_marque, nom_marque) 
            union 
                select id id_marque, nom nom_marque, 0 montant from marque 
    ) vendu
    group by vendu.id_marque, nom_marque
$$
    LANGUAGE SQL;

CREATE OR REPLACE FUNCTION topSellers(dateYYYYMM character varying, limit_value integer)
RETURNS TABLE (
    id integer,
    nom character varying,
    prenom character varying,
    valide bigint,
    vendu bigint,
    commission numeric,
    pourcentage numeric
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        utilisateur.id,
        utilisateur.nom,
        utilisateur.prenom, 
        COALESCE(COUNT(v_annonce_valide.id), 0) AS valide,
        COALESCE(COUNT(v_annonce_vendu.id), 0) AS vendu,
        COALESCE(SUM(v_annonce_valide.commission), 0) AS commission,
        CASE
            WHEN COUNT(v_annonce_valide.id) = 0 THEN 0.0
            ELSE (COUNT(v_annonce_vendu.id) / COUNT(v_annonce_valide.id)) * 100
        END AS pourcentage
    FROM
        utilisateur
    LEFT JOIN v_annonce_valide ON v_annonce_valide.id_utilisateur = utilisateur.id AND TO_CHAR(v_annonce_valide.date_maj::date, 'YYYYMM') <= dateYYYYMM
    LEFT JOIN v_annonce_vendu ON v_annonce_vendu.id_utilisateur = utilisateur.id AND TO_CHAR(v_annonce_vendu.date_maj::date, 'YYYYMM') <= dateYYYYMM
    GROUP BY
        utilisateur.id,
        utilisateur.nom
    ORDER BY
        valide desc
    LIMIT
        limit_value;

    RETURN;
END;
$$ LANGUAGE plpgsql;
select * from  topSellers('202401',2);


CREATE OR REPLACE FUNCTION inscription_par_mois(annee integer)
RETURNS TABLE (
    nb_inscription bigint,
    mois integer
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        COALESCE(COUNT(utilisateur.id), 0) AS nb_inscription,
        v_mois.mois
    FROM
        v_mois
    LEFT JOIN utilisateur ON
        v_mois.mois = EXTRACT(MONTH FROM date_inscription)
        AND EXTRACT(YEAR FROM date_inscription) = annee
    GROUP BY
        v_mois.mois
    ORDER BY
        v_mois.mois;

    RETURN;
END;
$$ LANGUAGE plpgsql;

-- 2024-01-18 fix/stats-general

create function f_benefice_par_mois(mois int, annee int) returns table (mois int, commission numeric) as $$

  select m.mois, coalesce(sum(v.commission), 0) commission  
from v_mois m  
left outer join   
(select *   
  from v_annonce_vendu   
  where extract(month from date_maj) = mois and extract(year from date_maj) = annee) v   
on m.mois = extract(month from v.date_maj)  
group by m.mois  
order by m.mois
$$ language sql;

-- 26/01/2024
-- TODO: REMPLACE FONCTION
create or replace function f_benefice_par_marque(mois INTEGER, annee INTEGER) RETURNS TABLE (id_marque INTEGER, nom_marque varchar, logo varchar, montant numeric) as 
$$  
    select id_marque, nom_marque, logo, sum(montant) from 
    (
        (select a.id_marque, a.nom_marque, logo, sum(commission) montant
            from v_annonce_gen_vendu a
            where extract(month from a.date_maj) = $1 and extract(year from a.date_maj) = $2
            group by id_marque, nom_marque, logo) 
            union 
                select id id_marque, nom nom_marque, logo, 0 montant from marque 
    ) vendu
    group by vendu.id_marque, nom_marque, logo
$$
    LANGUAGE SQL;

-- TODO replace this function
-- 26/01/2024
create or replace function f_benefice_par_mois(mois int, annee int) returns table (mois int, commission numeric) as $$

  select m.mois, coalesce(sum(v.commission), 0) commission  
from v_mois m  
left outer join   
(select *   
  from v_annonce_gen_vendu   
  where extract(month from date_maj) = mois and extract(year from date_maj) = annee) v   
on m.mois = extract(month from v.date_maj)  
group by m.mois  
order by m.mois
$$ language sql;

create or replace function f_nbr_valide(mois character varying) returns table (id_utilisateur int, valide bigint) as $$
BEGIN
    RETURN QUERY
    select v.id_utilisateur, count(*) valide
    from v_annonce_valide v
    where TO_CHAR(v.date_maj::date, 'YYYYMM') = mois
    group by v.id_utilisateur;
    RETURN;
END;
$$ LANGUAGE plpgsql;

create or replace function f_nbr_vendu(mois character varying) returns table (id_utilisateur int, vendu bigint, commission numeric) as $$
BEGIN
    RETURN QUERY
    select v.id_utilisateur, count(*) vendu, sum(v.commission) commission
    from v_annonce_vendu v
    where TO_CHAR(v.date_maj::date, 'YYYYMM') = mois
    group by v.id_utilisateur;
    RETURN;
END;
$$ LANGUAGE plpgsql;

-- replace fonction 
CREATE OR REPLACE FUNCTION f_topSellers(dateYYYYMM character varying, limit_value integer)
RETURNS TABLE (
    id integer,
    nom character varying,
    prenom character varying,
    valide bigint,
    vendu bigint,
    commission numeric,
    pourcentage numeric
) AS $$

BEGIN
    RETURN QUERY
    select u.id id, u.nom, u.prenom, 
    COALESCE(valide.valide, 0) valide,
    COALESCE(vendu.vendu, 0) vendu, COALESCE(vendu.commission, 0) commission,
    CASE
        when vendu.vendu is null then 0.0
        when valide.valide is null then 0.0
        ELSE  round((vendu.vendu::numeric / valide.valide::numeric) * 100, 2)
        END AS pourcentage
    from
    	utilisateur u 
    	left join f_nbr_vendu(dateYYYYMM) vendu on vendu.id_utilisateur = u.id 
    	left join f_nbr_valide(dateYYYYMM) valide on valide.id_utilisateur = u.id
    order by pourcentage desc, vendu desc, valide desc
    limit limit_value;
    RETURN;
END;
$$ LANGUAGE plpgsql;