📘 Student API - Documentation
Student API est une application Spring Boot qui permet la gestion des étudiants. Elle utilise H2 Database en mode mémoire/local et est déployée avec Docker.

🚀 Installation & Exécution
📌 1. Prérequis
Avant de commencer, assure-toi d'avoir :
✅ Docker installé sur ta machine.
✅ Un outil comme Postman ou curl pour tester l’API.

🏗️ 2. Récupérer l’image Docker
Télécharge l’image depuis Docker Hub avec la commande :

docker pull samirabd213/student-api:latest
▶️ 3. Lancer le conteneur
Exécute le conteneur avec :

docker run -d -p 8080:8080 --name student-api samirabd213/student-api:latest
L’API sera accessible à l’adresse :
http://localhost:8080
🎯 Accéder à la base de données H2
L’API utilise une base H2 en mémoire.
👉 Pour accéder à la console H2, ouvre un navigateur et entre l’URL suivante :
http://localhost:8080/h2-console
🛠️ Configuration H2
Paramètre	Valeur
JDBC URL	jdbc:h2:mem:samir_db
User:	sa
Password:	(laisser vide)
Une fois connecté, tu peux voir et manipuler les tables (STUDENT).

📖 Endpoints de l’API
🔹 1. Créer un étudiant
Méthode : POST
URL : /etudiants/create
Body (JSON) :

{
"firstName": "Jean",
"lastName": "Dupont",
"email": "jean.dupont@example.com",
"password": "securepassword"
}
🔹 2. Récupérer un étudiant par ID
Méthode : GET
URL : /etudiants/{id}
🔹 3. Mettre à jour un étudiant
Méthode : PUT
URL : /etudiants/update/{id}
Body (JSON) :

{
"firstName": "Ahmed",
"lastName": "Kaka",
"email": "ahmed.kaka@example.com",
"password": "newpassword"
}
🔹 4. Supprimer un étudiant
Méthode : DELETE
URL : /etudiants/delete/{id}
🔹 5. Récupérer tous les étudiants
Méthode : GET
URL : /etudiants/all
🛑 Vérifier, arrêter et supprimer le conteneur
📌 Vérifier si le conteneur fonctionne
docker ps
Si le conteneur tourne, tu verras une ligne avec student-api.

🛑 Arrêter le conteneur

docker stop student-api
❌ Supprimer le conteneur
docker rm student-api
Avec ce README, tout utilisateur peut installer, exécuter et tester ton API facilement. 🚀
