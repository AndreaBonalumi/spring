<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 31/05/2023
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Nuova prenotazione</title>
</head>
<body>
<h1>Prenotazione</h1>

<form:form method="get" modelAttribute="booking">
    <label>Data di inizio della prenotazione </label>
    <form:input type="date" path="dateBookingStart" min="<%= java.time.LocalDate.now().plusDays(2) %>" max="${param.end}" /> <br><br>

    <label for="end">Data di fine della prenotazione </label>
    <form:input type="date" path="dateBookingEnd" name="end" min="${param.start}"/><br><br>
    <input type="submit" value="cerca auto disponibili">
</form:form><br><br>
<form:form method="post" modelAttribute="car">

    <c:if test="${carsDate != null}">
        <table>
            <thead>
            <tr>
                <th></th>
                <th>Marca</th>
                <th>Modello</th>
                <th>Colore</th>
                <th>Descrizione</th>
                <th>Link</th>
            </tr>
            </thead>

            <tbody>
                <c:forEach var="car" items="${carsDate}">
                    <tr>
                        <td>
                            <form:input type="radio" path="id" />
                        </td>
                        <td>${car.brand}</td>
                        <td>${car.model}</td>
                        <td>${car.color}</td>
                        <td>${car.description}</td>
                        <td>${car.link}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="submit" value="Prenota">
    </c:if>
</form:form>
</body>
</html>





<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    table th, table td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    table th {
        background-color: #f2f2f2;
    }

    table tr:hover {
        background-color: #f5f5f5;
    }

    table td {
        font-size: 14px;
    }

</style>