# Keukentafelmeta

## Table of contents
* [General information](https://github.com/TheFluyter/keukentafelmeta#general-information)
* [Technologies](https://github.com/TheFluyter/keukentafelmeta#technologies)
* [Launch](https://github.com/TheFluyter/keukentafelmeta#launch)
* [Features](https://github.com/TheFluyter/keukentafelmeta#features)
* [Authors](https://github.com/TheFluyter/keukentafelmeta#authors)

## General information
An web application for looking up Magic: The Gathering cards, make your own decklists and see decklists of friends.

## Technologies
* Java

## Launch
This project is created with
* Scryfall API (https://scryfall.com/docs/api)

## Features (in progress)
* Make an account
* Look up Magic: the Gathering cards by name
* Make your own decklist

## Local
This application can be build by running the command: ```mvn clean install```<br>
This application can be start by running the command: ```mvn spring-boot:run```<br>

The application uses an in memory database (H2) for testing purposes. 
When running the application locally you can access the H2 databases as follows:

Go to http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:keukentafelmeta
User Name: overlord
Password: keukentafelmeta

On application start the data.sql (resources/data.sql) file will be loaded automatically in the H2 databse
Tables are created automatically based on the User.java class.

By design, the in-memory database is volatile, and data will be lost when we restart the application.
This behaviour could be changed to file based if needed.


## Authors
Back-end: Colin de Bruin, Rick de Ruiter
Front-end: Tatsuya Kaneko
