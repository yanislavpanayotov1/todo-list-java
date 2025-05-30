# Java To-Do List Web Application

## Purpose
This is a simple Java web application for managing a to-do list. It allows users to add, view, edit, and delete tasks using a web interface. The app is designed for educational purposes and demonstrates the use of Servlets, JSP, and an H2 in-memory database.

## How to Run the Project
1. **Build the project:**
   ```sh
   mvn clean install
   ```
2. **Deploy the WAR file:**
   Copy the generated `target/todo-list-1.0-SNAPSHOT.war` to your Apache Tomcat 9 `webapps` directory.
3. **Start Tomcat:**
   ```sh
   brew services start tomcat@9
   ```
4. **Access the app:**
   Open your browser and go to [http://localhost:8080/todo-list-1.0-SNAPSHOT/](http://localhost:8080/todo-list-1.0-SNAPSHOT/)

## Key Features
- Add new tasks with title, description, due date, and completion status
- View a list of all tasks
- Edit existing tasks
- Delete tasks
- Simple, clean interface using plain HTML and Bootstrap classes
- Data stored in an H2 in-memory database (resets on server restart)

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── todoapp/
│   │           ├── dao/         # Data Access Objects
│   │           ├── model/       # Entity classes
│   │           ├── servlet/     # Servlet classes
│   │           └── util/        # Utility classes
│   ├── resources/
│   │   └── database/           # Database scripts
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml         # Web application configuration
│       └── views/              # JSP files
└── test/                       # Test classes
```

## Database

The application uses H2 in-memory database. The schema is automatically created when the application starts.

## Contributing

Feel free to submit issues and enhancement requests. 