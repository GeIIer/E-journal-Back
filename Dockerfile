FROM maven:3.8.3-openjdk-17 AS builder
WORKDIR /app
COPY ./pom.xml /app/pom.xml
COPY ./src /app/src
RUN mvn -f /app/pom.xml clean package -DskipTests


FROM amazoncorretto:17
RUN /bin/bash -c "yum update -y && yum install -y curl"
VOLUME /tmp
ARG BUILDER_DIR=app
ARG APPJAR=target/*.jar
COPY --from=builder ${BUILDER_DIR}/${APPJAR} /app/app.jar
ENV TZ=Europe/Samara
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]