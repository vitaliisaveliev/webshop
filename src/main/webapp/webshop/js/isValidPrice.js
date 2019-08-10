(() => {
    var filterForm = $('#filterForm')[0];

    var priceFrom = $('.input-price')[0];
    var priceTo = $('.input-price')[1];

    $(filterForm).on('submit', validateFilterForm);

    $(priceFrom).change(validatePriceFrom);
    $(priceTo).change(validatePriceTo);

    function validatePriceFrom() {
        if ($(priceFrom).val() < 0) {
            return false;
        } else {
            return true;
        }
    }

    function validatePriceTo() {
        if ($(priceTo).val() < 0) {
            return false;
        } else {
            return true;
        }
    }

    function validateFilterForm(event) {

        if (validatePriceFrom() == false || validatePriceTo() == false || (validatePriceFrom() == false && validatePriceTo() == false)) {
            event.preventDefault();
            alert("Price must be positive");
        }
    }
})();