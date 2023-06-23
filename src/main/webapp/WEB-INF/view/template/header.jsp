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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
  <title><tiles:insertAttribute name="titolo" /></title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.0/css/bootstrap.min.css">

</head>

<body>
<nav class="navbar navbar-expand navbar-dark bg-dark">
  <div class="container">
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item active">
          <a class="nav-link" href="/springExample_war_exploded/home">Home</a>
        </li>
        <sec:authorize access="hasAuthority('ROLE_ADMIN')">
          <li class="nav-item">
            <a class="nav-link" href="/springExample_war_exploded/car/all">Parco auto</a>
          </li>
        </sec:authorize>
        <li class="nav-item">
          <a class="nav-link" href="/springExample_war_exploded/user/profile">Profilo</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<tiles:insertAttribute name="content" />

<script src="/webjars/bootstrap/5.1.0/js/bootstrap.min.js"></script>

</body>
</html>
