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
where extract(month from date_maj) = 1 and extract(year from date_maj) = 2024

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