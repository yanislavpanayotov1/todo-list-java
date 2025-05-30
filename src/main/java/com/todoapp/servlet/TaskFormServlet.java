package com.todoapp.servlet;

import com.todoapp.dao.TaskDAO;
import com.todoapp.model.Task;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/task-form")
public class TaskFormServlet extends HttpServlet {
    private TaskDAO taskDAO;

    @Override
    public void init() {
        taskDAO = new TaskDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            try {
                Task task = taskDAO.findById(Long.parseLong(id));
                if (task != null) {
                    request.setAttribute("task", task);
                }
            } catch (SQLException e) {
                throw new ServletException("Error retrieving task", e);
            }
        }
        request.getRequestDispatcher("/WEB-INF/views/task-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dueDateStr = request.getParameter("dueDate");
        String completed = request.getParameter("completed");

        try {
            Task task;
            if (id != null && !id.isEmpty()) {
                task = taskDAO.findById(Long.parseLong(id));
                if (task == null) {
                    throw new ServletException("Task not found");
                }
            } else {
                task = new Task();
            }

            task.setTitle(title);
            task.setDescription(description);
            task.setDueDate(LocalDateTime.parse(dueDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            task.setCompleted(completed != null);

            if (id != null && !id.isEmpty()) {
                taskDAO.update(task);
            } else {
                taskDAO.create(task);
            }

            response.sendRedirect(request.getContextPath() + "/tasks");
        } catch (SQLException e) {
            throw new ServletException("Error saving task", e);
        }
    }
} 