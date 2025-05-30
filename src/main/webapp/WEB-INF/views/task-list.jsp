<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="common/header.jsp"/>

<div class="container mt-4">
    <h1>Task List</h1>
    <a href="task-form" class="btn btn-primary mb-3">Add New Task</a>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Due Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tasks}" var="task">
                <tr>
                    <td>${task.title}</td>
                    <td>${task.description}</td>
                    <td>
                        <fmt:parseDate value="${task.dueDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                        <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm"/>
                    </td>
                    <td>${task.completed ? 'Completed' : 'Pending'}</td>
                    <td>
                        <a href="task-form?id=${task.id}" class="btn btn-sm btn-warning">Edit</a>
                        <a href="task-delete?id=${task.id}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="common/footer.jsp"/> 