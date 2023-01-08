<%@ page import="com.restaurant.database.javaBeans.Client" %><%--
  Created by IntelliJ IDEA.
  User: luckymen
  Date: 31.10.2022
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="editClient" value="${user}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center"><fmt:message key="edit_profile.header"/></div>
        <div class="card-body">
            <form action="edit" method="post">
                <div class="form-group">
                    <label><fmt:message key="edit_profile.first_name"/> </label>
                    <input type="text" class="form-control" pattern="([a-zA-Z]{3,30}\s*)+" name="name" value="${editClient.getFirstName()}">
                </div>
                <div class="form-group">
                    <label><fmt:message key="edit_profile.last_name"/></label>
                    <input type="text" class="form-control" pattern="[a-zA-Z]{3,30}" name="last_name" value="${editClient.getLastName()}">
                </div>
                <div class="form-group">
                    <label><fmt:message key="edit_profile.phone_number"/></label>
                    <h6/>
                    <label>
                        <b style="font-size: small; font-weigh: lighter; color: firebrick"><fmt:message key="edit_profile.phone_number.warning"/></b>
                    </label>
                    <input type="text" class="form-control" pattern="^\+380\d{3}\d{2}\d{2}\d{2}$" name="number" value="${editClient.getNumber()}"
                    >
                </div>
                <div class="form-group">
                    <label><fmt:message key="edit_profile.login"/></label>
                    <label>
                        <b style="font-size: small; font-weigh: lighter; color: firebrick"><fmt:message key="edit_profile.login.warning"/></b>
                    </label>
                    <input type="text" class="form-control" pattern="^[A-Za-z][A-Za-z0-9_]{7,29}$" name="login" value="${editClient.getLogin()}">
                </div>
                <div class="form-group">
                    <label><fmt:message key="edit_profile.password"/></label>
                    <h6/>
                    <label>
                        <b style="font-size: small; font-weigh: lighter; color: firebrick"><fmt:message key="edit_profile.password.warning"/></b>
                    </label>
                    <input type="text" class="form-control" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" name="password" value="${editClient.getPassword()}">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary"><fmt:message key="edit_profile.update"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
