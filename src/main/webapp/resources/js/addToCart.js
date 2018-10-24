$(document).ready(function () {
    $("#addToCart").click(function (e) {
        e.preventDefault();
      // var pId=$("#productIdForCart").text();
      //   $.post(ctx+"/product/addToCart", $("#productForCart").text());
        var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var headers = {};
        headers[csrfHeader] = csrfToken;
        $.ajax({
            url: ctx+"/order/cart/add",
            dataType: 'json',
            type:'post',
            headers:headers,
            contentType:'application/json',
            data:JSON.stringify(Number($("#productForCart").text())),
            processData:false
        }
        )
            .done(function () { $("#myModal").modal('toggle')})
            .fail(function () { alert("the product wasn't added to your cart"); });
    })
});