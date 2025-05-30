package com.todoapp.servlet;

import com.todoapp.dao.TaskDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/tasks")
public class TaskListServlet extends HttpServlet {
    private TaskDAO taskDAO;

    @Override
    public void init() {
        taskDAO = new TaskDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("tasks", taskDAO.findAll());
            request.getRequestDispatcher("/WEB-INF/views/task-list.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving tasks", e);
        }
    }
} 