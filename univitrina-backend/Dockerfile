FROM openjdk:11-slim

COPY target/univitrina-backend.jar /app/
ENTRYPOINT ["java","-jar","-Xms128m", "-Xmx256m","-DsettingsDir=/app/etc/","/app/univitrina-backend.jar"]
EXPOSE 4000
