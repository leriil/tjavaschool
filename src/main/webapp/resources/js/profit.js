$(document).ready(function () {

    $.get(
        ctx + "/order/stats/profit",
        {
            period: 'week'
        },
        function (result) {
            $("#profitTable td").remove();
            drawProfitTable(result);
        }
    );

    $("#profitSelect").change(function () {
        var period = $('#profitSelect :selected').val();
        var firstDay = null;
        var lastDay = null;
        getProfitByPeriod(period, firstDay, lastDay);
    });


    $("#profitButton").click(function () {
        var period = null;
        var firstDay = $("#profit-first-day").val();
        var lastDay = $("#profit-last-day").val();
        if (moment(firstDay, 'YYYY-MM-DD', true).isValid() && moment(lastDay, 'YYYY-MM-DD', true).isValid()) {
            getProfitByPeriod(period, firstDay, lastDay);
        }
        else {
            $("#validDate").text("Incorrect date format. Try using the calendar.");
        }

    });
});

function drawProfitTable(data) {
    var totalSum = 0;
    var totalItems = 0;
    for (var i = 0; i < data.length; i++) {
        drawRowDataProfit(data[i]);
        totalSum += data[i].profit;
        totalItems += data[i].itemsSold;
    }
    drawTotalRow(totalSum, totalItems);
}

function drawRowDataProfit(rowData) {
    var row = $("<tr />");

    $("#profitTable").append(row);

    row.append($("<td>" + makeDate(rowData.date) + "</td>"));
    row.append($("<td>" + accounting.formatMoney(rowData.profit, "") + "</td>"));
    row.append($("<td>" + rowData.itemsSold + "</td>"));


}

function makeDate(date) {
    return date.year + "-" + date.monthValue + "-" + date.dayOfMonth;
}

function getProfitByPeriod(period, firstDay, lastDay) {
    $.get(
        ctx + "/order/stats/profit",
        {
            period: period,
            firstDay: firstDay,
            lastDay: lastDay
        },
        function (result) {
            $("#profitTable td").remove();
            drawProfitTable(result);
        }
    );
}

function drawTotalRow(sum, items) {
    var row = $("<tr />", {'style': 'font-weight: bold'});

    $("#profitTable").append(row);

    row.append($("<td/>"));
    row.append($("<td>" + accounting.formatMoney(sum, "") + "</td>"));
    row.append($("<td>" + items + "</td>"));
}
