USE sae_parking;

DELETE FROM abonne;
DELETE FROM admins_parkings;
DELETE FROM adresse_utilisateur;
DELETE FROM est_proche_de;
DELETE FROM reservations_parking;
DELETE FROM reservations_voirie;
DELETE FROM vehicules;
DELETE FROM parkings;
DELETE FROM zones_voirie;
DELETE FROM lignes_metro;
DELETE FROM abonnements;
DELETE FROM adresse;
DELETE FROM utilisateurs;

INSERT INTO utilisateurs (nom, prenom, email, mot_de_passe, user_type) VALUES
('Durand','Alice','alice.durand@mail.com','password123','client'),
('Martin','Bob','bob.martin@mail.com','password123','client'),
('Admin','Parking','admin.parking@mail.com','adminpass','parkingadmin'),
('Admin','Systeme','admin.sys@mail.com','adminpass','sysadmin');

INSERT INTO abonnements (nom, description) VALUES
('Résident annuel','Abonnement résident valable 1 an'),
('Résident hebdomadaire','Abonnement résident 1 semaine'),
('Résident quinzaine','Abonnement résident 2 semaines'),
('Résident électrique','Abonnement gratuit véhicule électrique');

INSERT INTO adresse (numero, rue, code_postal, ville) VALUES
('12','Rue Alsace Lorraine','31000','Toulouse'),
('5','Rue du Taur','31000','Toulouse'),
('','Place Arnaud Bernard','31000','Toulouse'),
('4','Rue du docteur Suzanne Noël','31300','Toulouse'),
('','Place du Capitole','31000','Toulouse'),
('','Boulevard Lazare Carnot','31000','Toulouse'),
('9','Esplanade Compans Caffarelli','31000','Toulouse'),
('','Place des Carmes','31000','Toulouse'),
('','Place Esquirol','31000','Toulouse'),
('','Place de l Europe','31000','Toulouse'),
('','Boulevard de Marengo','31500','Toulouse'),
('1','Rue Luce Boyals','31300','Toulouse'),
('','Allées Jean-Jaurès','31000','Toulouse'),
('','Place Jeanne d Arc','31000','Toulouse'),
('','Boulevard Pierre Sémard','31500','Toulouse'),
('','Rue André Savès','31300','Toulouse'),
('','Route d Agde','31500','Toulouse'),
('','Avenue du Mirail','31100','Toulouse'),
('','Borderouge','31200','Toulouse'),
('','Place Victor-Hugo','31000','Toulouse');

INSERT INTO parkings (nom, capacite, hauteur_max, horaire_ouverture, horaire_fermeture, contient_places_moto, id_adresse) VALUES
('Parking Arnaud Bernard',251,1.90,'07:00:00','23:00:00',1,3),
('Parking Du Barry',466,2.70,'07:00:00','23:00:00',1,4),
('Parking Capitole',840,1.80,'07:00:00','23:00:00',1,5),
('Parking Carnot',349,1.90,'07:00:00','23:00:00',1,6),
('Parking Compans-Caffarelli',990,1.80,'07:00:00','23:00:00',1,7),
('Parking des Carmes',496,1.80,'07:00:00','23:00:00',1,8),
('Parking Esquirol',300,1.80,'07:00:00','23:00:00',1,9),
('Parking Europe',345,1.90,'07:00:00','23:00:00',1,10),
('Parking Gare-Marengo',400,1.80,'07:00:00','23:00:00',1,11),
('Parking Jean-Jaurès',1172,1.90,'07:00:00','23:00:00',1,13),
('Parking Jeanne d Arc',390,1.80,'07:00:00','23:00:00',1,14),
('Parking Matabiau',400,1.85,'07:00:00','23:00:00',1,15),
('Parking Raymond Badiou',555,3.00,'07:00:00','23:00:00',1,16),
('Parking relais Argoulets',1038,1.90,'07:00:00','23:00:00',1,17),
('Parking relais Basso Cambo',780,2.00,'07:00:00','23:00:00',1,18),
('Parking relais Borderouge',1165,1.90,'07:00:00','23:00:00',1,19),
('Parking Victor-Hugo',419,1.80,'07:00:00','23:00:00',1,20);

INSERT INTO lignes_metro (nom, couleur) VALUES
('Métro A','Rouge'),
('Métro B','Bleu');

INSERT INTO zones_voirie (nom, tarif_horaire, duree_max) VALUES
('Zone Jaune',1.50,150),
('Zone Orange',1.00,300),
('Zone Rouge',1.00,180),
('Zone Verte',0.50,300),
('Zone Bleue',0.00,90);

INSERT INTO abonne VALUES
(1,1,1),
(2,2,1);

INSERT INTO admins_parkings VALUES
(3,1),
(3,3),
(3,5),
(3,10);

INSERT INTO adresse_utilisateur VALUES
(1,1),
(2,2);

INSERT INTO est_proche_de VALUES
(1,1,300),
(3,1,150),
(8,2,200),
(9,1,180),
(10,1,100);

INSERT INTO reservations_parking (date_arrivee, date_depart, id_parking, id_utilisateur) VALUES
('2025-01-10 08:00:00','2025-01-10 10:00:00',1,1),
('2025-01-11 09:00:00',NULL,3,2);

INSERT INTO reservations_voirie (date_debut, duree_minutes, id_zone, id_utilisateur) VALUES
('2025-01-12 10:00:00',60,1,1),
('2025-01-13 14:00:00',90,5,2);

INSERT INTO vehicules (immatriculation, type_vehicule, id_utilisateur) VALUES
('AB-123-CD','normal',1),
('EF-456-GH','electrique',2);
