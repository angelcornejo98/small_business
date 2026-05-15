FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=target/vms-0.0.1.jar
COPY ${JAR_FILE} app_vms.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","app_vms.jar"]