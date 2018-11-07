$(document).ready(function () {
    $(".logout").click(function (e) {
        e.preventDefault();
        $(".logout-form").submit();
    });
    $("#orderOfSort").click(function (e) {
        e.preventDefault();
        if($("#sortIcon").hasClass('glyphicon glyphicon-sort-by-attributes-alt')){
            $("#sortIcon").removeClass('glyphicon-sort-by-attributes-alt');
            $("#sortIcon").addClass('glyphicon-sort-by-attributes');
        }
        else{
            $("#sortIcon").removeClass('glyphicon-sort-by-attributes');
            $("#sortIcon").addClass('glyphicon-sort-by-attributes-alt');
        }
    });

    $.ajax({
            url: ctx + "/order/cart/count",
            dataType: 'json',
            type: 'get',
            contentType: 'application/json',
            processData: false
        }
    )
        .done(function (result) {
            $("#basket").html(result);
        })
        .fail(function () {
            alert("the product wasn't removed from your cart");
        });
    $.ajax({
            url: ctx + "/order/cart/total",
            dataType: 'json',
            type: 'get',
            contentType: 'application/json',
            processData: false
        }
    )
        .done(function (result) {
            $("#total").html(result);
        })
        .fail(function () {
            alert("total wasn't estimated");
        });

});