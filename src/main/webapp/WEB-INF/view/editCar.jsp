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
<h1>Modifica ${carRequest.brand} ${carRequest.model}</h1><br>

<form:form method="post" modelAttribute="carRequest">

    <form:input path="id" hidden="true" />
    <span>Marca: </span> <form:input path="brand" placeholder="color" /><br><br>
    <span>Modello: </span> <form:input path="model" placeholder="color" /><br><br>
    <span>Colore: </span> <form:input path="color" placeholder="color" /><br><br>
    <span>Anno: </span> <form:input path="year" placeholder="color" /><br><br>
    <span>Descrizione: </span> <form:input path="description" placeholder="description" /><br><br>
    <span>Link: </span> <form:input path="link" placeholder="link" /><br><br>
    <input type="submit" value="Salva">

</form:form>
</body>
</html>
