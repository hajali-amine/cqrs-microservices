FROM maven:3-jdk-11

ADD . /apigateway
WORKDIR /apigateway

RUN ls -l

RUN mvn clean install -U

CMD mvn spring-boot:run
