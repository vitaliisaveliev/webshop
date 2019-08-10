$(document).ready(function () {
    $(".add-to-cart").click(function (event) {
        var $elem = $(event.target).val();
        $.ajax({
            type: 'GET',
            data: {
                productId: $elem,
            },
            url: '/cartAdd',
            success: onAjaxSuccess
        });
    });

    function onAjaxSuccess(data) {
        $("#cart").text("Cart: $" + data);
    }
});