<%@ page import="com.restaurant.database.javaBeans.Dish" %>
<%@ page import="com.restaurant.database.dao.impl.DishDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: luckymen
  Date: 26.10.2022
  Time: 4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center"><fmt:message key="registration.header"/> </div>
        <div class="card-body">
            <form action="registration" method="post">
                <div class="form-group">
                    <label><fmt:message key="registration.first_name"/></label>
                    <input type="text" class="form-control"<%-- pattern="([a-zA-Z]{3,30}\s*)+"--%> name="name" placeholder="<fmt:message key="registration.first_name.placeholder"/>" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="registration.last_name"/></label>
                    <input type="text" class="form-control" <%--pattern="[a-zA-Z]{3,30}"--%> name="last_name" placeholder="<fmt:message key="registration.last_name.placeholder"/>"
                           required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="registration.phone_number"/></label>
                    <h6/>
                    <label>
                        <b style="font-size: small; font-weigh: lighter; color: firebrick"><fmt:message key="registration.phone_number.warning"/></b>
                    </label>
                    <input type="text" class="form-control" pattern="^\+380\d{3}\d{2}\d{2}\d{2}$" name="number"
                           placeholder="<fmt:message key="registration.phone_number.placeholder"/>"
                           required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="registration.login"/></label>
                    <label>
                        <b style="font-size: small; font-weigh: lighter; color: firebrick">
                            <fmt:message key="registration.login.warning"/>
                        </b>
                    </label>
                    <input type="text" class="form-control" pattern="^[A-Za-z][A-Za-z0-9_]{7,29}$" name="login"
                           placeholder="<fmt:message key="registration.login.placeholder"/>" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="registration.password"/></label>
                    <h6/>
                    <label>
                        <b style="font-size: small; font-weigh: lighter; color: firebrick"><fmt:message key="registration.password.warning"/></b>
                    </label>
                    <input type="password" class="form-control" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" name="password" placeholder="<fmt:message key="registration.password.placeholder"/>" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary"><fmt:message key="registration.sign_up"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
