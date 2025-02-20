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

-- Assigner un étudiant à une formation (pour tester l'inscription)
-- Par exemple, inscrire Alice à la formation "Informatique" et Bob à "Mathematics"
UPDATE students SET formation_id = 1 WHERE id = 1;  -- Alice inscrite en Informatique
UPDATE students SET formation_id = 2 WHERE id = 2;  -- Bob inscrit en Mathematics
UPDATE students SET formation_id = 3 WHERE id = 3;  -- Charlie inscrit en Physique
