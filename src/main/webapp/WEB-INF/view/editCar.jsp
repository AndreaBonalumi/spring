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
<div class="container">
    <form:form method="post" modelAttribute="carRequest">

        <form:input path="id" hidden="true" />

        <div class="from-group">
            <span>Marca: </span> <form:input path="brand" placeholder="color" /><br><br>
        </div>
        <div class="from-group">
            <span>Modello: </span> <form:input path="model" placeholder="color" /><br><br>
        </div>
        <div class="from-group">
            <span>Colore: </span> <form:input path="color" placeholder="color" /><br><br>
        </div>
        <div class="from-group">
            <span>Anno: </span> <form:input path="year" placeholder="color" /><br><br>
        </div>
        <div class="from-group">
            <span>Descrizione: </span> <form:input path="description" placeholder="description" /><br><br>
        </div>
        <div class="from-group">
            <span>Link: </span> <form:input path="link" placeholder="link" /><br><br>
        </div>
        <input class="btn btn-primary btn-lg" type="submit" value="Salva">

    </form:form>
</div>
</body>
</html>
