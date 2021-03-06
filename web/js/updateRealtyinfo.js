$(document).ready(function () {
    $('#updatedescription').hide();
    $('#lid').hide();
    $('#subupdate').hide();
    $('#cancelUpateDes').hide();
    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
            vars[key] = value;
        });
        return vars;
    }
    ;
    $('#update').click(function (e) {
        e.preventDefault();
        $('#des').hide();
        $('#description').hide();
        $('#update').hide();
        $('#cancelUpateDes').show();
        $('#updatedescription').show();
        $('#lid').show();
        $('#subupdate').show();

    });

    $('#cancelUpateDes').click(function (e) {
        e.preventDefault();
        $('#des').show();
        $('#description').show();
        $('#updatedescription').hide();
        $('#lid').hide();
        $('#subupdate').hide();
        $('#cancelUpateDes').hide();
        $('#update').show();
    });
    $('.Display-realty').submit(function (e) {

        var id = getUrlVars()["id"];
        var description = $('#updatedescription').val();
        e.preventDefault();
        var that = $(this);
        $.ajax({
            url: 'http://localhost:9090/finalPojest/UpdateRealtyinfo',
            type: "GET",
            data: {
                id: id,
                description: description},
            dataType: "json",
            success: function (result) {
                if (result['key'] === -1) {
                    alert("الرجاء تسجيل الدخول");
                } else
                if (result['key'] === 1) {
                    Swal.fire({
                        type: 'error',
                        title: result['message'],
                        closeOnConfirm: false,
                        animation: "slide-from-top"
                    });

                    location.replace('http://localhost:9090/finalPojest/DisplayRealtyinfo.jsp' + "?id=" + id);
                } else if (result['key'] === 0) {
                    Swal.fire({
                        type: 'error',
                        title: result['message'],
                        closeOnConfirm: false,
                        animation: "slide-from-top"
                    });

                }
            },
            error: function () {
                console.log("Error");
                alert("error");
            }
        });
    });
});
