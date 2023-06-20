<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 01/06/2023
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Prenotazioni</title>
</head>
<body>


<h1>Prenotazioni di ${userBooking.firstName}</h1>
<div class="container">
    <c:if test="${bookings != null}">
        <table class="table">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Data inizio</th>
                <th scope="col">Data fine</th>
                <th scope="col">Marca</th>
                <th scope="col">Modello</th>
                <th scope="col">Stato</th>
                <th scope="col">Azioni</th>
            </tr>
            <c:forEach var="tempbooking" items="${bookings}">
                <tr>
                    <th scope="row">${tempbooking.id}</th>
                    <td>${tempbooking.dateBookingStart}</td>
                    <td>${tempbooking.dateBookingEnd}</td>
                    <td>${tempbooking.car.brand}</td>
                    <td>${tempbooking.car.model}</td>
                    <td>${tempbooking.status}</td>
                    <td>
                        <a href="${userBooking.id}/approve/${tempbooking.id}">
                            <button class="btn btn-primary" type="button" onclick="window.alert('prenotazione approvata')">
                                Approva</button> </a>
                        <a href="${userBooking.id}/decline/${tempbooking.id}">
                            <button class="btn btn-danger" type="button" onclick="window.alert('prenotazione rifiutata')">
                                Rifiuta</button> </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>