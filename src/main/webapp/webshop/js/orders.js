$(document).ready(function () {
    var child = document.createElement("div");
    child.innerHTML = "Enter your card: <input class='form-control mr-sm-1' name='payCard' id='payCard' type='password' placeholder='Your card' aria-label='Card'>";
    var regex = /^\d+$/;
    var card = $("#payment2");
    var cash = $("#payment1");
    var parent = card.parent().parent();
    parent.append(child);
    child.hidden = true;
    card.on("change", function () {
        child.hidden = false;
    })
    cash.on("change", function () {
        child.hidden = true;
    })
    var orderForm = $("#order");
    orderForm.on("submit", function (event) {
        if (card.is(":checked")) {
            if (regex.test($('#payCard').val())) {
                this.submit();
            } else {
                alert("must be number")
                event.preventDefault();
            }
        }
        else if (cash.is(":checked")) {
            this.submit();
        }
        else {
            alert("Specify delivery and payment type");
            event.preventDefault();
        }
    });
});