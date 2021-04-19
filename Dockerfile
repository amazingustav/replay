FROM openjdk:11-jdk-slim
WORKDIR /tmp
COPY . .
RUN ./gradlew --console=plain --warning-mode all clean build -x test

FROM openjdk:11-jre-slim
EXPOSE 8087
WORKDIR /srv
COPY --from=0 /tmp .

ENTRYPOINT exec /entrypoint.sh
