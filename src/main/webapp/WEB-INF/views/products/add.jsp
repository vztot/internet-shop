<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<h1>Add product</h1>
<h3><a href="${pageContext.request.contextPath}/">index</a></h3>
<h4>Please enter new product data</h4>
<form method="post" action="${pageContext.request.contextPath}/products/add">
    <table>
        <tr>
            <th style="text-align: left">name:</th>
            <th><input type="text" name="productName"></th>
        </tr>
        <tr>
            <th style="text-align: left">price:</th>
            <th><input type="number" name="price" required></th>
        </tr>
        <tr>
            <th></th>
            <th style="text-align: right"><button type="submit">Add</button></th>
        </tr>
    </table>
</form>
</body>
</html>
