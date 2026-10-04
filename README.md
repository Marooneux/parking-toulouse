# Parking Toulouse

Application de bureau en **Java Swing** pour trouver un stationnement à Toulouse (parking ou place en voirie), réserver et payer, avec un espace dédié aux gestionnaires de parkings.

Projet réalisé en équipe de 4 dans le cadre de notre 2ᵉ année de BUT Informatique à l'IUT Paul Sabatier (Toulouse III).

## Fonctionnalités

**Côté utilisateur**
- Inscription et connexion (mots de passe hachés avec BCrypt)
- Choix du type de stationnement : parking ou voirie
- Voirie : choix de la zone et de la durée de stationnement
- Parking : choix du parking (avec les lignes de métro à proximité) et de l'heure d'arrivée
- Paiement simulé par carte ou virement, puis génération d'un ticket
- Historique des réservations
- Gestion du profil, des véhicules et des abonnements

**Côté administrateur de parking**
- Ajout et modification de parkings
- Statistiques de fréquentation

## Stack technique

| Élément | Technologie |
|---|---|
| Langage | Java 21 |
| Interface | Swing |
| Base de données | MySQL (JDBC, `mysql-connector-j`) |
| Sécurité | jBCrypt |
| Build | Maven |
| Tests | JUnit 4, couverture JaCoCo |
| Qualité | SonarCloud |

## Architecture

Le projet suit le modèle **MVC** avec une couche **DAO** pour l'accès aux données :

```
src/main/java/
├── app/          Point d'entrée (App.java)
├── modele/       Classes métier (Parking, ZoneVoirie, Reservation, Paiement...)
│   └── dao/      Accès à la base MySQL, une requête par classe dans requetes/
├── vue/          Écrans Swing (utilisateur et adminParking/)
├── controleur/   Logique de chaque écran
├── utils/        Authentification et hachage des mots de passe
└── ui/theme/     Thème graphique
src/main/resources/
├── schema.sql    Création des tables
└── data.sql      Données de démonstration (parkings et zones à Toulouse)
```

## Organisation du projet

Le projet a été mené en équipe de 4 avec la méthode agile **Scrum**, sur **6 sprints**.

- **Backlog et tâches** : chaque fonctionnalité ou correction était décrite dans une issue GitLab, puis assignée et suivie sur un board
- **Sprints** : à chaque sprint, un ensemble d'issues était sélectionné dans le backlog, réalisé puis présenté
- **Branches** : développement sur des branches dédiées, intégrées via merge requests vers `develop` puis `main`
- **Qualité** : analyse SonarCloud automatique sur chaque merge request (pipeline GitLab CI)

> Le projet ayant été développé sur le GitLab de l'IUT, les issues et le board ne sont pas accessibles depuis ce dépôt. L'historique complet des commits est en revanche conservé.

## Compétences développées

| Domaine | Compétences |
|---|---|
| Conception | Architecture MVC, patron DAO, design patterns (Singleton, Itérateur) |
| Développement | Programmation orientée objet en Java, interfaces graphiques Swing |
| Base de données | Modélisation relationnelle, SQL, accès aux données avec JDBC |
| Sécurité | Hachage des mots de passe (BCrypt), requêtes préparées contre l'injection SQL |
| Qualité | Tests unitaires JUnit, mesure de couverture (JaCoCo), analyse statique (SonarCloud) |
| Travail en équipe | Scrum, gestion de tâches par issues, workflow Git avec branches et merge requests |

## Installation

### Prérequis
- JDK 21
- Maven 3.9+
- MySQL 8

### 1. Cloner le projet
```bash
git clone https://github.com/Marooneux/parking-toulouse.git
cd parking-toulouse
```

### 2. Créer la base de données
```bash
mysql -u root -p -e "CREATE DATABASE sae_parking;"
mysql -u root -p sae_parking < src/main/resources/schema.sql
mysql -u root -p sae_parking < src/main/resources/data.sql
```

### 3. Configurer la connexion
L'application lit les identifiants MySQL dans des variables d'environnement :

| Variable | Obligatoire | Valeur par défaut |
|---|---|---|
| `DB_USER` | Oui | aucune |
| `DB_PASS` | Non | vide |
| `DB_URL` | Non | `jdbc:mysql://localhost:3306/sae_parking` |

```bash
# Linux / macOS
export DB_USER=root
export DB_PASS=tonMotDePasse

# Windows (PowerShell)
$env:DB_USER="root"
$env:DB_PASS="tonMotDePasse"
```

### 4. Lancer l'application
```bash
mvn compile exec:java -Dexec.mainClass=app.App
```

Avec un IDE (Eclipse, IntelliJ), importer le projet en tant que projet Maven puis lancer `app.App`.

### 5. Lancer les tests
Les tests utilisent la base de données, les variables d'environnement doivent donc être définies.
```bash
mvn test
```

## Équipe

- [CUMBANE Claudio](https://github.com/claudio-narciso)
- [MUNKH-ERDENE Dulguun](https://github.com/Dulguun012)
- [NADIRI Noam](https://github.com/noam-nadiri)
- [WACKER Luka](https://github.com/Marooneux)
