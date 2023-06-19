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

<form:form action="/springExample_war_exploded/booking/completeBooking" method="get" modelAttribute="bookingRequest">
    <label for="start">Data di inizio della prenotazione </label>
    <form:input id="start" type="date" path="dateBookingStart" name="start"/> <br><br>

    <label for="end">Data di fine della prenotazione </label>
    <form:input id="end" type="date" path="dateBookingEnd" name="end"/><br><br>
    <input type="submit" value="cerca auto disponibili">
</form:form><br><br>
<c:if test="${cars != null}">

    <form:form method="post" modelAttribute="completeBooking">
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
                    <c:forEach var="car" items="${cars}">
                        <tr>
                            <td>
                                <form:radiobutton path="car" value="${car}" />
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
    </form:form>
</c:if>

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