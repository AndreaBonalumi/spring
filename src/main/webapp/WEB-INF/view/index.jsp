<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form:form action="" method="post" modelAttribute="loginRequest">
    <form:input id="username" type="text" name="username" path="username"/> <br><br>
    <form:input id="password" type="password" name="password" path="password"/><br><br>
    <input type="submit" value="Entra">
</form:form>
<c:if test="${error != null}">
    <p style="color: red">${error}</p>
</c:if>
</body>
</html>