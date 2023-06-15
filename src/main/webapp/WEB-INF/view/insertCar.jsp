<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 29/05/2023
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>inserisci macchina</title>
</head>
<body>
<br>
<h1>Inserisci i dati della macchina</h1>

<form:form method="post" modelAttribute="newCarRequest">

    <form:input id="brand" path="brand" type="text" name="brand" placeholder="Lamborghini" required="true" /><br><br>
    <form:input id="model" path="model" type="text" name="model" placeholder="Huracan" required="true" /><br><br>
    <form:input id="color" path="color" type="text" name="color" placeholder="Blue and orange" required="true" /><br><br>
    <form:input id="description" path="" type="text" name="description" placeholder="Es. cilindrata, cavalli ecc." /><br><br>
    <form:input id="link" path="link" type="text" name="link" placeholder="Link" /><br><br>
    <form:input id="year" path="year" type="text" name="year" placeholder="Year" required="true" /><br><br>

    <input type="submit" value="Inserisci"><br>
</form:form>
</body>
</html>
