# ToDoListApp

This is my first WEB application project, in which I focus mainly on the backend (Java, Spring, Hibernate, PostgreSQL), but I also take care of the frontend using Bootstrap,Thymeleaf, HTML and CSS.I am constantly developing the project with new functionalities that I am learning.

# Setup
To run this project, please clone this repository and create a local copy on your computer.

After downloading project configure your database and db server in few step:

* Create database connection with Docker pasting this into command line:
  
  docker run --name postgresToDoList -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres

* Connect with server:
  
  Login: postgres
  
  Password: password

* Create database in server:
  
  create database todolist;
