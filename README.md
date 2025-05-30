# To-Do List Java Web App

This is a simple to-do list app made with Java Servlets, JSP, and H2 database. You can add, edit, and delete tasks. The data is not saved forever (it resets when you restart the server).

## How to run
1. Build with:
   ```
   mvn clean install
   ```
2. Copy the WAR file from `target/` to your Tomcat 9 `webapps` folder.
3. Start Tomcat and go to [http://localhost:8080/todo-list-1.0-SNAPSHOT/](http://localhost:8080/todo-list-1.0-SNAPSHOT/)

## Features
- Add tasks
- Edit tasks
- Delete tasks
- See all tasks in a table

That's it. :) 