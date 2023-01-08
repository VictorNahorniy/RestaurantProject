<html>
<head>
    <title>Welcome</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center"><fmt:message key="login.header"/> </div>
        <div class="card-body">
            <form action="login" method="post">
                <div class="form-group" >
                    <label><fmt:message key="login.login"/></label>
                    <input type="text" class="form-control" name="login" placeholder="<fmt:message key="login.login.placeholder"/>" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="login.password"/></label>
                    <input type="password" class="form-control" name="password" placeholder="<fmt:message key="login.password.placeholder"/>" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary"><fmt:message key="login.button"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>