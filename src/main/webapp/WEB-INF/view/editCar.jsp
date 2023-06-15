<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 30/05/2023
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Modifica</title>
</head>
<body>
<h1>Modifica ${carEditRequest.brand} ${carEditRequest.model}</h1><br>

<form:form method="post" modelAttribute="carEditRequest">

    <span>Marca: ${carEditRequest.brand}</span><br><br>
    <span>Modello: ${carEditRequest.model}</span><br><br>
    <span>Anno: ${carEditRequest.year}</span><br><br>
    <form:input id="color" path="color" type="text" name="color" placeholder="color" value="${car.color}" required="true" /><br><br>
    <form:input id="description" path="description" type="text" name="description" placeholder="description" value="${car.description}" /><br><br>
    <form:input id="link" path="link" type="text" name="link" placeholder="link" value="${car.link}" /><br><br>
    <input type="submit" value="Salva">
</form:form>
</body>
</html>
