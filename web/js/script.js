$(document).ready(function () {
    fillDropDownWitheCategories();
    function fillDropDownWitheCategories() {
        $('#catogories').empty();
        var optiones = '<option value="0">التصنيفات</option>';
        var catURL = 'http://localhost:9090/finalPojest/getAllCategoriesServlet';
        $.getJSON(catURL, function (data) {
            for (var i = 0; i < data.length; i++) {
                optiones += '<option value="' + data[i].catid + '">' + data[i].name + '</option>';
            }
            $('#catogories').html(optiones);
        });
    }
    ;

    $('#search').submit(function (e) {
        //get fileds and marker values 
        var catid = $('#catogories option:selected').val();
        var address = $('#address').val();
        console.log(catid);
        var a = address.split(".");
        if (address === ''& catid==='0') {
            e.preventDefault();
            alert('الحقل فارغ الرجاء ادخال العنوان الالكتروني للعقار او اختر تصنيف');
        } else {
            e.preventDefault();
            location.href = 'http://localhost:9090/finalPojest/search.jsp?address='+ address+'&catid='+catid;
        }
    });
});
