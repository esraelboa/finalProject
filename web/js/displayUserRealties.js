$(document).ready(function () {
    $('#hideR').hide();
    $('#hideCR').hide();
    $('#realties').hide();
    $('#Crealties').hide();
    $('#Rmessage').hide();
    $('#CRmessage').hide();
//show realties table btn
    $('#showR').click(function () {
        $('#showR').hide();
        $('#hideR').show();
        $('#realties').show();
    });
//hide realties table btn
    $('#hideR').click(function () {
        $('#hideR').hide();
        $('#showR').show();
        $('#realties').hide();
    });
//show Crealties table btn
    $('#showCR').click(function () {
        $('#showCR').hide();
        $('#hideCR').show();
        $('#Crealties').show();
    });
//hide realties table btn
    $('#hideCR').click(function () {
        $('#hideCR').hide();
        $('#showCR').show();
        $('#Crealties').hide();
    });

    //get all Realties
    var realties = [];
    $.ajax({
        url: " http://localhost:9090/finalPojest/getAllUserrRealtiesServlet",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result['key'] === -1) {
                alert(result['message']);
            } else {
                if (result['key'] !== 0) {
                    for (var i = 0; i < result['realties'].length; i++) {
                        var obj = {"id": result['realties'][i].realtyid,
                            "رقم العقار": result['realties'][i].realtynumber,
                            "العنوان": result['realties'][i].address,
                            " ": '<a class="btn" href="DisplayRealtyinfo.jsp?id=' + result['realties'][i].realtyid + '">عرض تفاصيل</a>',
                            "": '<button class="btn AddResbtn" data-toggle="modal" data-target="#addResidentModal" value="' + result['realties'][i].realtyid + '" >اضافة عنوان فرعي</button>'};
                        realties.push(obj);
                    }
                    createRealtiesTable(realties);
                } else {
                    $('#Rmessage').html('لايوجد لديك عقارات تملكها <a href="addRealty.jsp">اضف الان</a>');
                }
            }
        },
        error: function () {
            console.log("Error");
        }
    });
//create table of all realties data 
    function createRealtiesTable(list) {
//create table
        var table = '';
//        create table head
        var th = '<tr><th></th><th>رقم العقار</th><th>العنوان</th><th> </th><th> </th></tr>';
//marge it all togther table head  with thable 
        table += th;
//create  table rows and marge it all togher
        for (var row = 0; row < list.length; row++) {
            table += '<tr>';
            table += '<td>' + (row + 1) + '</td>';
            table += '<td>' + list[row]['رقم العقار'] + '</td>';
            table += '<td>' + list[row]['العنوان'] + '</td>';
            table += '<td>' + list[row][' '] + '</td>';
            table += '<td>' + list[row][''] + '</td>';
            table += '</tr>';
        }
        table += '</table>';
        $('#realtiesData').html(table);
    }

    fillDropDownWitheCategories();
//fill dropDownList with Categories
    function fillDropDownWitheCategories() {
        $('#catogories').empty();
        var optiones = '<option value="0">التصنيفات</option>';
        var catURL = 'http://localhost:9090/finalPojest/getAllCategoriesServlet';
        $.getJSON(catURL, function (data) {
            for (var i = 0; i < data.length; i++) {
                optiones += '<option value="' + data[i].catid + '">' + data[i].name + '</option>';
            }
            $('#catogoryList').html(optiones);
        });
    }
    ;

    var insertedId;

    $('#addResident').click(function (e) {
        var realtyid = $('.AddResbtn').val(),
                email = $('#email').val(),
                address = $('#resAddress').val(),
                realtytype = $("input[type='radio']:checked").val();

        e.preventDefault();

        $.ajax({
            url: "http://localhost:9090/finalPojest/InsertResidentServlet",
            type: 'POST',
            data: {
                realtyid: realtyid,
                email: email,
                address: address,
                realtytype: realtytype
            },
            dataType: "json",
            success: function (result) {
                if (result['key'] === 1) {
                    if (realtytype === '1') {
                        $('#addCommercialRealtiesModal').modal('show');
                        $('#addResidentModal').modal('hide');
                        insertedId = result['id'];

                    } else if (realtytype === '0') {
                        alert('تمت عملية بنجاح');
                    }
                } else {
                    console.log(result['message']);
                }
            },
            error: function () {
                console.log("Error");
            }
        });

    });
    $('#addCommercialRealties').click(function (e) {
        var realtyname = $('#realtyName').val(),
                licensenumber = $('#CRlicensenumber').val(),
                description = $('#CRdescription').val(),
                selectedOpt = $('#catogoryList option:selected').val();

        e.preventDefault();
        $.ajax({
            url: "http://localhost:9090/finalPojest/InsertCommercialRealtiesServlet",
            type: 'POST',
            data: {
                realtyName: realtyname,
                licenseNumber: licensenumber,
                description: description,
                residentId: insertedId,
                categoryId: selectedOpt
            },
            dataType: "json",
            success: function (result) {
                if (result['key'] === -1) {
                    alert("الرجاء تسجيل الدخول");
                } else if (result['key'] === 0) {
                    alert("خطا الرجاء المحاولة مرة اخرى");
                } else if (result['key'] === 1) {
                    alert('تمت عملية بنجاح');
                    $('#addCommercialRealtiesModal').modal('hide');
                }
            },
            error: function () {
                console.log("Error");
            }

        });
    });

    // display C-Realties 
    $.ajax({
        url: "http://localhost:9090/finalPojest/getAllCommercialRealties",
        type: 'GET',
        data: {

        },
        dataType: "json",
        success: function (result) {
            if(result['key']!==0){
            var Crealties = result['realties'];
            var table = '';
            var th = '<tr><th></th><th>رقم الرخصة</th><th>الاسم التجاري</th><th></th><th></th><tr>';
            table += th;
            for (var i = 0; i < Crealties.length; i++) {
                //console.log(Crealties[i].realtyName);
                table += '<tr>';
                table += '<td>' + (i + 1) + '</td>';
                table += '<td>' + Crealties[i].licenseNumber + '</td>';
                table += '<td>' + Crealties[i].realtyName + '</td>';
                table += '<td><a class="btn" href="displayCommercialRealtyInfo.jsp?id='+Crealties[i].id+'"> عرض تفاصيل</a>';
                table += '</tr>';
            }
            table += '</table>';
            $('#CRrealtiesData').html(table);
        }else{
            $('#CRmessage').html('لايجود لديك عقارات تجارية،يمكنك <a href="addRealty.jsp">اضافة عقار</a> وجعله تجاري');
        }
        },
        error: function () {
            console.log("Error");
        }
    });

});
