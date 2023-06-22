<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 21/06/2023
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
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
  <c:url var="loginUrl" value="/home" />

  <form action="${loginUrl}" method="post">

    <c:if test="${param.fail != null}">
      <div class="alert alert-danger">
        <p>Credenziali sbagliate</p>
      </div>
    </c:if>

    <c:if test="${param.authDecline != null}">
      <div class="alert alert-danger">
        <p>Non possiedi le autorizzazioni per accedere a questa pagina, <br> accedi con un utente diverso</p>
      </div>
    </c:if>

    <c:if test="${param.logout != null}">
      <div class="alert alert-success">
        <p>Logout effettuato con successo</p>
      </div>
    </c:if>

    <div class="form-group">
      <label for="username">Username: </label>
      <input id="username" type="text" name="username" placeholder="username" required />
    </div>
    <br>
    <div class="form-group">
      <label for="password">Password: </label>
      <input id="password" type="password" name="password" placeholder="password" required />
    </div>
    <div class="form-group">
      <label>
        <input id="remember" type="checkbox" name="remember" />Ricordami
      </label>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

    <input class="btn btn-primary" type="submit" value="Login">

  </form>
</div>

<script src="/webjars/bootstrap/5.1.0/js/bootstrap.min.js"></script>
</body>
</html>
