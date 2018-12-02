$(document).ready(function () {
    var page = new Page();
    page.init();
});

function Page() {
}

Page.prototype.init = function () {
    $(".input-append").datepicker({
        autoclose: true,
        beforeShow: function (input, inst) {
            var rect = input.getBoundingClientRect();
            setTimeout(function () {
                inst.dpDiv.css({top: rect.top + 40, left: rect.left + 0});
            }, 0);
        }
    });
};
