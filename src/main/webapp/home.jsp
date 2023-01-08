<%--
  Created by IntelliJ IDEA.
  User: luckymen
  Date: 26.10.2022
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="includes/head.jsp" %>
</head>
<body>

<%@include file="includes/navbar.jsp" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="labels"/>
<div class="container mt-auto">
    <div class="card-header my-5"> <fmt:message key="home.menu"/></div>
    <div class="btn-group">
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                    data-bs-toggle="dropdown" aria-expanded="false">
                <fmt:message key="home.sortBy"/>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1" name="sortType">
                <li><a class="dropdown-item" href="sort?sortType=name"><fmt:message key="home.sortType.name"/> </a></li>
                <li><a class="dropdown-item" href="sort?sortType=price"><fmt:message key="home.sortType.price"/> </a></li>
                <li><a class="dropdown-item" href="sort?sortType=category"><fmt:message key="home.sortType.category"/></a>
                </li>
            </ul>
        </div>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle float-right" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
                <fmt:message key="home.category"/>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
                <li><a class="dropdown-item" href="category?category=Soup"><fmt:message key="home.category.soup"/></a></li>
                <li><a class="dropdown-item" href="category?category=Pizza"><fmt:message key="home.category.pizza"/></a></li>
                <li><a class="dropdown-item" href="category?category=Sushi"><fmt:message key="home.category.sushi"/></a></li>
                <li><a class="dropdown-item" href="category?category=Hot_meal"><fmt:message key="home.category.hot_meal"/></a></li>
                <li><a class="dropdown-item" href="category?category=Salad"><fmt:message key="home.category.salad"/></a></li>
                <li><a class="dropdown-item" href="category?category=Dessert"><fmt:message key="home.category.dessert"/></a></li>
                <li><a class="dropdown-item" href="category?category=Drink"><fmt:message key="home.category.drink"/></a></li>
                <li><a class="dropdown-item" href="category?category=All"><fmt:message key="home.category.all"/></a></li>
            </ul>
        </div>
    </div>
    <div class="row">
        <c:forEach items="${dishList}" var="dish">
            <div class="col-md-4 my-4">
                <div class="card" style="width: 22rem; height: 52rem">
                    <div class="card-body text-lg-left">
                        <img src=${dish.getImagePath()} class="card-img-top width="300" height="275" alt="...">
                        <h5 class="card-title">${dish.getName()}
                        </h5>
                        <p class="card-text">${dish.getDescribing()}
                        </p>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">${dish.getCategory().getString()}
                            </li>
                            <li class="list-group-item">${dish.getWeight()} <fmt:message key="home.weight"/></li>
                            <li class="list-group-item"><m:formatterTag number="${dish.getPrice()}" format="${lang}"/><%--${dish.getPrice()}--%> $
                            </li>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="AddToCartServlet?id=${dish.getId()}" class="card-link align-content-center"><fmt:message key="home.add_to_cart"/></a>
                                <a href="BuyNow?id=${dish.getId()}" class="card-link"><fmt:message key="home.buy_now"/></a>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="h-100 d-flex align-items-center justify-content-center">
        <c:if test="${currentPage != 1}">
            <td><a href="PaginationServlet?page=${currentPage - 1}"><fmt:message key="home.previous_page"/></a></td>
        </c:if>


        <table border="1" cellpadding="6" cellspacing="6">
            <tr>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="PaginationServlet?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>
        <c:if test="${currentPage lt noOfPages}">
            <td><a href="PaginationServlet?page=${currentPage + 1}"><fmt:message key="home.next_page"/></a></td>
        </c:if>
    </div>
    <p/>

</div>
</body>
</html>