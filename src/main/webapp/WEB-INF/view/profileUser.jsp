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

<p>Nome: ${user.firstName}</p> <br>
<p>Cognome: ${user.lastName}</p> <br>
<p>Data di nascita: ${user.birthday}</p> <br>
<p>Email: ${user.email}</p><br>
<p>Username: ${user.username}</p><br>
<p>Numero patente: ${user.nPatente}</p><br>


<a href="manage/${user.id}"><button type="button">Modifica i tuoi dati</button></a>
</body>
</html>
