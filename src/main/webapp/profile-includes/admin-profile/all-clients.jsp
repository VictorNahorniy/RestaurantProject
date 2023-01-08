<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/includes/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="d-flex py-3">
        <h3><fmt:message key="all_clients.header"/></h3>
    </div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col"><fmt:message key="all_clients.first_name"/></th>
            <th scope="col"><fmt:message key="all_clients.last_name"/></th>
            <th scope="col"><fmt:message key="all_clients.telephone_number"/></th>
            <th scope="col"><fmt:message key="all_clients.login"/></th>
            <th scope="col"><fmt:message key="all_clients.status"/></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:set var="clientList" value="${clientDaoImpl.findAll()}"/>
        <c:set var="userInSession" value="${user}"/>

        <c:if test="${clientList != null}">
            <c:forEach items="${clientList}" var="client">
                <tr>
                    <td>${client.getId()}
                    </td>
                    <td>${client.getFirstName()}
                    </td>
                    <td>${client.getLastName()}
                    </td>
                    <td>${client.getNumber()}
                    </td>
                    <td>${client.getLogin()}
                    </td>
                    <td>
                            ${client.isManager() ? "Manager" : "Client"}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${client.equals(userInSession)}">
                                <fmt:message key="all_clients.your_account"/>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-primary"
                                   href="ChangeUserStatus?isManager=${client.isManager()}&id=${client.getId()}"><i
                                        class="far fa-eye"><fmt:message key="all_clients.make"/>
                                    <c:choose>
                                        <c:when test="${client.isManager()}">
                                            <fmt:message key="all_clients.client"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="all_clients.manager"/>
                                        </c:otherwise>
                                    </c:choose>
                                </i></a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>