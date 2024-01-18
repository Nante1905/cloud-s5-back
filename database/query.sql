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

-- TODO: fix stat general : le annonce_vendu solona v_annonce_vendu

insert into historique_annonce values 
(default, '2024-01-01', 5,1),
(default, '2023-12-22', 5,2),
(default, '2023-12-23', 5,10),
(default, '2023-12-15', 5,12),
(default, '2023-12-14', 5,13);

insert into historique_annonce values 
(default, '2024-01-01', 10,1);

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


-- TODO: manala id_acheteur