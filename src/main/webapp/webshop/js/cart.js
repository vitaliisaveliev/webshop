$(document).ready(function () {

    var make = $('#makeOrder')[0];
    $(make).on('submit', validateContent);
    function validateContent(event) {
        var total = $('.total').val();
        if (total == 0) {
            alert("your cart is empty");
            event.preventDefault();
        }
    }
    
    var $div;
    var $count;
    var $price;
    var $hiddenCount;

    $(".change").change(function (event) {
        $count = $(event.target).val();
        var $id = $(event.target).siblings('input[type="hidden"]').val();
        $hiddenCount = $(event.target).siblings(".count");
        $price = $(event.target).siblings(".hiddenPrice");
        $div = $(event.target).parent().siblings(".price");
        $.ajax({
            type: 'GET',
            data: {
                count: $count,
                id: $id
            },
            url: '/change',
            success: onAjaxSuccess1
        });
    });

    function onAjaxSuccess1(data) {
        $("#sum").text("$" + data[0]);
        $("#quantity").text(data[1]);
        $($div).html("$" + data[2]);
        $($price).val(data[2]);
        $($hiddenCount).val($count);
    }

    var $product;

    $(".delete").click(function (event) {
        var $productId = $(event.target).val();
        $product = $(event.target).parent().parent();
        $.ajax({
            type: 'GET',
            data: {
                productId: $productId,
            },
            url: '/delete',
            success: onAjaxSuccess
        });
    });

    function onAjaxSuccess(data) {
        if (data[2] === "true") {
            $(".products").remove();
            $("#sum").text("$0");
            $("#quantity").text(0);
        } else {
            $("#sum").text("$" + data[0]);
            $("#quantity").text(data[1]);
            $($product).remove()
        }
    }

    $(".buy").click(function (event) {
        var check = $(event.target).val();
        if (check == false) {
            alert("You must login or sign up")
        }
    });
});