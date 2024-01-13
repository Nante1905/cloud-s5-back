-- vue avec id_voiture, id_marque
create view v_voiture_marque as
select v.id id_voiture, ma.id id_marque, ma.nom as nom_marque 
from voiture v
join modele m on v.id_modele = m.id
join marque ma on m.id_marque = ma.id;

-- les annonces vendues
create view v_annonce_vendu as
select a.*, av.date_vente, av.id_acheteur 
from annonce a
join annonce_vendu av
on av.id_annonce = a.id;

-- vente par mois
create view v_mois as select generate_series(1,12) mois;

-- UPDATE MIALISOA: Manampy id_acheter
create view v_vente as select a.*, av.date_vente, av.id_acheteur 
from annonce_vendu av 
join annonce a on av.id_annonce = a.id;

-- derniere commission 12-01-2024 17:53
create view v_last_commission as(
    select *
    from commission
    where date_debut = (select max(date_debut) from commission)
);
