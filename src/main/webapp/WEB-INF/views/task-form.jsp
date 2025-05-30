<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="common/header.jsp"/>

<div class="container mt-4">
    <h1>${task.id == null ? 'Create Task' : 'Edit Task'}</h1>
    
    <form action="task-form" method="post" class="mt-4">
        <input type="hidden" name="id" value="${task.id}">
        
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" value="${task.title}" required>
        </div>
        
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="3">${task.description}</textarea>
        </div>
        
        <div class="form-group">
            <label for="dueDate">Due Date:</label>
            <input type="datetime-local" id="dueDate" name="dueDate" 
                   value="${task.dueDate != null ? task.dueDate.toString().substring(0, 16) : ''}" required>
        </div>
        
        <div class="form-group">
            <label>
                <input type="checkbox" name="completed" ${task.completed ? 'checked' : ''}>
                Completed
            </label>
        </div>
        
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="tasks" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<jsp:include page="common/footer.jsp"/> 