#!/bin/bash
java -cp h2-2.2.222.jar org.h2.tools.RunScript -url "jdbc:h2:tcp://rgr-h2:9092/~/test" -user sa -script build_schema.sql
