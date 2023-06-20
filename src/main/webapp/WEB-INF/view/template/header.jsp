<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 29/05/2023
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page session="true" %>
<html>
<head>
  <title><tiles:insertAttribute name="titolo" /></title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.0/css/bootstrap.min.css">




</head>
<body>
<nav>
  <div class="topnav">

    <a href="/springExample_war_exploded/home">Home</a>
    <c:if test="${sessionScope.userLogger.admin}">
      <a href="/springExample_war_exploded/car/all">Parco auto</a>
    </c:if>
    <a href="/springExample_war_exploded/user/profile">Profilo utente</a>

  </div>
</nav>

<tiles:insertAttribute name="content" />

<script src="/webjars/bootstrap/5.1.0/js/bootstrap.min.js"></script>

</body>
</html>

<style>
  .topnav {
    background-color: #333;
    overflow: hidden;
  }

  .topnav a {
    float: left;
    color: #f2f2f2;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    font-size: 17px;
  }

  .topnav a:hover {
    background-color: #ddd;
    color: black;
  }
</style>