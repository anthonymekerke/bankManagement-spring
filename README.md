# BankManagement

## Description

### Ports

| Service | Port |
|---|---|
| Config Server | 8888 |
| Naming Server | 8761 |
| Account Service | 8080 |

### Initializr

Project: Maven  
Language: Java  
Spring Boot: 2.7.9  
GroupId: bankManagement  
Java: 11  
Packaging: jar  

dependencies: devtools, actuator  

### Services

spring-cloud-config-server: handle configurations files for services  
git-local-config-repo: git repository storing configuration file  
naming-server: service registry of the application  