$(document).ready(function () {
    $('#hideRealtyResidents').hide();
    //get id form url function  
    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
            vars[key] = value;
        });
        return vars;

    }
    ;
    var id = getUrlVars()["id"];


    var opt = {
        center: {lat: 32.885353, lng: 13.180161},
        zoom: 11
    };
//create map object
    var map = new google.maps.Map(document.getElementById('map'), opt);
//create marker and add to map 
    var marker = new google.maps.Marker({
        map: map
    });
//places marker in spesfic position
    function placeMarker(latLng) {
        marker.setPosition(latLng);
        marker.setDraggable(true);
        marker.setMap(map);
    }
    ;

    var realtyid = id;
    $.ajax({
        url: "http://localhost:9090/finalPojest/DisplayRealtyinfo",
        type: "GET",
        data: {
            realtyid: realtyid
        },
        dataType: "json",
        success: function (result) {
            $('#realtyNumber').text(result['realtynumber']);
            $('#realtyaddress').text(result['address']);
            $('#description').text(result['description']);

            var latlng = {lng: result['lat'], lat: result['lng']};
            map.setCenter(latlng);
            map.setZoom(17);
            placeMarker(latlng);

        },
        error: function () {
            console.log("Error");
        }
    });
    $('#showRealtyResidents').click(function (e) {
        $('#showRealtyResidents').hide();
        $('#hideRealtyResidents').show();
        console.log(realtyid);
        e.preventDefault();
        $.ajax({
            url: "http://localhost:9090/finalPojest/getAllRealtyResidents",
            type: "GET",
            data: {
                realtyId: realtyid
            },
            dataType: "json",
            success: function (result) {
                if (result['key'] === 0) {
                    $('#alertmessage').html('ﻻيوجد مستأجرين لهذا العقار<a class="mr-3"href="displayUserRealties.jsp">اضف مستأجر الان</a>');
                } else {
                    var table = '<table>', row = 0;
                    var th = '<tr><th></th><th>نوع النشاط</th><th>العنوان</th></tr>';
                    table += th;
                    var realtytype = {0: "سكني", 1: "تجاري"};
                    for (var i = 0; i < result['realties'].length; i++) {
                        table += '<tr>';
                        table += '<td>' + (row + 1) + '</td>';
                        table += '<td><span class="badge badge-primary badge-pill">' + realtytype[result['realties'][i].realtytype] + '</span></td>';
                        table += '<td>' + result['realties'][i].address + '</td>';
                        table += '</tr>';
                        row += 1;
                    }
                    table += '</table>';
                    $('#allresident').html(table);
                    $('#allresident').show();
                }


            },
            error: function () {
                console.log("Error");
            }
        });

    });
    $('#hideRealtyResidents').click(function (e) {
        $('#hideRealtyResidents').hide();
        $('#allresident').hide();
        $('#showRealtyResidents').show();
    });

});

