<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Management</title>
</head>
<body>
<h1>Product Management</h1>
<h3><a href="${pageContext.request.contextPath}/">index</a></h3>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.productId}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td style="text-align: center">
                <button onclick="location.href = '${pageContext.request.contextPath}/products/delete?product_id=${product.productId}'">delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<button onclick="location.href = '${pageContext.request.contextPath}/products/add'">New product</button>
</body>
</html>
