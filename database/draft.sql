INSERT INTO categorie (nom) VALUES
  ('Citadines'),
  ('Berlines'),
  ('Coupés'),
  ('SUV'),
  ('Monospaces'),
  ('Coupés Sport'),
  ('Voitures de Luxe'),
   ('Pick-up'),
  ('Voitures électriques');




select coalesce(avg(EXTRACT('days' FROM (av.date_creation - av.date_maj))), 0)
from v_annonce_vendu av
where extract(month from date_maj) = 1 and extract(year from date_maj) = 2024;

select a.*,
v.consommation, v.kilometrage, v.etat, v.id_couleur, v.id_modele, v.id_boite_vitesse, v.id_energie,
c.nom nom_couleur, c.hexa,
m.nom nom_modele, nb_place, nb_porte, annee_sortie, m.id_categorie, m.id_marque,
b.nom nom_vitesse,
e.nom nom_energie,
ca.nom nom_categorie,
ma.nom nom_marque, ma.logo,
u.nom utilisateur_nom, u.prenom utilisateur_prenom, u.date_inscription   
from annonce a
  join voiture v on a.id_voiture = v.id
  join couleur c on v.id_couleur = c.id
  join modele m on v.id_modele = m.id
  join boite_vitesse b on v.id_boite_vitesse = b.id
  join energie e on v.id_energie = e.id
  join categorie ca on m.id_categorie = ca.id
  join marque ma on m.id_marque = ma.id
  join utilisateur u on a.id_utilisateur = u.id
limit 2;



select a.id, a.reference,
  case 
    when f.date_ajout is null
    then false
    else true
    end favori
  from v_annonce_valide a
  left outer join annonce_favori f
    on a.id = f.id_annonce and f.id_utilisateur = 1
limit 4;
