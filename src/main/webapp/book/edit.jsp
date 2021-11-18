<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/17/2021
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2><a href="/books">RETURN BOOK MANAGER</a></h2>
<h1>EDIT BOOK</h1>
<form method="post">
    <table>
        <tr>
            <td>NAME</td>
            <td> <input type="text" name="name" value="${book.name}"> </td>
        </tr>
        <tr>
            <td>DESCRIPTION</td>
            <td> <input type="text" name="description" value="${book.description}"> </td>
        </tr>
        <tr>
            <td>Category</td>
            <td>
                <select name="category">
                    <c:forEach items="${categoryList}" var="cas">
                            <option value="${cas.id}">${cas.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td> <input type="submit" value="Edit"> </td>
        </tr>
    </table>
</form>
<h3 >
    <c:if test="${message !=null}">
        <span>${message}</span>
    </c:if>
</h3>
</body>
</html>
