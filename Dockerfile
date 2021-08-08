FROM adoptopenjdk:16-jre-hotspot
RUN mkdor /opt/app
COPY target/court_reservation-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/court_reservation.jar"]