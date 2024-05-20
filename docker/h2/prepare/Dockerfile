FROM openjdk:11-jdk-slim
WORKDIR /app

COPY h2-2.2.222.jar .
COPY build_schema.sql .
COPY entrypoint.sh .

RUN chmod +x entrypoint.sh

ENTRYPOINT [ "./entrypoint.sh" ]