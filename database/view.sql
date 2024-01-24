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
create  view v_annonce as (
    select 
    id as id_annonce, id_modele,id_categorie, id_marque, description, prix, annee_sortie
    from annonce
    join v_voiture as voiture
        on voiture.id_voiture = annonce.id_voiture 
);
create view v_modele as (
    select 
    modele.id as id_modele, categorie.id as id_categorie, marque.id as id_marque , annee_sortie
    from modele
    join categorie
        on categorie.id = modele.id_categorie
    join marque 
        on marque.id = modele.id_marque
);
create  view v_annonce_non_valide as
(
    select 
    annonce.*
    from annonce
    where annonce.status = 0
);

create view v_annonce_valide as
(
    select 
    annonce.*, historique_annonce.date_maj
    from annonce 
    join historique_annonce
        on historique_annonce.id_annonce = annonce.id
    where historique_annonce.status = 5
);
create view v_voiture as (
    select 
    voiture.id as id_voiture, modele.id_modele,  id_categorie, id_marque, annee_sortie
    from voiture    
    join v_modele as modele 
    on modele.id_modele = voiture.id_modele
);

create or replace  view v_annonce_vendu as (
    select 
    annonce.*, historique_annonce.date_maj
    from annonce 
    join historique_annonce
        on historique_annonce.id_annonce = annonce.id
    where historique_annonce.status = 10 
);

-- 19/01/2024 16:06
create view v_modele_marque as (
    select mo.*, ma.nom nom_marque
    from modele mo 
    join marque ma 
        on mo.id_marque = ma.id
);


-- 22/01/2024 10:00
create view v_annonce_general as
select a.*,
v.consommation, v.kilometrage, v.etat, v.id_couleur, v.id_modele, v.id_boite_vitesse, v.id_energie,
c.nom nom_couleur, c.hexa,
m.nom nom_modele, nb_place, nb_porte, annee_sortie, m.id_categorie, m.id_marque,
b.nom nom_vitesse,
e.nom nom_energie,
ca.nom nom_categorie,
ma.nom nom_marque, ma.logo,
u.nom utilisateur_nom, u.prenom utilisateur_prenom, u.date_inscription, u.adresse   
from annonce a
  join voiture v on a.id_voiture = v.id
  join couleur c on v.id_couleur = c.id
  join modele m on v.id_modele = m.id
  join boite_vitesse b on v.id_boite_vitesse = b.id
  join energie e on v.id_energie = e.id
  join categorie ca on m.id_categorie = ca.id
  join marque ma on m.id_marque = ma.id
  join utilisateur u on a.id_utilisateur = u.id;


create view v_annonce_gen_non_valide as 
select * from v_annonce_general where status = 0;

create view v_annonce_gen_valide as 
select a.*, h.date_maj 
from v_annonce_general a 
    join historique_annonce h 
    on a.id = h.id_annonce
where h.status = 5;

-- 24/01/2024 08:22
create view v_annonce_favori as
select h.*, f.id_utilisateur, f.date_ajout
from v_max_historique_annonce h 
join annonce_favori f 
	on h.id_annonce = f.id_annonce; 

