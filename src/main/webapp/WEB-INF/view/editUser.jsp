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

<form:form method="post" modelAttribute="userRequest">

  <form:input path="id" hidden="true" />
  <span>Nome: </span> <form:input path="firstName" placeholder="Nome" /><br><br>
  <span>Cognome: </span> <form:input path="lastName" placeholder="Cognome" /><br><br>
  <span>Username: </span> <form:input path="username" placeholder="username" /><br><br>
  <span>Password: </span> <form:password path="password" placeholder="password" /><br><br>
  <span>Data di nascita: </span> <form:input path="birthday" type="date" /><br><br>
  <span>Numero Patente: </span> <form:input path="nPatente" /><br><br>
  <input type="submit" value="Salva" />

</form:form>

</body>
</html>
