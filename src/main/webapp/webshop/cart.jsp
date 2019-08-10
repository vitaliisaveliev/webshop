<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/webshop/css/style.css">

    <title>Cart</title>
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
                    <a class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/showProducts">Catalog</a>
                </li>

                <%@ include file="/WEB-INF/tag/loginTag.jspf" %>

            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="mt-2 py-3 nav justify-content-center">
            <div class="d-inline-block w-18 text-center">Name</div>
            <div class="d-inline-block w-18 text-center">Price</div>
            <div class="d-inline-block w-18 text-center">Quantity</div>
            <div class="d-inline-block w-18 text-center">Total price</div>
            <div class="d-inline-block w-18 text-center">Delete from cart</div>
        </div>
        <form action="/buy" method="GET" id="makeOrder">
            <div class="products">
                <c:set var="productCount" value="${0}" />
                <c:forEach items="${cart}" var="item">
                    <c:set var="productCount" value="${productCount + 1}" />
                    <div class="mt-2 py-3 border border-secondary rounded nav justify-content-center">
                        <div class="d-inline-block w-18 text-center">${item.key.name}</div>
                        <div class="d-inline-block w-18 text-center">${item.key.price}</div>
                        <div class="w-18">
                            <input type="hidden" class="id" name="id${productCount}" value="${item.key.id}">
                            <input type="hidden" class="count" name="count${productCount}" value="${item.value}">
                            <input type="hidden" class="hiddenPrice" name="price${productCount}"
                                value="${item.key.price * item.value}">
                            <input class="form-control d-inline-block text-center change" type="number" min="1"
                                val="${item.key.id}" value="${item.value}">
                        </div>
                        <div class="d-inline-block w-18 text-center price">$${item.key.price * item.value}</div>
                        <div class="d-inline-block w-18 text-center"><button type="button"
                                class="btn btn-outline-danger delete" value="${item.key.id}">Delete</button></div>
                    </div>
                </c:forEach>
                <input type="hidden" value="${productCount}" name="productCount">
            </div>
            <c:set var="totalQuantity" value="${0}" />
            <c:set var="totalPrice" value="${0}" />
            <c:forEach items="${cart}" var="item">
                <c:set var="totalPrice" value="${totalPrice + item.key.price * item.value}" />
                <c:set var="totalQuantity" value="${totalQuantity + item.value}" />
            </c:forEach>
            <div class="mt-2 py-3 border border-info rounded nav justify-content-center">
                <div class="d-inline-block w-18 text-center">Total</div>
                <div class="d-inline-block w-18 text-center"></div>
                <div class="d-inline-block w-18 text-center" id="quantity">${totalQuantity}</div>
                <div class="d-inline-block w-18 text-center" id="sum" name="sum">$${totalPrice}</div>
                <div class="d-inline-block w-18 text-center"><button type="button"
                        class="btn btn-outline-danger delete">Clean
                        cart</button>
                    <button type="sumbit" value="${isAuthorized}" class="btn btn-outline-success buy">Buy</button>
                </div>
            </div>
            <input type="hidden" class="total" value="${totalPrice}">
        </form>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webshop/js/cart.js"></script>
</body>

</html>