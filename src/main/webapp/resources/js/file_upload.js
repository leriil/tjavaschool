$(function () {
    $("#fileUpload").change(function (e) {
        var fileName = e.target.files[0].name;
        $("#fileName").attr('value', fileName);
    });
});