<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/17/2021
  Time: 3:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>ADD NEW BOOK</h1>
<h2><a href="/books">BACK BOOK MANAGER</a></h2>
<form method="post">
    <table border="1" cellpadding="5">
        <tr>
            <td>NAME</td>
            <td> <input type="text" name="name" placeholder="Nhập tên sách"></td>
        </tr>
        <tr>
            <td>DESCRIPTION</td>
            <td> <input type="text" name="description" placeholder="Nhập mô tả"></td>
        </tr>
        <tr>
            <td>CATEGORY</td>
            <td>
                <select name="category">
                    <c:forEach items="${categoryList}" var="cate">
                        <option value="${cate.id}">${cate.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td> <input type="submit" value="Create"></td>
        </tr>
    </table>
</form>

<h3>
    <c:if test="${message !=null}">
        <span>${message}</span>
    </c:if>
</h3>

</body>
</html>
