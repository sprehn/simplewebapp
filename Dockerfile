FROM openjdk:8-jdk
VOLUME /tmp
ADD target/webapp-0.1-SNAPSHOT.jar /app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
EXPOSE 8080
