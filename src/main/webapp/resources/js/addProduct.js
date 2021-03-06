$(function () {

    $.get(
        ctx + "/product/categories",
        function (result) {
            addOptions(result);
            addDeviderAndUserOption();
            addCategoryToProduct();
        }
    );
    $('.selectpicker').change(function () {
        addCategoryToProduct();
    });

});

function addCategoryToProduct() {
    // $('#addProductForReview').click(function () {
            var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var headers = {};
            headers[csrfHeader] = csrfToken;
            var value=$(":selected").val();
            var text=$(":selected").text();
            $.ajax({
                    url: ctx + "/product/category/add",
                    dataType: 'json',
                    type: 'post',
                    headers: headers,
                    contentType: 'text/plain',
                    data: value

                }
            );
}

function addDeviderAndUserOption() {
    var content = "<input type='text' class='bss-input' onKeyDown='event.stopPropagation();' onKeyPress='addSelectInpKeyPress(this,event)' onClick='event.stopPropagation()' placeholder='Add item'> <span class='glyphicon glyphicon-plus addnewicon' onClick='addSelectItem(this,event,1);'></span>";

    var divider = $('<option/>')
        .addClass('divider')
        .data('divider', true);

    var addoption = $('<option/>', {class: 'addItem'})
        .data('content', content);

    $('.selectpicker')
        .append(divider)
        .append(addoption)
        .selectpicker('refresh');
}

function addOptions(options) {
    for (var i = 0; i < options.length; i++) {
        var option = $('<option/>').text(options[i].categoryName);
        $('.selectpicker')
            .append(option);
    }
    $(".selectpicker :first").attr("selected", "selected");
    $('.selectpicker').selectpicker('refresh');
}

function addSelectItem(t, ev) {
    ev.stopPropagation();
    var bs = $(t).closest('.bootstrap-select');
    var txt = $(t).prev().val().replace(/[|]/g, "").toUpperCase();
    if ($.trim(txt) == '') return;
    var p = bs.find('select');
    var o = $('option', p).eq(-2);
    o.before($("<option>", {
        "selected": true,
        "text": txt
    }));
    p.selectpicker('refresh');
    addCategoryToProduct();

}

function addSelectInpKeyPress(t, ev) {
    ev.stopPropagation();
    if (ev.which == 124) ev.preventDefault();

    if (ev.which == 13) {
        ev.preventDefault();
        addSelectItem($(t).next(), ev);
    }
}