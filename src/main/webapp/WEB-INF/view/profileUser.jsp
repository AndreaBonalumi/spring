<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 30/05/2023
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
    <title>Profilo Utente</title>
</head>
<body>
<br><br>
<h1>Dati</h1> <br><br>
<div class="container">
    <div class="form-group">
        <p>Nome: ${user.firstName}</p> <br>
    </div>
    <div class="form-group">
        <p>Cognome: ${user.lastName}</p> <br>
    </div>
    <div class="form-group">
        <p>Data di nascita: ${user.birthday}</p> <br></div>
    <div class="form-group">
        <p>Email: ${user.email}</p><br>
    </div>
    <div class="form-group">
        <p>Username: ${user.username}</p><br>
    </div>
    <div class="form-group">
        <p>Numero patente: ${user.nPatente}</p><br>
    </div>

    <a href="manage/${user.id}"><button type="button">Modifica i tuoi dati</button></a>
</div>
</body>
</html>
