<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="includes/header.jsp"></jsp:include>
    <jsp:include page="includes/footer.jsp"></jsp:include>
    <title>Shopping cart</title>
</head>
<body>
<div class="container">
    <main role="main">
        <br>
        <br>
        <h1 class="h1-responsive my-5">Shopping Cart</h1>

        <table class="mt-5 table table-striped table-bordered" cellspacing="0" width="100%">
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
                    <td>
                        <a class="btn btn-lg btn-danger"
                           href="${pageContext.request.contextPath}/shoppingCart/products/delete?product_id=${product.productId}"
                           role="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:choose>
            <c:when test="${products_available == 1}">
                <a class="btn btn-lg btn-success"
                   href="${pageContext.request.contextPath}/orders/new"
                   role="button">Order</a>
            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>
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
