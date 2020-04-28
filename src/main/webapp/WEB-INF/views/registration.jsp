<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>
<h3>Please enter new user data</h3>
<h4 style="color: red">${msg}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <table>
        <tr>
            <th style="text-align: left">name:</th>
            <th><input type="text" name="userName"></th>
        </tr>
        <tr>
            <th style="text-align: left">login:</th>
            <th><input type="text" name="login"></th>
        </tr>
        <tr>
            <th style="text-align: left">password:</th>
            <th><input type="password" name="pwd"></th>
        </tr>
        <tr>
            <th style="text-align: left">confirm pwd:</th>
            <th><input type="password" name="pwd-repeat"></th>
        </tr>
        <tr>
            <th></th>
            <th style="text-align: right"><button type="submit">Submit</button></th>
        </tr>
    </table>
</form>
</body>
</html>
