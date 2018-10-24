$(document).ready(function () {
    var products=0;
    $("#logout").click(function (e) {
        e.preventDefault();
        $("#logout-form").submit();
    })
    $.ajax({
            url: ctx+"/order/cart/count",
            dataType: 'json',
            type:'get',
            // headers:headers,
            contentType:'application/json',
            // data:JSON.stringify(Number(id)),
            processData:false
        }
    )
        .done(function (result) { $("#basket").html(result); })
        .fail(function () { alert("the product wasn't removed from your cart"); });
    }
);