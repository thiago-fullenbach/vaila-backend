# Imagem base
FROM eclipse-temurin:21-jdk

# Seleciona pasta de trabalho
WORKDIR /app

# Copia arquivo jar gerado na pasta target para pasta de trabalho
COPY /target/*.jar app.jar

# Roda aplicação
ENTRYPOINT [ "java", "-jar", "app.jar" ]