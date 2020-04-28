<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All orders</title>
</head>
<body>
<h1>All orders</h1>
<h3><a href="${pageContext.request.contextPath}/">index</a></h3>
<table border="1">
    <tr>
        <th>Id</th>
        <th>User</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.orderId}"/>
            </td>
            <td>
                <c:out value="${order.user.name}"/>
            </td>
            <td style="text-align: center">
                <button onclick="location.href = '${pageContext.request.contextPath}/orders/view?order_id=${order.orderId}'">view</button>
                <button onclick="location.href = '${pageContext.request.contextPath}/orders/delete?order_id=${order.orderId}'">delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
