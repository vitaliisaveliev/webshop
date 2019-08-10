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

    <title>Confirm your order</title>
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
                    <a class="nav-link" id="cart" href="/cart">Cart</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="mt-2 py-3 nav justify-content-center">
        <div>
            <form action="/buy" method="POST" id="order">
                <div class="form-group text-center payment">
                    Payment type:
                    <div class="d-block">
                        <label for="payment1">Cash</label>
                        <input type="radio" name="payment_type" value="Cash" id="payment1">
                    </div>
                    <div class="d-block">
                        <label for="payment2">Card</label>
                        <input type="radio" name="payment_type" value="Card" id="payment2"></div>
                </div>
                <div class="form-group text-center">
                    Delivery:
                    <div class="d-block">
                        <label for="delivery1">Courier</label>
                        <input type="radio" name="delivery_type" value="Courier" id="delivery1">
                    </div>
                    <div class="d-block">
                        <label for="delivery2">Postal office</label>
                        <input type="radio" name="delivery_type" value="Post" id="delivery2">
                    </div>
                </div>
                <div class="text-center">
                    <button type="reset" class="btn btn-outline-danger">Clear</button>
                    <button type="submit" class="btn btn-outline-success">Submit</button>
                </div>
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
    <script src="/webshop/js/orders.js"></script>
</body>

</html>