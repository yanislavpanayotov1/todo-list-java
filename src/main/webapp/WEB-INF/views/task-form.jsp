<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="common/header.jsp"/>

<h2>${task.id == null ? 'Create Task' : 'Edit Task'}</h2>
<form action="task-form" method="post">
    <input type="hidden" name="id" value="${task.id}">
    <p>
        <label for="title">Title:</label><br>
        <input type="text" id="title" name="title" value="${task.title}" required>
    </p>
    <p>
        <label for="description">Description:</label><br>
        <textarea id="description" name="description" rows="3">${task.description}</textarea>
    </p>
    <p>
        <label for="dueDate">Due Date:</label><br>
        <input type="datetime-local" id="dueDate" name="dueDate" value="${task.dueDate != null ? task.dueDate.toString().substring(0, 16) : ''}" required>
    </p>
    <p>
        <label>
            <input type="checkbox" name="completed" ${task.completed ? 'checked' : ''}>
            Completed
        </label>
    </p>
    <p>
        <button type="submit">Save</button>
        <a href="tasks">Cancel</a>
    </p>
</form>

<jsp:include page="common/footer.jsp"/> 