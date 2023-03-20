FROM maven:3.8.3-openjdk-17
WORKDIR /usr/src/app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
COPY pom.xml ./
#RUN ./mvnw dependency:resolve
RUN mvn dependency:resolve
COPY src ./src
#CMD ["./mvnw", "spring-boot:run"]
CMD ["mvn", "spring-boot:run"]
EXPOSE 8080

