$(document).ready(function () {

    $('#hideAddCategories').hide();
    $('#hideAllCategories').hide();
    $('#insertCategory').hide();
    $('#allCategoriesSection').hide();
//show add categories form
    $('#showAddCategories').click(function () {
        $('#showAddCategories').hide();
        $('#hideAddCategories').show();
        $('#insertCategory').show();
    });
// hide add categories form 
    $('#hideAddCategories').click(function () {
        $('#hideAddCategories').hide();
        $('#showAddCategories').show();
        $('#insertCategory').hide();
    });
// cancel add categories 
    $('#cancelAddCategory').click(function () {
        $('#hideAddCategories').hide();
        $('#showAddCategories').show();
        $('#insertCategory').hide();
    });
    //submit add Category form
    $('#insertCategory').submit(function (e) {
        var categoryname = $('#name').val();
        e.preventDefault();
        $.ajax({
            url: "http://localhost:9090/finalPojest//insertCategoryServlet",
            type: 'POST',
            data: {
                name: categoryname
            },
            dataType: "json",
            success: function (result) {
                if (result['key'] === 1) {
                    alert("تمت العملية بنجاح");
                    fillallCategoriestable();
                    $('#allCategoriesSection').show();
                } else {
                    console.log(result['message']);
                }
            },
            error: function () {
                console.log("Error");
            }
        });
    });
//show all categories
    $('#showAllCategories').click(function (e) {
        $('#showAllCategories').hide();
        $('#hideAllCategories').show();
        $('#allCategoriesSection').show();

    });
//hide all categories
    $('#hideAllCategories').click(function () {
        $('#hideAllCategories').hide();
        $('#showAllCategories').show();
        $('#allCategoriesSection').hide();
    });
    //get All Categoriesss table
    fillallCategoriestable();
    function fillallCategoriestable() {
        $('#allCategories').html(' ');
        $.ajax({
            url: "http://localhost:9090/finalPojest/getAllCategoriesServlet",
            type: 'GET',
            data: {

            },
            dataType: "json",
            success: function (result) {

                var table = '';
                var th = '<tr><th></th><th>الاسم</th><th></th><th></th></tr>';
                table += th;
                for (var i = 0; i < result.length; i++) {
                    table += '<tr>';
                    table += '<td>' + (i + 1) + '</td>';
                    table += '<td>' + result[i].name + '</td>';
                    table += '<td><button class="btn btnCatId" value="' + result[i].catid + '">تعديل الاسم </button></td>';
                    table += '</tr>';
                }
                table += '';
                $('#allCategories').html(table);
            },
            error: function () {
                console.log("Error");
            }
        });
    }
//search in all categories table
    $("#input").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#allCategories tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });
    var categoryid;
    $('#allCategories').on('click', 'button', function () {
        categoryid = $(this).val();
        $('#UpdateCategoryName').modal('show');
    });

//update Category name 
    $('#updatecatName').click(function (e) {
        var name = $('#inputname').val();
        $.ajax({
            url: "http://localhost:9090/finalPojest/UpdateCategoryServlet",
            type: 'POST',
            data: {
                name: name,
                catid: categoryid
            },
            dataType: "json",
            success: function (result) {
                if (result['key'], 1) {
                    alert("تمت العملية بنجاح");
                    $('#UpdateCategoryName').modal('hide');
                    fillallCategoriestable();
                } else {
                    alert("خطا الرجاء المحاولة مرة اخرى");
                }
            },
            error: function () {
                console.log("Error");
            }
        });
    });

});

