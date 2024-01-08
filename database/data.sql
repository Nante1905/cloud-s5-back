insert into role (nom, reference) values 
('admin', 'ADMIN'),
('utilisateur', 'USER');

-- Categorie
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

-- Marque
INSERT INTO marque (nom, logo) VALUES
  ('Toyota', 'Toyota.jpg'),
  ('Honda', 'Honda.jpg'),
  ('Ford', 'Ford.jpg'),
  ('Chevrolet', 'Chevrolet.jpg'),
  ('BMW', 'BMW.jpg'),
  ('Mercedes-Benz', 'Mercedes-Benz.jpg'),
  ('Audi', 'Audi.jpg'),
  ('Nissan', 'Nissan.jpg'),
  ('Volkswagen', 'Volkswagen.jpg'),
  ('Hyundai', 'Hyundai.jpg');

-- Modele 
INSERT INTO modele (nom, nb_place, nb_porte, annee_sortie, id_categorie, id_marque) VALUES
  -- Toyota
  ('Corolla', 5, 4, 2021, 2, 1),
  ('RAV4', 5, 4, 2022, 4, 1),
  ('Camry', 5, 4, 2021, 2, 1),
  ('Highlander', 7, 4, 2023, 4, 1),
  ('Land Cruiser', 8, 4, 2020, 4, 1),
  ('Sienna', 8, 4, 2022, 5, 1),
  ('Tacoma', 5, 2, 2022, 8, 1),
  ('Supra', 2, 2, 2023, 6, 1),
  ('4Runner', 7, 4, 2021, 4, 1),
  -- Honda
  ('Accord', 5, 4, 2021, 2, 2),
  ('CR-V', 5, 4, 2022, 4, 2),
  ('Civic Type R', 4, 2, 2023, 6, 2),
  ('Pilot', 8, 4, 2021, 4, 2),
  ('Odyssey', 8, 4, 2022, 5, 2),
  ('Fit', 5, 4, 2022, 1, 2),
  ('Ridgeline', 5, 4, 2022, 8, 2),
  ('HR-V', 5, 4, 2022, 4, 2),
  ('Clarity', 5, 4, 2021, 9, 2),

  -- Ford
  ('Mustang', 4, 2, 2023, 6, 3),
  ('Escape', 5, 4, 2022, 4, 3),
  ('Explorer', 7, 4, 2021, 4, 3),
  ('Fusion', 5, 4, 2022, 2, 3),
  ('F-150', 5, 4, 2022, 8, 3),
  ('Edge', 5, 4, 2023, 4, 3),
  ('EcoSport', 5, 4, 2021, 4, 3),
  ('Bronco', 5, 2, 2022, 4, 3),
  ('Ranger', 5, 4, 2021, 8, 3),
  ('Mach-E', 5, 4, 2021, 9, 3);

-- Chevrolet
INSERT INTO modele (nom, nb_place, nb_porte, annee_sortie, id_categorie, id_marque) VALUES
  ('Malibu', 5, 4, 2022, 2, 4),
  ('Equinox', 5, 4, 2021, 4, 4),
  ('Camaro', 4, 2, 2023, 6, 4),
  ('Traverse', 7, 4, 2021, 4, 4),
  ('Silverado', 5, 4, 2022, 8, 4),
  ('Blazer', 5, 4, 2022, 4, 4),
  ('Tahoe', 8, 4, 2022, 4, 4),
  ('Suburban', 8, 4, 2021, 4, 4),
  ('Spark', 4, 4, 2022, 1, 4),
  ('Bolt', 5, 4, 2021, 9, 4);

-- BMW
INSERT INTO modele (nom, nb_place, nb_porte, annee_sortie, id_categorie, id_marque) VALUES
  ('3 Series', 5, 4, 2023, 2, 5),
  ('X3', 5, 4, 2022, 4, 5),
  ('5 Series', 5, 4, 2021, 2, 5),
  ('X5', 5, 4, 2022, 4, 5),
  ('2 Series', 4, 2, 2022, 6, 5),
  ('X1', 5, 4, 2022, 4, 5),
  ('7 Series', 5, 4, 2021, 2, 5),
  ('X7', 7, 4, 2022, 4, 5),
  ('4 Series', 5, 4, 2022, 6, 5),
  ('i3', 4, 4, 2021, 9, 5);

-- Mercedes-Benz
INSERT INTO modele (nom, nb_place, nb_porte, annee_sortie, id_categorie, id_marque) VALUES
  ('C-Class', 5, 4, 2022, 2, 6),
  ('GLC', 5, 4, 2021, 4, 6),
  ('E-Class', 5, 4, 2021, 2, 6),
  ('GLE', 5, 4, 2022, 4, 6),
  ('A-Class', 5, 4, 2022, 1, 6),
  ('GLA', 5, 4, 2023, 4, 6),
  ('S-Class', 5, 4, 2021, 7, 6),
  ('GLS', 7, 4, 2022, 4, 6),
  ('CLA', 5, 4, 2022, 3, 6),
  ('EQC', 5, 4, 2021, 9, 6);

-- Audi
INSERT INTO modele (nom, nb_place, nb_porte, annee_sortie, id_categorie, id_marque) VALUES
  ('A4', 5, 4, 2021, 2, 7),
  ('Q5', 5, 4, 2022, 4, 7),
  ('A3', 5, 4, 2023, 2, 7),
  ('Q7', 7, 4, 2021, 4, 7),
  ('A5', 5, 4, 2022, 6, 7),
  ('Q3', 5, 4, 2022, 4, 7),
  ('A6', 5, 4, 2021, 2, 7),
  ('Q8', 5, 4, 2022, 4, 7),
  ('S4', 5, 4, 2022, 6, 7),
  ('e-tron', 5, 4, 2021, 9, 7);

-- Nissan
INSERT INTO modele (nom, nb_place, nb_porte, annee_sortie, id_categorie, id_marque) VALUES
  ('Altima', 5, 4, 2022, 2, 8),
  ('Rogue', 5, 4, 2021, 4, 8),
  ('Maxima', 5, 4, 2023, 2, 8),
  ('Murano', 5, 4, 2022, 4, 8),
  ('370Z', 2, 2, 2022, 6, 8),
  ('Pathfinder', 7, 4, 2022, 4, 8),
  ('Versa', 5, 4, 2021, 1, 8),
  ('Titan', 5, 4, 2022, 8, 8),
  ('Armada', 8, 4, 2022, 4, 8),
  ('Leaf', 5, 4, 2021, 9, 8);

-- Volkswagen
INSERT INTO modele (nom, nb_place, nb_porte, annee_sortie, id_categorie, id_marque) VALUES
  ('Golf', 5, 4, 2021, 1, 9),
  ('Tiguan', 5, 4, 2022, 4, 9),
  ('Passat', 5, 4, 2022, 2, 9),
  ('Atlas', 7, 4, 2023, 4, 9),
  ('Arteon', 5, 4, 2022, 2, 9),
  ('Jetta', 5, 4, 2021, 2, 9),
  ('ID.4', 5, 4, 2021, 9, 9),
  ('Taos', 5, 4, 2022, 4, 9),
  ('Touareg', 5, 4, 2021, 4, 9);

-- Hyundai
INSERT INTO modele (nom, nb_place, nb_porte, annee_sortie, id_categorie, id_marque) VALUES
  ('Elantra', 5, 4, 2021, 2, 10),
  ('Tucson', 5, 4, 2022, 4, 10),
  ('Sonata', 5, 4, 2023, 2, 10),
  ('Santa Fe', 5, 4, 2021, 4, 10),
  ('Veloster', 4, 3, 2022, 6, 10),
  ('Kona', 5, 4, 2022, 4, 10),
  ('Palisade', 8, 4, 2022, 4, 10),
  ('Venue', 5, 4, 2021, 4, 10),
  ('Ioniq', 5, 4, 2021, 9, 10),
  ('Nexo', 5, 4, 2022, 9, 10);

INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, date_inscription, adresse, id_role) VALUES
('Rakotonirina', 'Tiavina Irintsoa', 'rakotonirinairintsoa0@gmail.com', 'tiavina', '2022-12-15 15:20:00', 'Ankadifotsy', 2 ),
('Razafinjaka', 'Ralph Yoan', 'yoanrazafinjaka@gmail.com', 'ralph', '2020-12-15 15:20:00', 'Ankazomanga', 1 ),
('Andriamazaoro', 'Minohary Nantenaina', 'nantemino15@gmail.com', 'nantenaina', '2023-12-15 15:20:00', 'Ambatobe', 2 ),
('Rakotondriaka', 'Mialisoa Murielle', 'mialisoamurielle@gmail.com', 'mialisoa', '2024-01-05 15:20:00', 'Ambatobe', 2 );

--boite de vitesse
INSERT INTO boite_vitesse (nom) VALUES ('Boite de vitesses Audi S tronic');
INSERT INTO boite_vitesse (nom) VALUES ('Boite de vitesses BMW M Steptronic');
INSERT INTO boite_vitesse (nom) VALUES ('Boite de vitesses Mercedes AMG Speedshift');

--energie
insert into energie values
(default, 'Gasoil'),
(default, 'Essence SP 95'),
(default, 'Electrique'),
(default, 'Hybride');

--couleur
insert into couleur values
(default, 'Noir', '#333');

--voitures
insert into voiture values 
(default, 20, 2000, 1,1, 41,1, 4);

--annonce
insert into annonce values 
(default, 'AN890', 'Telephone:+2613434556, Lieu: Tanjombato',1,now(),35000000,30,12, 1, 2);