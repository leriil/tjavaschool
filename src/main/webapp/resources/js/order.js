$(document).ready(function () {
        $("#order-repeat-save").click()(function (e) {
            e.preventDefault();
            $("#order-repeat").submit();
        })
    }
);