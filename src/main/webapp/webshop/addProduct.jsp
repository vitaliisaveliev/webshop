<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/webshop/css/styles.css">
    <title>Add new product</title>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">

        <div class="collapse navbar-collapse" id="navbar1">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container">
        <div class="col-sm-6">
            <h1 class="header">Add product</h1>
            <form action="/addProduct" method="POST" id="addProductForm" name="registrationForm"
                enctype="multipart/form-data" novalidate>
                <div class="form-group">
                    <div id="error">${error}</div>
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Enter name">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label for="surname">Price</label>
                    <input type="number" min="0" class="form-control" id="price" name="price" placeholder="Enter price">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label for="name">Category</label>
                    <input type="text" class="form-control" id="category" name="category" placeholder="Enter category">
                    <div class="invalid-feedback"></div>
                </div>

                <div class="form-group">
                    <label for="name">Manufacturer</label>
                    <input type="text" class="form-control" id="manufacturer" name="manufacturer"
                        placeholder="Enter manufacturer">
                    <div class="invalid-feedback"></div>
                </div>

                <div class="form-group">
                    <label for="picture">Product logo</label>
                    <input type="file" id="picture" name="picture" accept="image/png,image/jpg">
                </div>

                <button type="submit" class="btn btn-primary">Add</button>
            </form>
        </div>
    </div>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>