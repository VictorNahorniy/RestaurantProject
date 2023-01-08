<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
  <div class="card w-50 mx-auto my-5">
    <div class="card-header text-center"><fmt:message key="confirm.address"/> </div>
    <div class="card-body">
      <form action="address" method="post">
        <div class="form-group">
          <label><fmt:message key="confirm.city"/></label>
          <input type="text" class="form-control" name="city" placeholder="<fmt:message key="confirm.city.placeholder"/>" required>
        </div>
        <div class="form-group">
          <label><fmt:message key="confirm.street"/></label>
          <input type="text" class="form-control" name="street" placeholder="<fmt:message key="confirm.street.placeholder"/>" required>
        </div>
        <div class="form-group">
          <label><fmt:message key="confirm.building_num"/></label>
          <input type="number" class="form-control" name="building-number" placeholder="<fmt:message key="confirm.building_num.placeholder"/>" required>
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-primary"><fmt:message key="confirm.complete"/></button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
