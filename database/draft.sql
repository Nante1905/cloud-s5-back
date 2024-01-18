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