# Define a imagem base
# FROM adoptopenjdk:11-jdk-hotspot

# Define o diretório de trabalho dentro do contêiner
# WORKDIR /app

# Copia o arquivo JAR para o diretório de trabalho
# COPY target/recall-de-compras-0.0.1-SNAPSHOT.jar app.jar

# Define o comando para executar o aplicativo quando o contêiner for iniciado
# CMD ["java", "-jar", "app.jar"]

#  BUILD stage
FROM maven:alpine AS build
COPY /src/main /home/app/src/main
COPY pom.xml /home/app
WORKDIR /home/app
RUN mvn clean package 

FROM adoptopenjdk:11-jdk-hotspot
COPY target/recall-de-compras-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

