<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="cart.title"/></title>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<c:set var="orderDish" value="${orderDish}"/>
<div class="container">
    <div class="d-flex py-3">
        <h3><fmt:message key="cart.total_price"/> <m:formatterTag number="${orderDish.getDishMap() != null ?
        orderDish.getTotalPrice() : 0 }"
                                                                  format="${lang}"/>$</h3>

        <c:set var="client" value="${user}"/>
        <c:choose>
            <c:when test="${client != null}">
                <a class="mx-3 btn btn-primary" href="confirm?isLogged=${client != null}"><fmt:message
                        key="cart.confirm"/> </a>
            </c:when>
            <c:otherwise>
                <a class="mx-3 btn btn-primary" href="confirm?isLogged=false"><fmt:message key="cart.confirm"/></a>
            </c:otherwise>
        </c:choose>
    </div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="cart.name"/></th>
            <th scope="col"><fmt:message key="cart.category"/></th>
            <th scope="col"><fmt:message key="cart.weight"/></th>
            <th scope="col"><fmt:message key="cart.price"/></th>
            <th scope="col"><fmt:message key="cart.quantity"/></th>
            <th scope="col"><fmt:message key="cart.cancel"/></th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${orderDish.getDishMap() != null && orderDish.getDishMap().size() != 0}">
            <c:forEach items="${orderDish.getDishMap().keySet().stream().toList()}" var="dish">
                <tr>
                    <td>${dish.getName()}
                    </td>
                    <td>${dish.getCategory().getString()}
                    </td>
                    <td>${dish.getWeight()}
                    </td>
                    <td><m:formatterTag number="${dish.getPrice() * (orderDish.getDishMap().get(dish))}"
                                        format="${lang}"/>
                    </td>
                    <td>
                        <form action="" method="get" class="form-inline">
                            <a class="btn btn-primary" href="quantity?action=decr&id=${dish.getId()}"><i
                                    class="far fa-eye">-</i></a>
                            <input type="text" class="form-control" href="" value="${orderDish.getDishMap().get(dish)}"
                                   min="1" id="input1" placeholder="enter amount">
                            <a class="btn btn-primary" href="quantity?action=incr&id=${dish.getId()}"><i
                                    class="far fa-eye">+</i></a>
                        </form>
                    </td>
                    <td>
                        <a class="btn btn-sm btn-danger" href="remove?id=${dish.getId()}"><fmt:message
                                key="cart.remove"/></a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
