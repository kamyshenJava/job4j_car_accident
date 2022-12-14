<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
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
                <a class="nav-link" href="<c:url value="/logout"/>">
                    <span>${user.username} </span>
                    <span>| Log out</span>
                </a>
            </div>
        </div>
    </div>
</nav>

<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Text</th>
        <th scope="col">Address</th>
        <th scope="col">Type</th>
        <th scope="col">Rules</th>
        <th scope="col">Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="accident" varStatus="i" items="${accidents}">
        <tr>
            <th scope="row">${i.count}</th>
            <td>${accident.name}</td>
            <td>${accident.text}</td>
            <td>${accident.address}</td>
            <td>${accident.type.name}</td>
            <td>
                <c:forEach varStatus="loop" var="rule" items="${accident.rules}">
                    <span>${rule.name}<c:if test="${not loop.last}">,</c:if>
                    </span>
                </c:forEach>
            </td>
            <td><a class="btn btn-outline-success btn-sm mx-auto" href="<c:url value="/edit?id=${accident.id}"/>">
                Edit
            </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a class="btn btn-primary ms-3 mt-3" href="<c:url value="/create"/>">Add accident</a>
</div>
</body>
</html>