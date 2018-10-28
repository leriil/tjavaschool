$(document).ready(function () {
    $(".logout").click(function (e) {
        e.preventDefault();
        $(".logout-form").submit();
    });
    $.ajax({
            url: ctx+"/order/cart/count",
            dataType: 'json',
            type:'get',
            contentType:'application/json',
            processData:false
        }
    )
        .done(function (result) { $("#basket").html(result); })
        .fail(function () { alert("the product wasn't removed from your cart"); });
    });