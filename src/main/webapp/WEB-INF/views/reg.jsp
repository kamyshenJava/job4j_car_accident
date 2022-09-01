<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Car accidents</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <div class="navbar-brand me-2">
                Accidents
            </div>
            <div class="d-flex justify-content-end" id="navbarButtonsExample">
                <div class="d-flex align-items-center">
                    <a class="nav-link" href="<c:url value="/login"/>">
                        <span>Log in</span>
                    </a>
                </div>
            </div>
        </div>
    </nav>
    <c:if test="${not empty errorMessage}">
        <div class="container-fluid-nav text-center mt-5" style="color:red; font-weight: bold">
                ${errorMessage}
        </div>
    </c:if>
    <div class="col-md-6 col-lg-4 offset-lg-4 offset-md-3 mt-5">
        <div class="bg-light p-5 border shadow">
            <h4 class="text-center mb-3">SIGN IN</h4>
            <form name='signin' action="<c:url value='/reg'/>" method="POST">
                <div class="mb-4">
                    <input type="text" class="form-control" placeholder="Username" name="username" required>
                </div>
                <div class="mb-4">
                    <input type="password" class="form-control" placeholder="Enter Password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary w-100 my-3 shadow">Sign in</button>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>
