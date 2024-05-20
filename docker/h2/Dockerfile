FROM openjdk:11-jre-slim

RUN mkdir /opt/h2
WORKDIR /opt/h2
ADD h2-2023-08-22/h2/bin/h2*.jar /opt

EXPOSE 8082 1521 9092

CMD java -cp /opt/h2*.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists