<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form:form action="home" method="post" modelAttribute="loginRequest">
        <div class="form-group">
            <span>username: </span><form:input id="username" path="username"/> <br><br>
        </div>
        <div class="form-group">
            <span>password: </span><form:password id="password" path="password"/><br><br>
        </div>
        <input class="btn btn-lg btn-primary" type="submit" value="Entra">
    </form:form>
    <c:if test="${error != null}">
        <p style="color: red">${error}</p>
    </c:if>
</div>

<script src="/webjars/bootstrap/5.1.0/js/bootstrap.min.js"></script>
</body>
</html>