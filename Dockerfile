FROM adoptopenjdk:16-jre-hotspot
RUN mkdir /opt/app
COPY target/court_reservation-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/court_reservation.jar"]