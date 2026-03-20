INSERT INTO utilisateurs (nom, prenom, email, mdp, user_type) VALUES
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
(NULL,'Place Arnaud Bernard','31000','Toulouse'),
('4','Rue du docteur Suzanne Noël','31300','Toulouse'),
(NULL,'Place du Capitole','31000','Toulouse'),
(NULL,'Boulevard Lazare Carnot','31000','Toulouse'),
('9','Esplanade Compans Caffarelli','31000','Toulouse'),
(NULL,'Place des Carmes','31000','Toulouse'),
(NULL,'Place Esquirol','31000','Toulouse'),
(NULL,'Place de l''Europe','31000','Toulouse'),
(NULL,'Boulevard de Marengo','31500','Toulouse'),
('1','Rue Luce Boyals','31300','Toulouse'),
(NULL,'Allées Jean-Jaurès','31000','Toulouse'),
(NULL,'Place Jeanne d''Arc','31000','Toulouse'),
(NULL,'Boulevard Pierre Sémard','31500','Toulouse'),
(NULL,'Rue André Savès','31300','Toulouse'),
(NULL,'Route d''Agde','31500','Toulouse'),
(NULL,'Avenue du Mirail','31100','Toulouse'),
(NULL,'Borderouge','31200','Toulouse'),
(NULL,'Place Victor-Hugo','31000','Toulouse');

INSERT INTO adresse_utilisateur (id_adresse, id_utilisateur) VALUES
(1,1),
(2,2);

INSERT INTO parkings
(nom, capacite, hauteur_max, horaire_ouverture, horaire_fermeture, contient_places_moto, id_adresse, tarif)
VALUES
('Parking Arnaud Bernard',251,1.90,'07:00:00','23:00:00',1,3,3.39),
('Parking Du Barry',466,2.70,'07:00:00','23:00:00',1,4,1.85),
('Parking Capitole',840,1.80,'07:00:00','23:00:00',1,5,4.59),
('Parking Carnot',349,1.90,'07:00:00','23:00:00',1,6,1.89),
('Parking Compans-Caffarelli',990,1.80,'07:00:00','23:00:00',1,7,4.67),
('Parking des Carmes',496,1.80,'07:00:00','23:00:00',1,8,2.18),
('Parking Esquirol',300,1.80,'07:00:00','23:00:00',1,9,2.40),
('Parking Europe',345,1.90,'07:00:00','23:00:00',1,10,3.96),
('Parking Gare-Marengo',400,1.80,'07:00:00','23:00:00',1,11,4.08),
('Parking Jean-Jaurès',1172,1.90,'07:00:00','23:00:00',1,13,3.51),
('Parking Jeanne d''Arc',390,1.80,'07:00:00','23:00:00',1,14,3.84),
('Parking Matabiau',400,1.85,'07:00:00','23:00:00',1,15,3.66),
('Parking Raymond Badiou',555,3.00,'07:00:00','23:00:00',1,16,1.77),
('Parking relais Argoulets',1038,1.90,'07:00:00','23:00:00',1,17,3.36),
('Parking relais Basso Cambo',780,2.00,'07:00:00','23:00:00',1,18,2.98),
('Parking relais Borderouge',1165,1.90,'07:00:00','23:00:00',1,19,3.35),
('Parking Victor-Hugo',419,1.80,'07:00:00','23:00:00',1,20,2.79);

INSERT INTO zones_voirie
(couleur, tarif_horaire, duree_max, debut_am, fin_am, debut_pm, fin_pm)
VALUES
('jaune',1.50,150,'09:00:00','20:00:00',NULL,NULL),
('orange',1.00,300,'09:00:00','19:00:00',NULL,NULL),
('rouge',1.00,180,'09:00:00','19:00:00',NULL,NULL),
('verte',0.50,300,'09:00:00','19:00:00',NULL,NULL),
('bleue',0.00,90,'09:00:00','12:00:00','14:00:00','19:00:00');

INSERT INTO lignes_metro (nom, couleur) VALUES
('Métro A','Rouge'),
('Métro B','Bleu');

INSERT INTO est_proche_de (id_parking, id_ligne_metro, distance_metres) VALUES
(1,1,300),
(3,1,150),
(10,2,200),
(11,1,180),
(15,1,100);

INSERT INTO vehicules (immatriculation, type_vehicule, id_utilisateur) VALUES
('AB-123-CD','normal',1),
('EF-456-GH','electrique',2);

INSERT INTO reservations_parking
(date_arrivee, date_depart, id_parking, id_utilisateur)
VALUES
('2025-01-10 08:00:00','2025-01-10 10:00:00',1,1),
('2025-01-11 09:00:00',NULL,3,2);

INSERT INTO reservations_voirie
(date_debut, duree_minutes, id_zone, id_utilisateur)
VALUES
('2025-01-12 10:00:00',60,1,1),
('2025-01-13 14:00:00',90,5,2);

INSERT INTO abonne (id_utilisateur, id_abonnement, est_actif) VALUES
(1,1,1),
(2,2,1);

INSERT INTO admins_parkings (id_utilisateur, id_parking) VALUES
(3,1),
(3,2),
(3,3),
(3,4);

INSERT INTO reservations_parking (date_arrivee, date_depart, id_parking, id_utilisateur) VALUES
('2026-01-03 08:00:00','2026-01-03 10:00:00',1,1),
('2026-01-05 09:30:00','2026-01-05 11:00:00',3,2),
('2026-01-10 14:00:00','2026-01-10 16:30:00',5,1),
('2026-01-15 07:45:00','2026-01-15 09:15:00',1,2),
('2026-01-20 13:00:00','2026-01-20 15:00:00',3,1),
('2026-01-25 10:00:00','2026-01-25 12:00:00',5,2);

