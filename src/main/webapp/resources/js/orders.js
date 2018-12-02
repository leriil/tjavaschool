$(document).ready(function () {

    $.get(
        ctx + "/order/find/all", function (result) {
            $("#allOrdersTable td").remove();
            drawOrdersTable(result);
        }
    );
    $("#getOrders").click(function () {

        var firstDay = $("#first-day").val();
        var lastDay = $("#last-day").val();
        if (moment(firstDay, 'YYYY-MM-DD', true).isValid() && moment(lastDay, 'YYYY-MM-DD', true).isValid()) {
            getOrdersByPeriod(firstDay, lastDay);
        }
        else {
            $("#validDateOrders").text("Incorrect date format. Try using the calendar.");
        }
    })
});

function drawOrdersTable(data) {
    for (var i = 0; i < data.length; i++) {
        drawRowDataOrder(data[i]);
    }
}

function drawRowDataOrder(rowData) {
    var row = $("<tr />");
    $("#allOrdersTable").append(row);
    // var link=ctx+"/order/"+rowData.orderId;
    row.append($("<td/>").html('<a href="' + ctx + '/order/' + rowData.orderId + '">' + '# Order ' + rowData.orderId + '</a>'));
    row.append($("<td>" + rowData.user.login + "</td>"));
    row.append($("<td>" + rowData.paymentMethod + "</td>"));
    row.append($("<td>" + rowData.deliveryMethod + "</td>"));
    row.append($("<td>" + rowData.paymentStatus + "</td>"));
    row.append($("<td>" + rowData.orderStatus + "</td>"));
    row.append($("<td>" + makeDate(rowData.orderDate) + "</td>"));

}

function makeDate(date) {
    return date.year + "-" + date.monthValue + "-" + date.dayOfMonth;
}

function getOrdersByPeriod(first, last) {
    $.get(
        ctx + "/order/find/all/dates",
        {
            firstDay: first,
            lastDay: last
        },
        function (result) {
            $("#allOrdersTable td").remove();
            drawOrdersTable(result);
        }
    );
}