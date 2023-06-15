<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 01/06/2023
  Time: 09:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Modifica</title>
</head>
<body>
<h1>Modifica dati:</h1>

<form:form method="post" modelAttribute="userEditRequest">
  <form:input id="firstName" path="firstName" type="text" name="firstName" value="${userEditRequest.firstName}" /><br><br>
  <form:input id="lastName" path="lastName" type="text" name="lastName" value="${userEditRequest.lastName}" /><br><br>
  <form:input id="password" path="password" type="password" name="password" placeholder="password" /><br><br>
  <form:input id="nPatente" path="nPatente" type="text" name="nPatente" value="${userEditRequest.nPatente}" /><br><br>
  <input type="submit" value="Salva" />
</form:form>

</body>
</html>
