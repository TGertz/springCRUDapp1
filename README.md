![image](https://user-images.githubusercontent.com/45018986/153750706-dd93cd4c-f785-43b6-a327-d1eba0e90f66.png)

# Project Name: springCRUDapp1

Requirements IntelliJ IDEA Ultimate

Runs on Tomcat Server 9.0.58  Port: localhost:3030

Entry point URL: http://localhost:3030/people


# Using Relational PostgreDB 

DB name: first_db -> table: person -> columns: id(integer), name(varchar), age(integer), email(varchar) 

Source Driver: org.postgresql.Driver

Data Source url: jdbc:postgresql://localhost:5432/first_db

Username: postgres

Password: password


# About the App: 

SpringMVC application represents parameters from stored in DB persons table and allows to perform CRUD operations. 

HTTP REST pattern.

DAO(Data Access Object) pattern.

pure JDBC API and Jdbc Template options.

