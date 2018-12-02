$(document).ready(function () {

    $("#addToCart").click(function (e) {
        addProductToCart(e);
    });
});

function addProductToCart(e) {
    e.preventDefault();
    var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var headers = {};
    headers[csrfHeader] = csrfToken;
    $.ajax({
            url: ctx + "/order/cart/add",
            dataType: 'json',
            type: 'post',
            headers: headers,
            contentType: 'application/json',
            data: JSON.stringify(Number($("#productForCart").text())),
            processData: false
        }
    )
        .done(function (result) {
            $("#myModal").modal('toggle');
        });
}