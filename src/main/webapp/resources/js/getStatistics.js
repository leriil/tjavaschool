$(function () {
    getStatistics();
    var topLength = 10;

    $("#orderOfSort").click(function (e) {
        e.preventDefault();
        var $icon = $("#sortIcon");
        if ($icon.hasClass('glyphicon glyphicon-sort-by-attributes-alt')) {
            $icon.removeClass('glyphicon-sort-by-attributes-alt');
            $icon.addClass('glyphicon-sort-by-attributes');
        }
        else {
            $icon.removeClass('glyphicon-sort-by-attributes');
            $icon.addClass('glyphicon-sort-by-attributes-alt');
        }
        getStatistics();
    });

    $("#selectSort").change(function () {
        getStatistics();
    });
});

function getStatistics() {
    var target;
    if ($("#selectSort :selected").val() == "10 products") {
        target = "/product";
    }
    else if ($("#selectSort :selected").val() == "10 clients") {
        target = "/user";
    }
    $.get(
        ctx + target + "/find/top",
        {
            order: $("#sortIcon").attr('class')
        },
        function (result) {
            drawProductsTable(result, target);
        }
    );
}

function drawProductsTable(data, target) {

    $('#statisticsTable th').remove();
    $('#statisticsTable td').remove();
    if (target == '/product') {

        drawRowDataProductHeader();

        var i = 0;
        for (i = 0; i <= 10; i++) {
            drawRowDataProduct(data[i]);
        }
    }
    else {
        drawRowDataUserHeader();

        for (i = 0; i <= 10; i++) {
            drawRowDataUser(data[i]);
        }
    }

}

function drawRowDataProduct(rowData) {
    var row = $("<tr />");
    $("#statisticsTable").append(row);
    var link = ctx + "/product/" + rowData.productId;
    row.append($("<td/>").html('<a href="' + ctx + '/product/' + rowData.productId + '">' + '# ' + rowData.productId + '</a>'));
    row.append($("<td>" + rowData.name + "</td>"));
    row.append($("<td>" + rowData.categoryName + "</td>"));
    row.append($("<td>" + rowData.inStock + "</td>"));
    row.append($("<td>" + accounting.formatMoney(rowData.price, "") + "</td>"));
    row.append($("<td>" + getValue(rowData.top) + "</td>"));
}

function drawRowDataUser(rowData) {
    var row = $("<tr />");
    $("#statisticsTable").append(row);
    var link = ctx + "/user/" + rowData.userId;
    row.append($("<td/>").html('<a href="' + ctx + '/user/' + rowData.userId + '">' + '# ' + rowData.userId + '</a>'));
    row.append($("<td>" + rowData.name + "</td>"));
    row.append($("<td>" + rowData.surname + "</td>"));
    row.append($("<td>" + rowData.email + "</td>"));
    row.append($("<td>" + accounting.formatMoney(rowData.moneySpent, "") + "</td>"));
}

function drawRowDataProductHeader() {

    var row = $('<tr/>');
    $('#statisticsTable').append(row);
    row.append($('<th/>').text('Id'));
    row.append($('<th/>').text('Name'));
    row.append($('<th/>').text('CategoryName'));
    row.append($('<th/>').text('in Stock'));
    row.append($('<th/>').text('Price'));
    row.append($('<th/>').text('Sold'));
}

function drawRowDataUserHeader() {

    var row = $('<tr/>');
    $('#statisticsTable').append(row);
    row.append($('<th/>').text('Id'));
    row.append($('<th/>').text('Name'));
    row.append($('<th/>').text('Surname'));
    row.append($('<th/>').text('E-mail'));
    row.append($('<th/>').text('Money spent'));
}



