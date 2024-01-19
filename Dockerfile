FROM maven:3.9.5 AS maven
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM azul/zulu-openjdk:17-latest
WORKDIR /app

COPY --from=maven /app/target/*.jar /app/app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]