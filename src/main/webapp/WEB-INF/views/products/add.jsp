<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../includes/header.jsp"></jsp:include>
    <jsp:include page="../includes/footer.jsp"></jsp:include>
    <title>Add product</title>
</head>
<body>
<div class="container">
    <main role="main">

        <br>
        <br>
        <h1 class="h1-responsive my-5">Add product</h1>

        <h4 class="h4-responsive my-5">Please enter new product data</h4>
        <form method="post" action="${pageContext.request.contextPath}/products/add">
            <div class="form-group">
                <label>Name</label>
                <input type="text" name="productName" class="form-control col-4 mx-sm-3"
                       id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Name">
                <small class="form-text text-muted">Please provide simple product name.</small>
            </div>
            <div class="form-group">
                <label>Price</label>
                <input type="number" class="form-control mx-sm-3 col-4" placeholder="0.00"
                       name="price">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
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
