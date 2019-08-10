$("#sort").change(function () { setAllFieldsToForm() });
$("#perPage").change(function () { setAllFieldsToForm() });
$("#submit_filter").click(function () { setAllFieldsToForm2() });
$(".path").click(function (event) { setAllFieldsToForm3(event) });

function setAllFieldsToForm() {
    var sortValue = $("#sort").val();
    var perPageValue = $("#perPage").val();
    var currentPage = 1;

    $("#productQuantity").val(perPageValue);
    $("#sortProduct").val(sortValue);
    $("#currentPage").val(currentPage);

    $("#sort option[value=" + sortValue + "]").prop('selected', true);
    $("#sort option[value=" + perPageValue + "]").prop('selected', true);

    $("#filterForm").submit();
}

function setAllFieldsToForm2() {
    var sortValue = $("#sort").val();
    var perPageValue = $("#perPage").val();
    var currentPage = 1;

    $("#productQuantity").val(perPageValue);
    $("#sortProduct").val(sortValue);
    $("#currentPage").val(currentPage);
}

function setAllFieldsToForm3(event) {
    var $elem = $(event.target).parent();
    var sortValue = $("#sort").val();
    var perPageValue = $("#perPage").val();
    var currentPage = $elem.val();
    $("#productQuantity").val(perPageValue);
    $("#sortProduct").val(sortValue);
    $("#currentPage").val(currentPage);

    $("#sort option[value=" + sortValue + "]").prop('selected', true);
    $("#sort option[value=" + perPageValue + "]").prop('selected', true);

    $("#filterForm").submit();
}

$(document).ready(function () {
    $('input.checkCat:checkbox').click(function () {
        $('input.checkCat:checkbox').not(this).prop('checked', false);
    });
});

$(document).ready(function () {
    $('input.checkMan:checkbox').click(function () {
        $('input.checkMan:checkbox').not(this).prop('checked', false);
    });
});


