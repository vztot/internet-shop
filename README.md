# Internet-shop [![Build Status](https://travis-ci.com/vztot/internet-shop.svg?branch=master)](https://travis-ci.com/vztot/internet-shop)

![gif](doc/img/sign-in.gif)

## Table of Contents

* [Project purpose](#project-purpose)
* [Project structure](#project-structure)
* [For developer](#for-developer)
* [Author](#author)

## Project purpose

This is a template for creating an e-store. It implements typical functions for an online store. It has login and registration forms.

Available functions for all users:
* registration
* log in
* log out

Available functions for users with a USER role only:
* add to user's bucket
* delete from user's bucket
* view all user's orders
* complete order
* view a lists of selected items in user`s bucket

Available functions for users with an ADMIN role only:
* add items to the store
* delete items from the store
* view a list of all users
* delete users from the store

## Project structure

* Java 11
* Maven 4.0.0
* Servlet API 3.1.0
* JSTL 1.2
* Log4j 1.2.17
* Maven Checkstyle Plugin
* Mysql Connector Java 8.0.18
* SonarQube 1.2
* Bootstrap

## For developer

Open the project in your IDE (add it as maven project).

Configure Tomcat:
* add artifact
* add SDK 11 or above

Add SDK 11 or above in project structure.

Add lombok plugin to your IDE.

Install MySQL.

Use file /jv-internet-shop/src/main/resources/init_db.sql to create the schema and all the tables required by this app in MySQL database.

At /jv-internet-shop/src/main/java/mate/academy/internetshop/util/ConnectionUtil.java class use username and password for your DB to create a Connection.

Change a path in /jv-internet-shop/src/main/resources/log4j.properties. It has to reach your logFile.

Configure SonarQube:
* install gem
* run "gem install travis"
* add github project to sonarcloud.io and choose travis in "analysis method"
* generate proper token via "travis encrypt --pro [given token on sonarcloud.io]"
* add generated token via travis to ".travis.yml" file

Run the project.

If you first time launch this project: register new user and run InjectDefaultUsersController by URL = .../internet_shop_war_exploded/injectFakeData to create default users and products.

## Author

[Oleksandr Husenko](https://github.com/vztot)
