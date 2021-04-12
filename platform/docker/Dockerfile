FROM openjdk:11-jdk-slim

COPY . /tmp
WORKDIR /tmp
CMD ./gradlew --console=plain --warning-mode all clean build

EXPOSE 8087

CMD java -jar /tmp/main/build/libs/replay-micronaut.jar .
