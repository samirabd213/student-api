-- Insertion des formations
INSERT INTO formation (id, name) VALUES
									 (1, 'Informatique'),
									 (2, 'Mathematics'),
									 (3, 'Physique');

-- Insertion des étudiants
INSERT INTO students (id, first_name, last_name, email, password) VALUES
																	  (1, 'Alice', 'Dupont', 'alice.dupont@example.com', 'password123'),
																	  (2, 'Bob', 'Martin', 'bob.martin@example.com', 'securePass!'),
																	  (3, 'Charlie', 'Lemoine', 'charlie.lemoine@example.com', 'charlie2024'),
																	  (4, 'David', 'Moreau', 'david.moreau@example.com', 'david@2024'),
																	  (5, 'Emma', 'Bernard', 'emma.bernard@example.com', 'emmaPass45');

-- Création de la table des groupes si elle n'existe pas déjà
CREATE TABLE IF NOT EXISTS groupe (
									  id BIGINT AUTO_INCREMENT PRIMARY KEY,
									  nom VARCHAR(255) NOT NULL,  -- Nom du groupe (par exemple, "Groupe 1")
									  type VARCHAR(10) NOT NULL   -- Type du groupe : "TD" ou "TP"
);

INSERT INTO groupe (id, nom, type)
SELECT 1, 'Groupe 1', 'TD' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM groupe WHERE id = 1);
INSERT INTO groupe (id, nom, type)
SELECT 2, 'Groupe 2', 'TP' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM groupe WHERE id = 2);
INSERT INTO groupe (id, nom, type)
SELECT 3, 'Groupe 3', 'TD' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM groupe WHERE id = 3);
INSERT INTO groupe (id, nom, type)
SELECT 4, 'Groupe 4', 'TP' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM groupe WHERE id = 4);

-- Création de la table d'association entre étudiants et groupes si elle n'existe pas déjà
CREATE TABLE IF NOT EXISTS student_groupes (
											   student_id BIGINT NOT NULL,
											   groupe_id BIGINT NOT NULL,
											   PRIMARY KEY (student_id, groupe_id),
											   FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
											   FOREIGN KEY (groupe_id) REFERENCES groupe(id) ON DELETE CASCADE
);

INSERT INTO student_groupes (student_id, groupe_id)
VALUES (1, 1), (1, 2), (2, 2), (2, 3), (3, 3), (3, 4), (4, 1), (4, 4), (5, 2), (5, 3);

-- Création de la table des UE si elle n'existe pas déjà
CREATE TABLE IF NOT EXISTS ue (
								  id BIGINT AUTO_INCREMENT PRIMARY KEY,
								  nom VARCHAR(255) NOT NULL,  -- Nom de l'UE
								  capacite INT NOT NULL,      -- Capacité de l'UE
								  est_obligatoire BOOLEAN NOT NULL -- Si l'UE est obligatoire
);

INSERT INTO ue (id, nom, capacite, est_obligatoire)
VALUES
	(1, 'Programmation Java', 50, TRUE),
	(2, 'Calcul Numérique', 40, TRUE),
	(3, 'Système d''exploitation', 30, FALSE),
	(4, 'Mathématiques Discrètes', 60, TRUE);

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
VALUES (1, 1), (1, 2),  -- Alice dans Programmation Java et Calcul Numérique
	   (2, 2), (2, 3),  -- Bob dans Calcul Numérique et Système d'exploitation
	   (3, 3), (3, 4),  -- Charlie dans Système d'exploitation et Mathématiques Discrètes
	   (4, 1), (4, 4),  -- David dans Programmation Java et Mathématiques Discrètes
	   (5, 1), (5, 2);  -- Emma dans Programmation Java et Calcul Numérique
