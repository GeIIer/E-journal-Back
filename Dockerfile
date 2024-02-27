FROM maven:3.8.3-amazoncorretto-17 as builder
WORKDIR project
# Copy pom.xml from cache layer
COPY ./pom.xml ./pom.xml
# Copy project file
COPY ./src ./src
# Build project with out test(if need run test, use gitlab CI/CD)
RUN mvn package -DskipTests

FROM amazoncorretto:11.0.8-alpine
# install curl(heartbeat test)
RUN apk --no-cache add curl
# create volume(if read file, drop error)
VOLUME /tmp
# get prev workdir(from builder)
ARG BUILDER_DIR=project
# create regexp jar file (maybe it's not need)
ARG APPJAR=target/*.jar
# load build file
COPY --from=builder ${BUILDER_DIR}/${APPJAR} app.jar
# set timezone
ENV TZ=Russian/Samara
# Run java project
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom", "app.jar"]