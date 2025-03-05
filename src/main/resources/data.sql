-- Création de la table des formations
CREATE TABLE IF NOT EXISTS formation (
										 id BIGINT AUTO_INCREMENT PRIMARY KEY,
										 name VARCHAR(255) NOT NULL
);

-- Insertion des formations
INSERT INTO formation (name) VALUES
								 ('Informatique'),
								 ('Mathematiques'),
								 ('Physique');

-- Création de la table des étudiants
CREATE TABLE IF NOT EXISTS students (
										id BIGINT AUTO_INCREMENT PRIMARY KEY,
										first_name VARCHAR(255) NOT NULL,
										last_name VARCHAR(255) NOT NULL,
										email VARCHAR(255) NOT NULL UNIQUE,
										password VARCHAR(255) NOT NULL
);

-- Insertion des étudiants
INSERT INTO students (first_name, last_name, email, password) VALUES
																  ('Alice', 'Dupont', 'alice.dupont@example.com', 'password123'),
																  ('Bob', 'Martin', 'bob.martin@example.com', 'securePass!'),
																  ('Charlie', 'Lemoine', 'charlie.lemoine@example.com', 'charlie2024'),
																  ('David', 'Moreau', 'david.moreau@example.com', 'david@2024'),
																  ('Emma', 'Bernard', 'emma.bernard@example.com', 'emmaPass45');

-- Création de la table des groupes
CREATE TABLE IF NOT EXISTS groupe (
									  id BIGINT AUTO_INCREMENT PRIMARY KEY,
									  nom VARCHAR(255) NOT NULL,  -- Nom du groupe (par exemple, "Groupe 1")
									  type VARCHAR(10) NOT NULL   -- Type du groupe : "TD" ou "TP"
);

-- Insertion des groupes
INSERT INTO groupe (nom, type)
SELECT 'Groupe 1', 'TD' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM groupe WHERE nom = 'Groupe 1');
INSERT INTO groupe (nom, type)
SELECT 'Groupe 2', 'TP' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM groupe WHERE nom = 'Groupe 2');
INSERT INTO groupe (nom, type)
SELECT 'Groupe 3', 'TD' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM groupe WHERE nom = 'Groupe 3');
INSERT INTO groupe (nom, type)
SELECT 'Groupe 4', 'TP' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM groupe WHERE nom = 'Groupe 4');

-- Création de la table d'association entre étudiants et groupes
CREATE TABLE IF NOT EXISTS student_groupes (
											   student_id BIGINT NOT NULL,
											   groupe_id BIGINT NOT NULL,
											   PRIMARY KEY (student_id, groupe_id),
											   FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
											   FOREIGN KEY (groupe_id) REFERENCES groupe(id) ON DELETE CASCADE
);

-- Insertion des liens étudiants-groupes
INSERT INTO student_groupes (student_id, groupe_id)
VALUES
	(1, 1), (1, 2),
	(2, 2), (2, 3),
	(3, 3), (3, 4),
	(4, 1), (4, 4),
	(5, 2), (5, 3);

-- Création de la table des UE
CREATE TABLE IF NOT EXISTS ue (
								  id BIGINT AUTO_INCREMENT PRIMARY KEY,
								  nom VARCHAR(255) NOT NULL,  -- Nom de l'UE
								  capacite INT NOT NULL,      -- Capacité de l'UE
								  est_obligatoire BOOLEAN NOT NULL -- Si l'UE est obligatoire
);

-- Insertion des UE
INSERT INTO ue (nom, capacite, est_obligatoire)
VALUES
	('Programmation Java', 50, TRUE),
	('Calcul Numérique', 40, TRUE),
	('Système d''exploitation', 30, FALSE),
	('Mathématiques Discrètes', 60, TRUE);

-- Création de la table d'association entre étudiants et UE
CREATE TABLE IF NOT EXISTS student_ue (
										  student_id BIGINT NOT NULL,
										  ue_id BIGINT NOT NULL,
										  PRIMARY KEY (student_id, ue_id),
										  FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
										  FOREIGN KEY (ue_id) REFERENCES ue(id) ON DELETE CASCADE
);

-- Inscrire les étudiants aux UE
INSERT INTO student_ue (student_id, ue_id)
VALUES
	(1, 1), (1, 2),  -- Alice dans Programmation Java et Calcul Numérique
	(2, 2), (2, 3),  -- Bob dans Calcul Numérique et Système d'exploitation
	(3, 3), (3, 4),  -- Charlie dans Système d'exploitation et Mathématiques Discrètes
	(4, 1), (4, 4),  -- David dans Programmation Java et Mathématiques Discrètes
	(5, 1), (5, 2);  -- Emma dans Programmation Java et Calcul Numérique
