<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/tag/custom.tld" prefix="captcha" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/webshop/css/styles.css">
    <title>Sign up</title>
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
            <h1 class="header">Registration</h1>
            <form action="/registration" method="POST" id="registrationForm" name="registrationForm"
                enctype="multipart/form-data" novalidate>
                <div class="form-group">
                    <div id="errorFirstName">${errors.name}</div>
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Enter name"
                        value="${user.name}">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <div id="errorSurname">${errors.surname}</div>
                    <label for="surname">Surname</label>
                    <input type="text" class="form-control" id="surname" name="surname" placeholder="Enter surname"
                        value="${user.surname}">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <div id="errorEmail">${errors.email}</div>
                    <div class="errorDuplicateUser">${errors.duplicateUser}</div>
                    <label for="email">Email address</label>
                    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp"
                        placeholder="Enter email" value="${user.email}">
                    <div class="invalid-feedback"></div>
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.
                    </small>
                </div>
                <div class="form-group">
                    <div id="errorPassword">${errors.password}</div>
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password"
                        placeholder="Enter password" value="">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <div id="errorConfirmPassword">${errors.confirmPassword}</div>
                    <label for="confirmPassword">Confirm password</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                        placeholder="Confirm your password" value="">
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group has-float-label">
                    <div id="errorCountry">${errors.country}</div>
                    <label for="country">Country</label>
                    <select class="form-control custom-select" id="country" name="country">
                        <option selected>Select country</option>
                        <option>Ukraine</option>
                        <option>Russia</option>
                        <option>USA</option>
                    </select>
                    <div class="invalid-feedback"></div>
                </div>

                <div class="form-group">
                    <label for="picture">Your avatar</label>
                    <input type="file" id="picture" name="picture" accept="image/png,image/jpg">
                </div>

                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="check" name="check">
                    <label class="form-check-label" for="exampleCheck">Check me out</label>
                    <div class="invalid-feedback"></div>
                </div>

                <div class="captcha">
                    <captcha:captcha key="${captchaKey}" />
                </div>
                <input type="text" id="captchaAnswer" class="form-control " name="captchaAnswer"
                    placeholder="Type your answer">
                <div class="errorCaptcha">${errors.captchaAnswer}</div>

                <button type="submit" class="btn btn-primary">Sign up</button>
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
    <script src="/webshop/js/validationJQuery.js"></script>

</body>

</html>