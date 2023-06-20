<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 29/05/2023
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista Macchine</title>
</head>
<body>
<br>
<h1>Lista Macchine</h1>

<br>
<a href="manage/-1">Inserisci nuova auto</a>


<div class="container">
    <table class="table">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Marca</th>
            <th scope="col">Modello</th>
            <th scope="col">Colore</th>
            <th scope="col">Descrizione</th>
            <th scope="col">Link</th>
            <th scope="col">Azioni</th>
        </tr>
        <c:forEach var="macchina" items="${cars}">
            <tr onclick="">
                <th scope="row">${macchina.id}</th>
                <td>${macchina.brand}</td>
                <td>${macchina.model}</td>
                <td>${macchina.color}</td>
                <td>${macchina.description}</td>
                <td>${macchina.link}</td>
                <td>
                    <a href="manage/${macchina.id}"><button type="button">Modifica</button></a>
                    <a href="delete/${macchina.id}"><button onclick="window.alert('elemento cancellato')" type="button">Cancella</button></a>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>