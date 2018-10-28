// $(document).ready(removeProduct);

function removeProduct (id) {
    var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var headers = {};
    headers[csrfHeader] = csrfToken;
    $.ajax({
            url: ctx+"/order/cart/remove",
            dataType: 'json',
            type:'post',
            headers:headers,
            contentType:'application/json',
            data:JSON.stringify(Number(id)),
            processData:false
        }
    )
        .done(function () {location.reload(); })
        .fail(function () { alert("the product wasn't removed from your cart"); });
}


