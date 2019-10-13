$(document).ready(function () {
    //get all Realties
    var realties = [];
    $.ajax({
        url: " http://localhost:8080/finalPojest/getAllUserrRealtiesServlet",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result['key'] === -1) {
                alert(result['message']);
            } else {

                for (var i = 0; i < result['realties'].length; i++) {
                    var obj = {"id": result['realties'][i].realtyid,
                        "رقم العقار": result['realties'][i].realtynumber,
                        "العنوان": result['realties'][i].address,
                        " ": '<a class="btn" href="DisplayRealtyinfo.jsp?id='+result['realties'][i].realtyid +'">عرض تفاصيل</a>',
                        "": '<button class="btn AddResbtn" data-toggle="modal" data-target="#addResidentModal" value="'+result['realties'][i].realtyid+'" >اضافة ساكن</button>'};
                    realties.push(obj);
                }
                createRealtiesTable(realties);
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
            table += '<td>' + (row+1) + '</td>';
               table += '<td>' + list[row]['رقم العقار'] + '</td>';
                table += '<td>' + list[row]['العنوان'] + '</td>';
                table += '<td>' + list[row][' '] + '</td>';
                table += '<td>' + list[row][''] + '</td>';
              table += '</tr>';
        }
        table += '</table>';
      $('#realtiesData').html(table);
    }
 $('.AddRes').click(function(){
     console.log("clicked");
 });
    
 $('#addResident').click(function () {
  var realtyid= $('.AddResbtn').val(),
      email=$('#email').val(),
      address=$('#address').val(),
      realtytype=$('#realtytype').val();
      
        $.ajax({
            url: "http://localhost:8080/finalPojest/InsertResidentServlet",
            type: 'POST',
            data: {
                realtyid:realtyid,
                email: email,
                address: address,
                realtytype: realtytype
            },
            dataType: "json",
            success: function (result) {
                if (result['key'] === -1) {
                    alert("الرجاء تسجيل الدخول");
                } else if (result['key'] ===0) { 
                     alert("ﻻ يوجد مستخدم بهذا البريد الالكتروني");
                }else if (result['key'] === -2){
                    alert('عنوان مكرر ');
                }else if(result['key'] === 1){
                     alert('تمت عملية بنجاح');
                }
            },
            error: function () {
                console.log("Error");
            }
        });

        });
             
});