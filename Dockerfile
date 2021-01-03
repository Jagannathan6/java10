FROM adoptopenjdk/openjdk14
EXPOSE 8087
COPY build/libs/java11-1.0.jar
ENTRYPOINT ["java","-jar","/java11-1.0.jar"]