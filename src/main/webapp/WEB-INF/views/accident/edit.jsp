<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>
<div class="col-md-6 col-lg-4 offset-lg-4 offset-md-3 mt-5">
    <div class="bg-light p-5 border shadow">
        <h4 class="text-center mb-3">EDIT THE ACCIDENT</h4>
        <form action="<c:url value='/edit'/>" method="POST">
            <input type="hidden" name="id" value="${accident.id}">
            <div class="mb-4">
                <input type="text" class="form-control" value="${accident.name}" name="name" required>
            </div>
            <div class="mb-4">
                <input type="text" class="form-control" value="${accident.text}" name="text" required>
            </div>
            <div class="mb-4">
                <input type="text" class="form-control" value="${accident.address}" name="address" required>
            </div>
            <button type="submit" class="btn btn-primary w-100 my-3 shadow">Submit</button>
        </form>
    </div>
</div>
</body>
</html>