CREATE TABLE utilisateurs (
   id INT AUTO_INCREMENT,
   nom VARCHAR(50),
   prenom VARCHAR(50),
   email VARCHAR(100) NOT NULL UNIQUE,
   mot_de_passe VARCHAR(255) NOT NULL,
   user_type ENUM('parkingadmin','sysadmin','client') NOT NULL DEFAULT 'client',
   PRIMARY KEY (id)
);

CREATE TABLE abonnements (
   id INT AUTO_INCREMENT,
   nom VARCHAR(50) NOT NULL,
   description VARCHAR(255),
   PRIMARY KEY (id)
);

CREATE TABLE adresse (
   id INT AUTO_INCREMENT,
   numero VARCHAR(10),
   rue VARCHAR(100) NOT NULL,
   code_postal CHAR(5) NOT NULL,
   ville VARCHAR(100) NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE abonne (
   id_utilisateur INT NOT NULL,
   id_abonnement INT NOT NULL,
   est_actif BOOLEAN NOT NULL,
   PRIMARY KEY (id_utilisateur, id_abonnement),
   FOREIGN KEY (id_utilisateur) REFERENCES utilisateurs(id) ON DELETE CASCADE,
   FOREIGN KEY (id_abonnement) REFERENCES abonnements(id) ON DELETE CASCADE
);

CREATE TABLE adresse_utilisateur (
   id_adresse INT NOT NULL,
   id_utilisateur INT NOT NULL,
   PRIMARY KEY (id_adresse, id_utilisateur),
   FOREIGN KEY (id_adresse) REFERENCES adresse(id) ON DELETE CASCADE,
   FOREIGN KEY (id_utilisateur) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

CREATE TABLE parkings (
   id INT AUTO_INCREMENT,
   nom VARCHAR(50),
   capacite INT NOT NULL,
   hauteur_max DECIMAL(5,2),
   horaire_ouverture TIME,
   horaire_fermeture TIME,
   contient_places_moto BOOLEAN DEFAULT FALSE,
   id_adresse INT NOT NULL,
   tarif_horaire DECIMAL(5,2),
   PRIMARY KEY (id),
   FOREIGN KEY (id_adresse) REFERENCES adresse(id) ON DELETE RESTRICT,
   CHECK (capacite > 0)
);

CREATE TABLE zones_voirie (
   id INT AUTO_INCREMENT,
   couleur VARCHAR(50),
   tarif_horaire DECIMAL(5,2) NOT NULL,
   duree_max INT,
   debut_am TIME,
   fin_am TIME,
   debut_pm TIME,
   fin_pm TIME,
   PRIMARY KEY (id)
);

CREATE TABLE lignes_metro (
   id INT AUTO_INCREMENT,
   nom VARCHAR(50) NOT NULL,
   couleur VARCHAR(30),
   PRIMARY KEY (id)
);

CREATE TABLE vehicules (
   id INT AUTO_INCREMENT,
   immatriculation CHAR(10) UNIQUE NOT NULL,
   type_vehicule ENUM('electrique','hybride','moto','normal') NOT NULL,
   id_utilisateur INT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (id_utilisateur) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

CREATE TABLE reservations_parking (
   id INT AUTO_INCREMENT,
   date_arrivee DATETIME NOT NULL,
   date_depart DATETIME,
   id_parking INT NOT NULL,
   id_utilisateur INT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (id_parking) REFERENCES parkings(id) ON DELETE CASCADE,
   FOREIGN KEY (id_utilisateur) REFERENCES utilisateurs(id) ON DELETE CASCADE,
   CHECK (date_depart IS NULL OR date_depart > date_arrivee)
);

CREATE TABLE reservations_voirie (
   id INT AUTO_INCREMENT,
   date_debut DATETIME NOT NULL,
   duree_minutes INT NOT NULL,
   id_zone INT NOT NULL,
   id_utilisateur INT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (id_zone) REFERENCES zones_voirie(id) ON DELETE RESTRICT,
   FOREIGN KEY (id_utilisateur) REFERENCES utilisateurs(id) ON DELETE CASCADE,
   CHECK (duree_minutes > 0)
);

CREATE TABLE est_proche_de (
   id_parking INT NOT NULL,
   id_ligne_metro INT NOT NULL,
   distance_metres INT,
   PRIMARY KEY (id_parking, id_ligne_metro),
   FOREIGN KEY (id_parking) REFERENCES parkings(id) ON DELETE CASCADE,
   FOREIGN KEY (id_ligne_metro) REFERENCES lignes_metro(id) ON DELETE CASCADE,
   CHECK (distance_metres >= 0)
);

CREATE TABLE admins_parkings (
   id_utilisateur INT NOT NULL,
   id_parking INT NOT NULL,
   PRIMARY KEY (id_utilisateur, id_parking),
   FOREIGN KEY (id_utilisateur) REFERENCES utilisateurs(id) ON DELETE CASCADE,
   FOREIGN KEY (id_parking) REFERENCES parkings(id) ON DELETE CASCADE
);
