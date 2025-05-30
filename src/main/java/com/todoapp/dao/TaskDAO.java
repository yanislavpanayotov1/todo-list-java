package com.todoapp.dao;

import com.todoapp.model.Task;
import com.todoapp.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    
    public Task create(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, due_date, created_at, completed) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setTimestamp(3, Timestamp.valueOf(task.getDueDate()));
            pstmt.setTimestamp(4, Timestamp.valueOf(task.getCreatedAt()));
            pstmt.setBoolean(5, task.isCompleted());
            
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    task.setId(rs.getLong(1));
                }
            }
            
            return task;
        }
    }
    
    public List<Task> findAll() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
                task.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                task.setCompleted(rs.getBoolean("completed"));
                tasks.add(task);
            }
        }
        
        return tasks;
    }
    
    public Task findById(Long id) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Task task = new Task();
                    task.setId(rs.getLong("id"));
                    task.setTitle(rs.getString("title"));
                    task.setDescription(rs.getString("description"));
                    task.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());
                    task.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    task.setCompleted(rs.getBoolean("completed"));
                    return task;
                }
            }
        }
        
        return null;
    }
    
    public void update(Task task) throws SQLException {
        String sql = "UPDATE tasks SET title = ?, description = ?, due_date = ?, completed = ? WHERE id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setTimestamp(3, Timestamp.valueOf(task.getDueDate()));
            pstmt.setBoolean(4, task.isCompleted());
            pstmt.setLong(5, task.getId());
            
            pstmt.executeUpdate();
        }
    }
    
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
} 