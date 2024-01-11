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