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
            <th scope="col">ID</th>
            <th scope="col"><fmt:message key="orders.full_name"/></th>
            <th scope="col"><fmt:message key="orders.date"/></th>
            <th scope="col"><fmt:message key="orders.address"/></th>
            <th scope="col"><fmt:message key="orders.price"/></th>
            <th scope="col"><fmt:message key="orders.status"/></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:set var="orderList" value="${orderDaoImpl.findAll()}"/>
        <c:set var="clientDaoImpl" value="${clientDaoImpl}"/>
        <c:set var="addressesDaoImpl" value="${addressesDaoImpl}"/>
        <c:set var="COMPLETED" value="${Completed}"/>
        <c:if test="${orderList != null}">
            <c:forEach items="${orderList}" var="order">
                <tr>
                    <td>${order.getId()}</td>
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
                    <td>
                        <c:if test="${!order.getStatus().equals(COMPLETED)}">
                            <a class="btn btn-primary"
                               href="change-status?status=${order.getStatus().getString()}&id=${order.getId()}">
                                <i class="far fa-eye"><fmt:message key="orders.next_stage"/></i>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
