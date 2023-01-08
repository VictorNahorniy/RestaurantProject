<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<html>
<head>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="/RestaurantProject/HomeServlet">Restaurant</a>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav ml-auto">

            <c:set var="client" scope="session" value="${user}"/>
            <c:choose>
                <c:when test="${client != null}">
                    <li class="nav-item">
                        <a class="nav-link">
                            <fmt:message key="navbar.greeting"/><custom:UsernameTag surname="${client.getLastName()}"
                                                                                     name="${client.getFirstName()}"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/RestaurantProject/HomeServlet"><fmt:message key="navbar.home"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout"><fmt:message key="navbar.logout"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/RestaurantProject/profile"><fmt:message key="navbar.profile"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="/RestaurantProject/HomeServlet"><fmt:message key="navbar.home"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp"><fmt:message key="navbar.login"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="registration.jsp"><fmt:message key="navbar.sign_up"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
            <li class="nav-item">
                <a class="nav-link" href="cart.jsp"> <fmt:message key="navbar.cart"/>
                    <span class="badge badge-pill badge-danger">
                        ${orderDish != null ? orderDish.getDishMap().size() : 0}
                    </span>
                </a>
            </li>
        </ul>
        <ul>

            <li><a style="color: whitesmoke" href="?sessionLocale=en"><fmt:message key="navbar.lang.en"/></a></li>

            <li><a style="color: whitesmoke" href="?sessionLocale=ukr"><fmt:message key="navbar.lang.ukr"/></a></li>

        </ul>
    </div>
    <%@include file="footer.jsp" %>
</nav>
</body>
</html>