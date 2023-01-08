<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<c:set var="userProfile" value="${user}"/>
<c:choose>
    <c:when test="${!userProfile.isManager()}">
        <div class="row">
            <div class="col-3">
                <div class="nav flex-column nav-pills" id="v-pills-tab-user" role="tablist" aria-orientation="vertical">
                    <a class="nav-link active" id="v-pills-home-tab-user" data-toggle="pill" href="#v-pills-home-user"
                       role="tab"
                       aria-controls="v-pills-home" aria-selected="false"><fmt:message key="profile.orders"/> </a>
                    <a class="nav-link" id="v-pills-profile-tab-user" data-toggle="pill" href="#v-pills-profile-user"
                       role="tab"
                       aria-controls="v-pills-profile-user" aria-selected="false"><fmt:message key="profile.edit_profile"/></a>
                    <%--<a class="nav-link" id="v-pills-messages-tab-user" data-toggle="pill" href="#v-pills-messages-user"
                       role="tab"
                       aria-controls="v-pills-messages-user" aria-selected="false">Messages</a>
                    <a class="nav-link" id="v-pills-settings-tab-user" data-toggle="pill" href="#v-pills-settings-user"
                       role="tab"
                       aria-controls="v-pills-settings-user" aria-selected="false">Settings</a>--%>
                </div>
            </div>
            <div class="col-9">
                <div class="tab-content" id="v-pills-tabContent-user">
                    <div class="tab-pane fade show active" id="v-pills-home-user" role="tabpanel"
                         aria-labelledby="v-pills-home-tab-user">
                        <%@include file="profile-includes/orders.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="v-pills-profile-user" role="tabpanel"
                         aria-labelledby="v-pills-profile-tab-user">
                        <%@include file="profile-includes/edit-profile.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="v-pills-messages-user" role="tabpanel"
                         aria-labelledby="v-pills-messages-tab-user"></div>
                    <div class="tab-pane fade" id="v-pills-settings-user" role="tabpanel"
                         aria-labelledby="v-pills-settings-tab-user">
                        <%@include file="lol.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="row">
            <div class="col-3">
                <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                    <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab"
                       aria-controls="v-pills-home" aria-selected="false"><fmt:message key="profile.orders"/></a>
                    <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab"
                       aria-controls="v-pills-profile" aria-selected="false"><fmt:message key="profile.edit_profile"/></a>
                    <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab"
                       aria-controls="v-pills-messages" aria-selected="false"><fmt:message key="profile.admin.users_profiles"/></a>
                    <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings" role="tab"
                       aria-controls="v-pills-settings" aria-selected="false"><fmt:message key="profile.admin.add_dish"/></a>
                </div>
            </div>
            <div class="col-9">
                <div class="tab-content" id="v-pills-tabContent">
                    <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
                         aria-labelledby="v-pills-home-tab-user">
                        <%@include file="profile-includes/admin-profile/all-orders.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
                         aria-labelledby="v-pills-profile-tab-user">
                        <%@include file="profile-includes/edit-profile.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="v-pills-messages" role="tabpanel"
                         aria-labelledby="v-pills-messages-tab-user">
                        <%@include file="profile-includes/admin-profile/all-clients.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="v-pills-settings" role="tabpanel"
                         aria-labelledby="v-pills-settings-tab-user">
                        <%@include file="profile-includes/admin-profile/add-dish.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
