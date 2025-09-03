# Estágio 1: Build da aplicação com Maven
FROM maven:3-eclipse-temurin-21-alpine AS build


# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar o cache do Docker
COPY pom.xml .

# Baixa as dependências do projeto
RUN mvn dependency:go-offline

# Copia o resto do código-fonte
COPY src ./src

# Executa o build do Maven para gerar o arquivo .jar
RUN mvn package -DskipTests

# Estágio 2: Criação da imagem final
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar gerado no estágio de build para a imagem final
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para executar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]