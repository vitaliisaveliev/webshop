<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Catalog</title>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
        <a class="navbar-brand">Navigation</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar1"
            aria-controls="navbar1" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbar1">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="cart" href="/cart">Cart: $${cart.sum}</a>
                </li>
            </ul>
        </div>
    </nav>
    <br>
    <div class="container" style="width: 67%; top: 123px; position: absolute; margin-left: 337px;">
        <table class="table">
            <h1 align="center">was founded</h1>
            <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>Manufacturer</th>
                <th>Category</th>

            </tr>
            <c:forEach var="product" items="${products}">
                <tr class="row1">
                    <td> <img src='/imageServlet?source=${product.img}' width=80px height=80px>
                    </td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.manufacturer}</td>
                    <td>${product.category}</td>
                    <td>
                        <input type="hidden" value="${product.id}" name="productId">
                        <button value="${product.id}" class="btn-sm btn-dark add-to-cart" id="submit">buy</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <script>

    </script>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webshop/js/addToCart.js"></script>
</body>

</html>