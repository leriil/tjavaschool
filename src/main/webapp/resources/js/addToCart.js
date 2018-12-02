$(document).ready(function () {

    // var products=[];

    $("#addToCart").click(function (e) {
        addProductToCart(e);
        // .fail(function () {
        //     alert("the product wasn't added to your cart");
        // });
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
            // products.push(result);
            // localStorage.setItem("products", products);
        });
}