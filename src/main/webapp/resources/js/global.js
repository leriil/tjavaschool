$(document).ready(function () {

    var sortOrder="asc";

    $(".logout").click(function (e) {
        e.preventDefault();
        $(".logout-form").submit();
    });

    $("#allProductsTable th").click(function(e){
        var column = $(this).text();
        if($(this).attr('class')=="highlight"){
            if(sortOrder=="asc"){
                sortOrder="desc";
            }
            else{sortOrder="asc"}
        }
        else {
            $("#allProductsTable th").removeClass('highlight');
            $(this).toggleClass('highlight');
        sortOrder="asc";}

        $.get(
            ctx+"/product/sort",
            {
                column:column,
                order:sortOrder
            },
            function (result) {

                $("#allProductsTable td").remove();
                drawTable(result);
            }
        );
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

    //TODO: what if we remove .open() and .close()?
    $("#selectSort").change(function () {
        $.get(
            ctx + "/product/all",
            {
                sortingOption: $("#selectSort :selected").val(),
                sortingOrder: $("#sortIcon").attr('class')
            },
            function (result) {
                document.open();
                document.write(result);
                document.close();
            }

        );
        });


    $.ajax({
        url: ctx+"/product/sort",
        dataType:'json',
        type: 'get',
        contentType: 'application/json',
        processData:false,
        }

    )
        .done(function(data){
            drawTable(data);
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

            if(result==0){
                $("#emp").attr("value","true");
            }else{$("#emp").attr("value","false")}
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
function drawTable(data) {
    for (var i = 0; i <data.length ; i++) {
        drawRowData(data[i]);
    }
}
function drawRowData(rowData){
    var row = $("<tr />");
    $("#allProductsTable").append(row);
    row.append($("<td>" + rowData.name + "</td>"));
    row.append($("<td>" + rowData.price + "</td>"));
    row.append($("<td>" + rowData.weight + "</td>"));
    row.append($("<td>" + rowData.volume + "</td>"));
    row.append($("<td>" + rowData.inStock + "</td>"));
    row.append($("<td>" + rowData.category + "</td>"));
}

