#!/bin/sh
export SPRING_DATASOURCE_URL='jdbc:postgresql://localhost:5432/postgres'
export SPRING_DATASOURCE_PASSWORD='postgres'
export SPRING_DATASOURCE_USERNAME='postgres'
echo $SPRING_DATASOURCE_USERNAME
echo $SPRING_DATASOURCE_URL
docker compose up
./mvn install

