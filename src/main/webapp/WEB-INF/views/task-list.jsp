<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="common/header.jsp"/>

<h2>Task List</h2>
<a href="task-form">Add New Task</a>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Due Date</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
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
                <a href="task-form?id=${task.id}">Edit</a> |
                <a href="task-delete?id=${task.id}" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="common/footer.jsp"/> 