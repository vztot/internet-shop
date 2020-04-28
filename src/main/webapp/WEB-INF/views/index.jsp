<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<h1>internet-shop</h1>
<h3>HW 16.2:</h3>
<ul>
    <li><a href="${pageContext.request.contextPath}/injectFakeData?inject=true">Inject data</a></li>
    <br>
    <li><a href="${pageContext.request.contextPath}/registration">Register new user</a></li>
    <li><a href="${pageContext.request.contextPath}/users/all">All users</a></li>
    <br>
    <li><a href="${pageContext.request.contextPath}/products/add">Add product</a></li>
    <li><a href="${pageContext.request.contextPath}/products/all">All products</a></li>
    <li><a href="${pageContext.request.contextPath}/products/management">Product Management</a></li>
    <br>
    <li><a href="${pageContext.request.contextPath}/shoppingCart">View shoppingCart of User with 1L Id</a></li>
    <br>
    <li><a href="${pageContext.request.contextPath}/orders/all">All orders</a></li>
</ul>
</body>
</html>
