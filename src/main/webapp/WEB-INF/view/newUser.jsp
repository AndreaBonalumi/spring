<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 31/05/2023
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Nuovo utente</title>
</head>
<body>

<h1> Inserisci i dati del nuovo utente</h1> <br><br>

<form:form action="UserServlet?action=new" method="post">
  <form:input id="firstName" path="firstName" type="text" name="firstName" placeholder="First Name" /><br><br>
  <form:input id="lastName" path="lastName" type="text" name="lastName" placeholder="Last Name" /><br><br>
  <form:input id="username" path="username" type="text" name="username" placeholder="username" /><br><br>
  <form:input id="password" path="password" type="password" name="password" placeholder="Password" /><br><br>
  <form:input id="email" path="email" type="text" name="email" placeholder="Email" /><br><br>
  <form:input id="bd" path="bd" type="date" name="bd" placeholder="Birthday" /><br><br>
  <form:input id="nPatente" path="nPatente" type="text" name="nPatente" placeholder="Numero Patente" /><br><br>
  <input type="submit" value="Salva">
</form:form>

</body>
</html>
