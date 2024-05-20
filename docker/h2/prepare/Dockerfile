FROM openjdk:11-jdk-slim
WORKDIR /app

COPY h2-2.2.222.jar .
COPY build_schema.sql .
COPY entrypoint.sh .

RUN chmod +x entrypoint.sh

ENTRYPOINT [ "./entrypoint.sh" ]

# CMD java -cp h2-2.2.222.jar org.h2.tools.RunScript -url "jdbc:h2:tcp://rgr-h2:9092/~/test" -user sa -script build_schema.sql