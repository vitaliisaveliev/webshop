<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Welcome to shop</title>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
        <a class="navbar-brand">Navigation</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar1"
            aria-controls="navbar1" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <form class="form-inline my-2 my-lg-0" action="/searchedProducts" method="GET">
            <input class="form-control mr-sm-1" id="pattern" name="pattern" type="search" placeholder="Search product"
                aria-label="Search">
            <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
        </form>
        <div class="collapse navbar-collapse" id="navbar1">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#" data-toggle="modal" data-target="#aboutUS">About us<span
                            class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/registration">Registration</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Weapons</a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                        <a class="dropdown-item" href="/showProducts">All weapons</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/addProduct">Add weapon</a>
                    </div>
                </li>

                <%@ include file="/WEB-INF/tag/loginTag.jspf" %>

            </ul>
        </div>
    </nav>
    <div class="container-fluid">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li class="active" data-target="#carouselExampleIndicators" data-slide-to="0">
                    < /li> <li data-target="#carouselExampleIndicators" data-slide-to="2">
                        < /li> <li data-target="#carouselExampleIndicators" data-slide-to="3">
                            < /li> </ol> <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img src="webshop/img/bg1.jpg" alt="" class="d-block w-100">
                                </div>
                                <div class="carousel-item">
                                    <img src="webshop/img/bg3.jpg" alt="" class="d-block w-100">
                                </div>
                                <div class="carousel-item">
                                    <img src="webshop/img/bg4.jpg" alt="" class="d-block w-100">
                                </div>
        </div>
        <a href="#carouselExampleIndicators" class="carousel-control-prev" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a href="#carouselExampleIndicators" class="carousel-control-next" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    </div>

    <div class="container-fluid">
        <div class="container">
            <div class="row text-center">
                <div class="col">
                    <h3>FN F2000</h3>
                    <p>The FN F2000 is a 5.56×45mm NATO bullpup assault rifle, designed by FN Herstal in Belgium.[5] The
                        F2000 made its debut in March 2001 at the IDEX defence exhibition held in Abu Dhabi, in the
                        United Arab Emirates. As of early 2019, the F2000 is removed from the FN international and US
                        websites.</p>
                    <img src="webshop/img/FN F2000.jpeg" alt="" class="w-100">
                </div>
                <div class="col">
                    <h3>M4A1</h3>
                    <p>
                        M4 (military index and name - Carbine, 5.56 mm, M4, manufacturer's factory index - Colt Model
                        920) - an automatic carbine, created in the USA on the basis of an M16A2 rifle and originally
                        intended for arming crews of combat vehicles and calculating weapons and military equipment.</p>
                    <img src="webshop/img/M4A1.png" alt="" class="w-100">
                </div>
                <div class="col">
                    <h3>MK47</h3>
                    <p>The CMMG Mk47 Mutant is an American-made semi-automatic rifle chambered in 7.62×39mm caliber,
                        made by CMMG Inc. It can accept all types of 7.62-type magazines, including steel, polymer and
                        drum magazines.[1]</p>
                    <img src="webshop/img/MK47.jpg" alt="" class="w-100">
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="aboutUS" tabindex="-1" role="dialog" aria-labelledby="aboutUS" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="aboutUS">Dear visitors!</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>
                        Hello, on our site you can pick up any rifle you like and come to our shooting range before
                        buying
                        and try your test sample</p>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" data-dismiss="modal">Close</button>
                </div>
            </div>
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