(() => {
    var registrationForm = $('#registrationForm')[0];


    var name = $('#name')[0];
    var surname = $('#surname')[0];
    var email = $('#email')[0];
    var password = $('#password')[0];
    var confirmPassword = $('#confirmPassword')[0];
    var selectedCountry = $('#country')[0];
    var terms = $('#check')[0];

    $(registrationForm).on('submit', validateRegistrationForm);

    $(name).focusout(validateName);
    $(surname).focusout(validateSurname);
    $(email).focusout(validateEmail);
    $(password).focusout(validatePassword);
    $(confirmPassword).focusout(validateConfirmPassword);
    $(selectedCountry).focusout(validateSelectedCountry);
    $(terms).change(validateTermsCheckbox);

    function validateName() {
        resetError(name);
        if (!$(name).val()) {
            showError(name, "User name shouldn't be empty.");
        } else if ($(name).val().length < 3) {
            showError(name, "User name should be minimum 3 symbols length.");
        } else {
            markValid(name);
        }
    }

    function validateSurname() {
        resetError(surname);
        if (!$(surname).val()) {
            showError(surname, "User surname shouldn't be empty.");
        } else if ($(surname).val().length < 3) {
            showError(surname, "User surname should be minimum 3 symbols length.");
        } else {
            markValid(surname);
        }
    }

    function validateEmail() {
        resetError(email);
        if (!$(email).val()) {
            showError(email, "E-mail shouldn't be empty.");
        } else if (!validateEmailRegExp($(email).val())) {
            showError(email, "Please enter a valid email address.");
        } else {
            markValid(email);
        }
    }

    function validatePassword() {
        resetError(password);
        if (!$(password).val()) {
            showError(password, "Password shouldn't be empty.");
        } else if (!validatePasswordRegExp($(password).val())) {
            showError(password, "Password should be 0-9a-zA-Z 8 symbols length minimum.");
        } else {
            markValid(password);
        }
    }

    function validateConfirmPassword() {
        resetError(confirmPassword);
        if ($(confirmPassword).val() && $(confirmPassword).val() == $(password).val()) {
            markValid(confirmPassword);
        } else {
            showError(confirmPassword, "Passwords doesn't match.")
        }
    }

    function validateSelectedCountry() {
        resetError(selectedCountry);
        if ($(selectedCountry).val() === "Select country") {
            showError(selectedCountry, "Please, choose country")
        }
        else {
            markValid(selectedCountry);
        }
    }

    function validateTermsCheckbox() {
        resetError(terms);
        if (terms.checked) {
            markValid(terms);
        } else {
            showError(terms, "You must agree before submitting.");
        }
    }

    function validateRegistrationForm(event) {
        var formFields = $('input');
        var formFieldsArray = Array.prototype.slice.call(formFields);

        validateName();
        validateSurname();
        validateEmail();
        validatePassword();
        validateConfirmPassword();
        validateSelectedCountry();
        validateTermsCheckbox();

        if (formFieldsArray.some(input => input.classList.contains('is-invalid'))) {
            event.preventDefault();
        }
    }

    function showError(element, message) {
        var invalidFeedback = $(element).siblings('.invalid-feedback');

        invalidFeedback.text(message);
        $(element).addClass('is-invalid');
    }

    function markValid(element) {
        $(element).addClass('is-valid');
    }

    function resetError(element) {
        var invalidFeedback = $(element).siblings('.invalid-feedback');

        $(element).removeClass('is-invalid');
        invalidFeedback.text("");
    }

    function validateEmailRegExp(email) {
        return /\S+@\S+\.\S+/.test(email);
    }

    function validatePasswordRegExp(password) {
        var passwordRegExp = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;

        return passwordRegExp.test(password);
    }

})();