-- benefice par mois
select coalesce(sum(commission), 0) commission from annonce_vendu av join annonce a on av.id_annonce = a.id where extract(month from date_vente) = 1 and extract(year from date_vente) = 2019;

-- date vendu vs date annonce
select avg(a.date_creation - av.date_vente)
from annonce_vendu av
join annonce a 
on av.id_annonce = a.id
where extract(month from date_vente) = 1 and extract(year from date_vente) = 2019

-- nb annonce par mois
select count(*) from annonce 
where extract(month from date_vente) = 1 and extract(year from date_vente) = 2019


--  nb vendu par mois
select count(*) from annonce_vendu 
where extract(month from date_vente) = 1 and extract(year from date_vente) = 2019

-- vente par mois
create view v_mois as select generate_series(1,12) mois;

create view v_vente as select a.*, av.date_vente 
from annonce_vendu av 
join annonce a on av.id_annonce = a.id;

select m.mois, coalesce(sum(v.commission), 0) commission
from v_mois m
left outer join 
    (select * 
    from v_vente 
    where extract(month from date_vente) = 2019 and extract(year from date_vente) = 2019) v 
on m.mois = extract(month from v.date_vente)
group by m.mois
order by m.mois;

create view v_modele as (
    select 
    modele.id as id_modele, categorie.id as id_categorie, marque.id as id_marque , annee_sortie
    from modele
    join categorie
        on categorie.id = modele.id_categorie
    join marque 
        on marque.id = modele.id_marque
);

create or replace view v_annonce_non_valide as
(
    select 
    *
    from annonce 
    where status = 0
);

create view v_voiture as (
    select 
    voiture.id as id_voiture, modele.id_modele,  id_categorie, id_marque, annee_sortie
    from voiture    
    join v_modele as modele 
    on modele.id_modele = voiture.id_modele
);

create or replace view v_annonce as (
    select 
    id as id_annonce, id_modele,id_categorie, id_marque, description, prix, annee_sortie
    from annonce
    join v_voiture as voiture
        on voiture.id_voiture = annonce.id_voiture 
);

select 
avg(annonce.prix) as prix
from annonce
join voiture
on voiture.id = annonce.id_voiture
where voiture.id_modele = 1 and voiture.id_energie=4 and voiture.id_boite_vitesse=1 and etat <= 5+2 or etat >=5-2 