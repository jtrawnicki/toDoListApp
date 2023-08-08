# ToDoListApp

This is my first WEB application project, in which I focus mainly on the backend (Java, Spring, Hibernate, PostgreSQL), but I also take care of the frontend using Bootstrap,Thymeleaf, HTML and CSS.I am constantly developing the project with new functionalities that I am learning. The app allows you to create, edit and delete tasks to do and assign them to different categories.

# Screen Shots

* Main menu:
    
![appMain](https://github.com/jtrawnicki/toDoListApp/assets/118732841/74f59ea1-30f1-4275-8b69-2bf289be56a4)

* Creating new task:

![addTask](https://github.com/jtrawnicki/toDoListApp/assets/118732841/69ffd27d-ca50-4ba2-a285-c36bc8a6c26f)

* List of tasks:

![taskList](https://github.com/jtrawnicki/toDoListApp/assets/118732841/4fb51430-0b68-44fb-8ebc-0b5f06b8fb5d)

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






