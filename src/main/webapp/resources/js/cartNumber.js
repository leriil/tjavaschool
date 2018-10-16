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
            url: ctx+"/product/addToCart",
            dataType: 'json',
            type:'post',
            headers:headers,
            contentType:'application/json',
            data:JSON.stringify(Number($("#productForCart").text())),
            processData:false
        //     success: function () {
        //         // $("#myModal").modal('toggle');
        //         alert("perfect");
        //     },
        //     error: function () {
        //         alert("problem with adding the product to the cart");
        //     }
        }
        )
            .done(function () { $("#myModal").modal('toggle'); })
            .fail(function () { alert("problem"); });
    })
});