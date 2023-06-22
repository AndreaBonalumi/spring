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
<div class="container">
    <form:form action="/springExample_war_exploded/booking/completeBooking" method="get" modelAttribute="bookingRequest">

        <form:input path="id" hidden="true"/>

        <div class="form-group">
            <label for="start">Data di inizio della prenotazione </label>
            <form:input id="start" type="date" path="dateBookingStart" name="start"/> <br><br>
        </div>

        <div class="form-group">
            <label for="end">Data di fine della prenotazione </label>
            <form:input id="end" type="date" path="dateBookingEnd" name="end"/><br><br>
        </div>
        <input class="btn btn-primary btn-lg" type="submit" value="cerca auto disponibili">
    </form:form><br><br>
    <c:if test="${cars != null}">

        <form:form method="post" modelAttribute="booking">

            <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Marca</th>
                        <th scope="col">Modello</th>
                        <th scope="col">Colore</th>
                        <th scope="col">Descrizione</th>
                        <th scope="col">Link</th>
                    </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="car" items="${cars}">
                            <tr>
                                <th scope="row">
                                    <form:radiobutton path="car.id" value="${car.id}" />
                                </th>
                                <td>${car.brand}</td>
                                <td>${car.model}</td>
                                <td>${car.color}</td>
                                <td>${car.description}</td>
                                <td>${car.link}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <input class="btn btn-primary btn-lg" type="submit" value="Prenota">
        </form:form>
    </c:if>
</div>
</body>
</html>