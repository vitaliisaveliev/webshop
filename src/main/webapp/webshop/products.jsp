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
    <div class="nav justify-content-center mt-2">
        <ul class="pagination">
            <c:choose>
                <c:when test="${pages == 0}"></c:when>
                <c:when test="${pages < 6}">
                    <c:forEach var="i" begin="1" end="${pages}">
                        <c:choose>
                            <c:when test="${currentPage == i}">
                                <li class="active page-item"><a class="page-link">
                                        ${currentPage} <span class="sr-only">(current)</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item submitLink path" value="${i}"><a class="page-link">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${currentPage == 1}">
                            <li class="page-item active path"><a class="page-link">
                                    ${currentPage} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item submitLink path" value="1"><a class="page-link">1</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${currentPage > 4 && currentPage < pages - 3}">
                        <li class="page-item submitLink disabled"><a class="page-link">...</a></li>
                        <li class="page-item submitLink path" value="${currentPage - 2}"><a
                                class="page-link">${currentPage - 2}</a></li>
                        <li class="page-item submitLink path" value="${currentPage - 1}"><a
                                class="page-link">${currentPage - 1}</a></li>
                        <li class="active page-item"><a class="page-link">${currentPage} <span
                                    class="sr-only">(current)</span></a></li>
                        <li class="page-item submitLink path" value="${currentPage + 1}"><a
                                class="page-link">${currentPage + 1}</a></li>
                        <li class="page-item submitLink path" value="${currentPage + 2}"><a
                                class="page-link">${currentPage + 2}</a></li>
                        <li class="page-item submitLink disabled"><a class="page-link">...</a></li>
                    </c:if>
                    <c:if test="${currentPage < 5}">
                        <c:forEach var="i" begin="2" end="${currentPage + 2}">
                            <c:choose>
                                <c:when test="${currentPage == i}">
                                    <li class="active page-item"><a class="page-link">
                                            ${currentPage} <span class="sr-only">(current)</span></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item submitLink path" id="${i}" value="${i}"><a
                                            class="page-link">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <li class="page-item submitLink disabled"><a class="page-link">...</a></li>
                    </c:if>
                    <c:if test="${currentPage > pages - 4}">
                        <li class="page-item submitLink disabled"><a class="page-link">...</a></li>
                        <c:forEach var="i" begin="${currentPage - 2}" end="${pages - 1}">
                            <c:choose>
                                <c:when test="${currentPage == i}">
                                    <li class="active page-item"><a class="page-link">
                                            ${currentPage} <span class="sr-only">(current)</span></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item submitLink path" value="${i}"><a class="page-link">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:if>
                    <c:choose>
                        <c:when test="${currentPage == pages}">
                            <li class="active page-item"><a class="page-link">
                                    ${currentPage} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item submitLink path" value="${pages}"><a class="page-link">${pages}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <br>
    <div class="content">
        <div class="nav justify-content">
            <div class="sort">
                <div class="d-inline-block">
                    Sort
                    <select name="sort" id="sort" class="selectpicker">
                        <option value="1" <c:if test="${filterBean.sortValue == 1}">selected</c:if>>Price to high
                        </option>
                        <option value="2" <c:if test="${filterBean.sortValue == 2}">selected</c:if>>Price to low
                        </option>
                        <option value="3" <c:if test="${filterBean.sortValue == 3}">selected</c:if>>Name</option>
                    </select>
                </div>
                <div class="d-inline-block">
                    Per page
                    <select name="perPage" id="perPage" class="selectpicker">
                        <option value="5" <c:if test="${filterBean.perPage == 5}">selected</c:if>>5</option>
                        <option value="10" <c:if test="${filterBean.perPage == 10}">selected</c:if>>10</option>
                        <option value="15" <c:if test="${filterBean.perPage == 15}">selected</c:if>>15</option>
                    </select>
                </div>
            </div>
        </div>
        <br>
        <div class="container" style="margin-left: 0; width: 217px; ">

            <form action="/showProducts" method="GET" id="filterForm" name="filterForm">
                <div>
                    Name:
                    <div>
                        <input type="text" placeholder="Product name" name="filterName" value="${filterBean.name}">
                    </div>
                </div>
                <div class="mt-1">
                    Price:
                    <div class="validatePrice">
                        <input type="text" class="input-price" placeholder="From" name="priceFrom"
                            value="${filterBean.priceFrom}">
                        <input type="text" class="input-price" placeholder="To" name="priceTo"
                            value="${filterBean.priceTo}">
                        <div class="invalid-feedback"></div>
                    </div>
                </div>
                <div class="mt-1">
                    Categories:
                    <c:forEach items="${categories}" var="item">
                        <div class="categories">
                            <input type="checkbox" class="checkCat" <c:if test="${filterBean.category == item.id}">
                            checked
                            </c:if>
                            value="${item.getId()}" name="categoryId">
                            <label for="${item}">${item.name}</label>
                        </div>
                    </c:forEach>
                </div>

                <div class="mt-1">
                    Manufacturers:
                    <c:forEach items="${manufacturers}" var="item">
                        <div class="categories">
                            <input type="checkbox" class="checkMan" <c:if test="${filterBean.manufacturer == item.id}">
                            checked
                            </c:if>
                            value="${item.getId()}" name="manufacturerId">
                            <label for="${item}">${item.country}</label>
                        </div>
                    </c:forEach>
                </div>
                <input type="hidden" id="productQuantity" name="productQuantity">
                <input type="hidden" id="sortProduct" name="sortProduct">
                <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
                <button type="submit" class="btn btn-primary" id="submit_filter">Search</button>
            </form>
        </div>

        <c:choose>
            <c:when test="${pages == 0}">
                <div class="nav justify-content-center">Products not found</div>
            </c:when>
            <c:otherwise>
                <div class="container" style="width: 67%; top: 123px; position: absolute; margin-left: 337px;">
                    <table class="table">
                        <h1 align="center">Available products</h1>
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
                                    <button value="${product.id}" class="btn-sm btn-dark add-to-cart"
                                        id="submit">buy</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
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
    <script src="/webshop/js/cart.js"></script>
    <script src="/webshop/js/fill_hidden_field.js"></script>
    <script src="/webshop/js/isValidPrice.js"></script>
</body>

</html>