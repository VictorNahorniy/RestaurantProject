<%--
  Created by IntelliJ IDEA.
  User: luckymen
  Date: 01.11.2022
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
  <div class="card w-50 mx-auto my-5">
    <div class="card-header text-center">Dish</div>
    <div class="card-body">
      <form action="" method="post">
        <div class="input-group">
          <div class="custom-file">
            <input type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
            <label class="custom-file-label" for="inputGroupFile04">Choose file</label>
          </div>
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">Button</button>
          </div>
        </div>
        <div class="form-group">
          <label>Name</label>
          <input type="text" class="form-control" name="name" placeholder="Enter name of the dish" required>
        </div>
        <div class="form-group">
          <label>Price</label>
          <input type="number" class="form-control" name="last_name" placeholder="Enter price $" min="0"
                 required>
        </div>
        <div class="form-group">
          <label>Category</label>
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                    data-bs-toggle="dropdown" aria-expanded="false">
              Sort by
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1" name="sortType">
              <li><a class="dropdown-item" href="sort?sortType=name">Name</a></li>
              <li><a class="dropdown-item" href="sort?sortType=category">Category</a></li>
              <li><a class="dropdown-item" href="sort?sortType=price">Price</a></li>
            </ul>
          </div>
        </div>
        <div class="form-group">
          <label>Weight</label>
          <input type="number" class="form-control" name="login" placeholder="Enter weight" required>
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-primary">Add dish</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
