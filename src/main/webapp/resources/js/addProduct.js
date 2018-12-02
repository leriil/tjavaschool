$(function() {
    $.get(
        ctx+"/product/categories",
        function (result) {
           addOptions(result);
           addDeviderAndUserOption();
           // addCategoryToProduct();
            }
    );
    addCategoryToProduct();


});
function addCategoryToProduct() {
    $('#addProductForReview').click(function () {
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
                    // processData: false
                }
            );
        }
    );
}
function addDeviderAndUserOption(){
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
    $('.selectpicker').selectpicker('refresh');
}

function addSelectItem(t,ev)
{
    ev.stopPropagation();

    var bs = $(t).closest('.bootstrap-select');
    // var txt=bs.find('.bss-input').val().replace(/[|]/g,"").toUpperCase();
    var txt=$(t).prev().val().replace(/[|]/g,"").toUpperCase();
    if ($.trim(txt)=='') return;

    // Changed from previous version to cater to new
    // layout used by bootstrap-select.
    var p=bs.find('select');
    var o=$('option', p).eq(-2);
    o.before( $("<option>", {
        "selected": true,
        "text": txt}) );
    p.selectpicker('refresh');
}

function addSelectInpKeyPress(t,ev)
{
    ev.stopPropagation();

    // do not allow pipe character
    if (ev.which==124) ev.preventDefault();

    // enter character adds the option
    if (ev.which==13)
    {
        ev.preventDefault();
        addSelectItem($(t).next(),ev);
    }
}