<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<h3><a href="${pageContext.request.contextPath}/">index</a></h3>
<h3><a href="${pageContext.request.contextPath}/registration">registration</a></h3>
<h4 style="color: red">${errorMsg}</h4>
<form method="post" action="${pageContext.request.contextPath}/login">
    <table>
        <tr>
            <th style="text-align: left">login:</th>
            <th><input type="text" name="login"></th>
        </tr>
        <tr>
            <th style="text-align: left">password:</th>
            <th><input type="password" name="pwd"></th>
        </tr>
        <tr>
            <th></th>
            <th style="text-align: right"><button type="submit">Login</button></th>
        </tr>
    </table>
</form>
</body>
</html>
