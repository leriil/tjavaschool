$(document).ready(function () {

    var sortOrder = "asc";

    $('[data-toggle="tooltip"]').tooltip();

    $(".logout").click(function (e) {
        e.preventDefault();
        clearCart();
        $(".logout-form").submit();
    });

    $("#allProductsTable th").click(function () {
        var column = $(this).text();
        if ($(this).attr('class') == "highlight") {
            if (sortOrder == "asc") {
                sortOrder = "desc";
            }
            else {
                sortOrder = "asc"
            }
        }
        else {
            $("#allProductsTable th").removeClass('highlight');
            $(this).toggleClass('highlight');
            sortOrder = "asc";
        }

        $.get(
            ctx + "/product/sort",
            {
                column: column,
                order: sortOrder
            },
            function (result) {

                $("#allProductsTable td").remove();
                drawTable(result);
            }
        );
    });

    $.ajax({
            url: ctx + "/product/sort",
            dataType: 'json',
            type: 'get',
            contentType: 'application/json',
            processData: false,
        }
    )
        .done(function (data) {
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

            if (result == 0) {
                $("#emp").attr("value", "true");
            } else {
                $("#emp").attr("value", "false")
            }
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
        });
    $("#user-birthdate").on('change',
        function () {
            if (!moment($("#user-birthdate").val(), 'YYYY-MM-DD', true).isValid()) {
                $("#valid-user-birthday").text("Invalid date format. Try using a calendar.");
                $("#btn-save").attr("disabled", "disabled");
            }
            else if (moment($("#user-birthdate").val(), 'YYYY-MM-DD', true).isValid()) {
                $("#valid-user-birthday").text("");
                $("#btn-save").removeAttr("disabled");
            }
        }
    );
});

function drawTable(data) {
    for (var i = 0; i < data.length; i++) {
        drawRowData(data[i]);
    }
}

function drawRowData(rowData) {
    var row = $("<tr />");
    $("#allProductsTable").append(row);
    var link = ctx + "/product/" + rowData.productId;
    row.append($("<td/>").html('<a href="' + ctx + '/product/' + rowData.productId + '">' + rowData.name + '</a>'));
    row.append($("<td>" + accounting.formatMoney(rowData.price, "") + "</td>"));
    row.append($("<td>" + getValue(rowData.weight) + "</td>"));
    row.append($("<td>" + getStringValue(rowData.color) + "</td>"));
    row.append($("<td>" + rowData.inStock + "</td>"));
    row.append($("<td>" + rowData.category.categoryName + "</td>"));
}

function repeatOrder(id) {
    var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var headers = {};
    headers[csrfHeader] = csrfToken;
    $.ajax({
            url: ctx + "/order/products",
            dataType: 'json',
            type: 'post',
            headers: headers,
            contentType: 'application/json',
            data: JSON.stringify(Number(id)),
            processData: false
        }
    )
        .done(function (data) {

            for (var i = 0; i < data.length; i++) {

                var productId = data[i].productId;

                $.ajax({
                        url: ctx + "/order/cart/add",
                        dataType: 'json',
                        type: 'post',
                        headers: headers,
                        contentType: 'application/json',
                        data: JSON.stringify(productId),
                        processData: false
                    }
                ).done(function () {
                    window.location = ctx + "/order/place";
                });
            }

        })
        .fail(function () {
            alert("no luck");
        });
}

function getValue(val) {
    if (val != null) return val;
    else return 0;
}

function getStringValue(val) {
    if (val != null) return val;
    else return '';
}

function clearCart() {
    $.get(ctx + "/order/cart/clear");
}



