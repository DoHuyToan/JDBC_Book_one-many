<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/17/2021
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>BOOK MANAGER</h1>
<h3><a href="/books?action=create" style="color: red">CREATE NEW BOOK</a></h3>
<table border="1" cellpadding="5">
    <tr>
        <td><a href="/books?action=sort">NAME</a></td>
        <td>DESCRIPTION</td>
        <td>CATEGORY</td>
        <td>EDIT</td>
        <td>DELETE</td>
    </tr>
    <c:forEach items="${bookList}" var="book">
        <tr>
            <td> <a href="/books?action=view&id=${book.id}">${book.name}</a> </td>
            <td>${book.description}</td>
            <td>${book.getCategory().name}</td>
            <td><a href="/books?action=edit&id=${book.id}">EDIT</a></td>
            <td><a href="/books?action=delete&id=${book.id}">DELETE</a></td>
        </tr>
        </c:forEach>
</table>
</body>
</html>
