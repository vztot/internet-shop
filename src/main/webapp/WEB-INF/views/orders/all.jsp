<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../includes/header.jsp"></jsp:include>
    <jsp:include page="../includes/footer.jsp"></jsp:include>
    <title>My Orders</title>
</head>
<body>
<div class="container">
    <main role="main">

        <br>
        <br>
        <h1 class="h1-responsive my-5">My Orders</h1>

        <table class="mt-5 table table-striped table-bordered" cellspacing="0" width="100%">
            <tr>
                <th>Id</th>
                <th>UserId</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>
                        <c:out value="${order.orderId}"/>
                    </td>
                    <td>
                        <c:out value="${order.userId}"/>
                    </td>
                    <td>
                        <a class="btn btn-lg btn-primary"
                           href="${pageContext.request.contextPath}/orders/view?order_id=${order.orderId}"
                           role="button">View</a>
                        <a class="btn btn-lg btn-danger"
                           href="${pageContext.request.contextPath}/orders/delete?order_id=${order.orderId}"
                           role="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </main>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"><\/script>')</script>
<script src="https://getbootstrap.com/docs/4.0/assets/js/vendor/popper.min.js"></script>
<script src="https://getbootstrap.com/docs/4.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
