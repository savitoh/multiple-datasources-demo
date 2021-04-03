## Build project
FROM maven:3.6.3-openjdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B -f pom.xml clean package -DskipTests


FROM openjdk:11-jre-slim
EXPOSE 8080
VOLUME /tmp
COPY --from=build /workspace/target/multiple-datasources-demo-0.0.1-SNAPSHOT.jar multiple-datasources-demo-0.0.1-SNAPSHOT.jar
CMD ["sh", "-c", " java ${JAVA_OPTS} -jar multiple-datasources-demo-0.0.1-SNAPSHOT.jar"]