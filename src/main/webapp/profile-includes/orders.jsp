<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/includes/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="d-flex py-3">
        <h3><fmt:message key="orders.header"/> </h3>
    </div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="orders.full_name"/> </th>
            <th scope="col"><fmt:message key="orders.date"/></th>
            <th scope="col"><fmt:message key="orders.address"/></th>
            <th scope="col"><fmt:message key="orders.price"/></th>
            <th scope="col"><fmt:message key="orders.status"/></th>
        </tr>
        </thead>
        <tbody>
        <c:set var="orderDaoImpl" value="${orderDaoImpl}"/>
        <c:set var="clientDaoImpl" value="${clientDaoImpl}"/>
        <c:set var="addressesDaoImpl" value="${addressesDaoImpl}"/>
        <c:set var="client" value="${user}"/>
        <c:set var="orderList" value="${orderDaoImpl.findOrderByClientID(client.getId())}"/>
        <c:if test="${orderList!=null}">
            <c:forEach items="${orderList}" var="order">
                <tr>
                    <td>${clientDaoImpl.findEntityById(order.getClientID()).getFullName()}
                    </td>
                    <td>${order.getOrderDate()}
                    </td>
                    <td>${addressesDaoImpl.findEntityById(order.getAddressesID()).toString()}
                    </td>
                    <td>${order.getPrice()}
                    </td>
                    <td>${order.getStatus().getString()}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
