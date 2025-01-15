# Challenge-Restful-Calculator
Challenge of a Restful API Calculator for a calculator (add,sub,mul,div)

# Technologies
- Spring Boot
- Maven
- Restful API
- Kafka
- Docker

# Setup

## Pre-Requisites (Be sure to have all installed)
- Docker
- Java 17+
- Maven

## Build

1 - Clone the repository from gihub

    git clone https://github.com/Tiggs27/Challenge-Java.git

2 - Go to the current folder and enter in each module, and build them

    mvn clean package

3 - Go back to the parent folder and do the following Docker command

    docker compose up --build

4 - Verify if the application is running

    - By accessing in the browser:

        http://localhost:8080/calculator/add?a=5&b=10
    
    - By sending a get request

5 - Open a new terminal and write a new calculation

    - Send a request like this 

        curl "http://localhost:8080/calculator/sub?a=10&b=2"
    
6 - Open a new terminal and check the messages getting sent in kafka by executing this commands

    Access Kakfa container

        docker exec -it challengerestfulapi-kafka-1 bash

    Check the calculations being executed

        kafka-console-consumer.sh --topic calculate --bootstrap-server localhost:9092 --from-beginning

# Testing 

Go back to each module and do the following command to run the unit test:

    mvn test