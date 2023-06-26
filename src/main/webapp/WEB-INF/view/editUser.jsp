<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 01/06/2023
  Time: 09:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Modifica</title>
</head>
<body>
<sec:authorize access="@userServiceImpl.checkUserId(authentication, ${id}) or hasRole('ADMIN')">
  <h1>Modifica dati:</h1>
  <div class="container">
    <form:form method="post" modelAttribute="userRequest">

      <form:input path="id" hidden="true"  />
      <div class="form-group">
      <span>Nome: </span> <form:input path="firstName" placeholder="Nome" /></div><br><br>
      <div class="form-group">
      <span>Cognome: </span> <form:input path="lastName" placeholder="Cognome" /></div><br><br>
      <div class="form-group">
      <span>Username: </span> <form:input path="username" placeholder="username" /></div><br><br>
      <div class="form-group">
      <span>Password: </span> <form:password path="password" placeholder="password" /></div><br><br>
      <div class="form-group">
      <span>Data di nascita: </span> <form:input path="birthday" type="date" /></div><br><br>
      <div class="form-group">
      <span>Numero Patente: </span> <form:input path="nPatente" /></div><br><br>
      <form:errors path="firstName" cssClass="error" /><br>
      <form:errors path="lastName" cssClass="error" /><br>
      <form:errors path="password" cssClass="error" /><br>
      <form:errors path="nPatente" cssClass="error" /><br>
      <input class="btn btn-lg btn-primary" type="submit" value="Salva" />

    </form:form>
  </div>
</sec:authorize>
<sec:authorize access="!(@userServiceImpl.checkUserId(authentication, ${id}) or hasRole('ADMIN'))">
  <p class="alert alert-danger">Non possiedi le autorizzazioni per effettuare questa operazione</p>
</sec:authorize>
</body>
</html>
