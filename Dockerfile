# Étape 1: Utiliser une image de base contenant Java (JDK)
FROM openjdk:17-slim as builder

# Étape 2: Définir le répertoire de travail dans l'image Docker
WORKDIR /app

# Étape 3: Copier le fichier pom.xml et télécharger les dépendances (si tu utilises Maven)
COPY pom.xml .

# Étape 4: Installer Maven et télécharger les dépendances
RUN apt-get update && apt-get install -y maven && mvn dependency:go-offline

# Étape 5: Copier le code source du projet
COPY src ./src

# Étape 6: Compiler le projet et créer le fichier JAR
RUN mvn clean package -DskipTests

# Étape 7: Utiliser la même image de base pour l'exécution
FROM openjdk:17-slim

# Étape 8: Copier le fichier JAR généré depuis l'étape précédente
COPY --from=builder /app/target/student-api-0.0.1-SNAPSHOT.jar /app/student-api.jar

# Étape 9: Exposer le port de l'API (ex. 8080 par défaut)
EXPOSE 8080

# Étape 10: Commande pour exécuter l'API
ENTRYPOINT ["java", "-jar", "/app/student-api.jar"]
