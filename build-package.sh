#!/bin/bash
cd apigateway
mvn clean package
#cp  apigateway-0.0.1-SNAPSHOT
cd ../config
mvn clean package
echo 'hi'
cd ../registry
mvn clean package

cd ../command
mvn clean package

cd ../query
mvn clean package
