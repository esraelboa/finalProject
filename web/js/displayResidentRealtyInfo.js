$(document).ready(function () {
    //   get resident realty info   
    $.ajax({
        url: " http://localhost:8080/finalPojest/getResidentInfoServlet",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result['key'] === -1) {
                alert(result['message']);
            } else if (result['key'] === 1) {
                $('#residentId').html(result['id']);
                $('#address').html(result['address']);
                $('#description').html(result['description']);
            }
        },
        error: function () {
            console.log("Error");
        }
    });


    $('#update').click(function () {
        var description = $('#textdescription').val(),
                residentid = $('#residentId').html();
        $.ajax({
            url: " http://localhost:8080/finalPojest/UpdateResidentInfoServlet",
            type: 'POST',
            data: {
                description: description,
                residentid: residentid
            },
            dataType: "json",
            success: function (result) {
                if (result['key'] === -1) {
                    alert("الرجاء تسجيل الدخول");
                } else if (result['key'] === 1) {                   
                }
            },
            error: function () {
                console.log("Error");
            }
        });
      
        var $modal = $('#residentRealtyData');
        $modal.on('click', '#update', function () {
            $modal.modal("hide");
            $modal.on("hidden.bs.modal", function () {
               alert("تمت العملية بنجاح");
                location.href = 'http://localhost:8080/finalPojest/displayResidentRealtyInfo.jsp';
            });
        });
    });
});

