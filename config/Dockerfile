FROM maven:3-jdk-11

ADD . /config
WORKDIR /config

RUN ls -l

RUN mvn clean install -U

CMD mvn spring-boot:run
