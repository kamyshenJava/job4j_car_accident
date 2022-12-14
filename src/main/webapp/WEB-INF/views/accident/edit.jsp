<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
            <div class="mb-4">
                <select class="form-select" name="type.id" required>
                    <c:forEach var="type" items="${types}" >
                    <option value="${type.id}"  ${type.id == accident.type.id ? "selected" : ""}>${type.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-4">
                <select class="form-select" multiple name="rule.ids" required>
                    <c:forEach var="rule" items="${rules}" >
                        <spring:eval var="containsValue" expression="(accident.rules).contains(rule)" />
                        <option value="${rule.id}" ${containsValue ? "selected" : ""}>${rule.name}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary w-100 my-3 shadow">Submit</button>
        </form>
    </div>
</div>
</body>
</html>