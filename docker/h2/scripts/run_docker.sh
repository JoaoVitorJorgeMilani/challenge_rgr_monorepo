docker network create rgr-network
docker run -d -p 8082:8082 -p 9092:9092 --name rgr-h2 --network rgr-network rgr-h2-img
docker exec rgr-h2 java -cp /opt/h2/bin/h2-2.2.222.jar org.h2.tools.RunScript -url "jdbc:h2:tcp://localhost/~/test" -user sa -script /opt/h2/prepare/build_schema.sql