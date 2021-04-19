#!/bin/sh

./gradlew flywayBaseline flywayMigrate

exec java -jar "build/libs/replay-micronaut.jar"
