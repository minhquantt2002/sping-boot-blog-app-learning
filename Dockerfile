FROM eclipse-temurin:17

WORKDIR /app

COPY target/blog-0.0.1-SNAPSHOT.jar /app/blog-spring.jar

ENTRYPOINT ["java", "-jar", "blog-spring.jar"]

