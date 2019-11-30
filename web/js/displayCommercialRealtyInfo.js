$(document).ready(function () {
    $('#UpdateCRForm').hide();
    var lnumber,
            name,
            description,
            catname;
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
    showCRinf();
    function showCRinf() {
        $.ajax({
            url: "http://localhost:9090/finalPojest/DisplayCommercialRealtyInfo",
            type: "GET",
            data: {
                id: id
            },
            dataType: "json",
            success: function (result) {
                lnumber = result['licenseNumber'],
                        name = result['realtyName'],
                        description = result['description'],
                        catname = result['category'];
                //fill form fileds
                $('#CRlicenseNumber').text(lnumber);
                $('#CRname').val(name);
                $('#CRdes').text(description);
                //fill view fileds
                $('#licenseNumber').text(lnumber);
                $('#CRrealtyname').text(name);
                $('#CRdescription').text(description);
                $('#cattype').text(result['category']);
                var latlng = {lng: result['lat'], lat: result['lng']};
                map.setCenter(latlng);
                map.setZoom(17);
                placeMarker(latlng);

            },
            error: function () {
                console.log("Error");
            }
        });
    }

    function fillWitheCategories() {
        $('#CRcatogories').empty();

        var optiones = '<option value="0">التصنيفات</option>';
        var catURL = 'http://localhost:9090/finalPojest/getAllCategoriesServlet';
        $.getJSON(catURL, function (data) {
            for (var i = 0; i < data.length; i++) {
                optiones += '<option value="' + data[i].catid + '">' + data[i].name + '</option>';
              
            }
            $('#CRcatogories').html(optiones);
        });
        $('select option[value="0"]').attr("selected", true);

    }
    ;
    $('#update').click(function () {
        $('#displayCRinfo').hide();
        fillWitheCategories();
        $('#UpdateCRForm').show();
    });

    $('#cancelCRupdate').click(function (e) {
        e.preventDefault();
        $('#UpdateCRForm').hide();
        $('#displayCRinfo').show();
    });

//update CR info
    $('#saveCRupdate').click(function (e) {
        e.preventDefault();
        var name = $('#CRname').val(),
                description = $('#CRdes').val(),
                catid = $('#CRcatogories option:selected').val();
        $.ajax({
            url: "http://localhost:9090/finalPojest/UpdateCommercialRealtyInfo",
            type: "GET",
            data: {
                id: id,
                realtyName: name,
                description: description,
                catId: catid
            },
            dataType: "json",
            success: function (result) {
                alert('تم التعديل بنجاح');
                $('#UpdateCRForm').hide();
                showCRinf();
                $('#displayCRinfo').show();
            },
            error: function () {
                console.log("Error");
            }
        });


    });
});



