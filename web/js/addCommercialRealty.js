
$(document).ready(function () {
     
//get select elemant 

    let cities = $('#cities');
    let incity = $('#incity');
    const citiesUrl = 'http://localhost:9090/finalPojest/getAllCityServlet?type=1';
    const incityUrl = 'http://localhost:9090/finalPojest/getAllCityServlet?type=2';
    fillList(cities, citiesUrl);
    fillList(incity, incityUrl);
    //fill dropdown with json
    function fillList(dropdown, url) {
        dropdown.empty();
        $.getJSON(url, function (data) {
            $.each(data, function (key, entry) {
                dropdown.append($('<option></option>').attr('value', entry.abbreviation).text(entry.name));
            });
        });
        dropdown.prop('selectedIndex', 0);

    }
    ;
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
// initaliaz options for map object 
    var opt = {
        center: {lat: 32.885353, lng: 13.180161},
        zoom: 11
    };
//create map object
    var map = new google.maps.Map(document.getElementById('map'), opt);
//create marker and add to map 
    var marker = new google.maps.Marker({
        position: {lat: 32.885353, lng: 13.180161},
        Draggable: true,
        map: map
    });
//places marker in spesfic position
    function placeMarker(latLng) {
        marker.setPosition(latLng);
        marker.setDraggable(true);
        marker.setMap(map);
    }
    ;
//click event for map to add marker in spesfic position    
    google.maps.event.addListener(map, 'click', function (e) {
        placeMarker(e.latLng);
    });
//drage event to change marker postion in nes position    
    google.maps.event.addListener(marker, 'dragend', function (e) {
        placeMarker(e.latLng);
    });

//submit add C-realty form
    $('#addRealty').click(function (e) {
        //get fileds and marker values 
       e.preventDefault();
        var realtyNumber = $('#realtyNumber').val(),
                description = $('#description').val(),
                lng = marker.getPosition().lat(),
                lat = marker.getPosition().lng(),
                address= $('#craddress').val(),
                realtyname= $('#realtyName').val(),
                licenseNumber= $('#CRlicensenumber').val(),
                CrDescription= $('#CRdescription').val(),
                categoryId = $('#catogoryList option:selected').val();

        $.ajax({
            url: "http://localhost:9090/finalPojest/addCommercialRealtyServlet",
            type: "POST",
            data: {
                realtyNumber: realtyNumber,
                description: description,
                lng: lng,
                lat: lat,
                address:address,
                realtyName:realtyname,
                licenseNumber:licenseNumber,
                CrDescription:CrDescription,
                categoryId:categoryId
            },
            dataType: "json",
            success: function (result) {
                if (result['key'] === -1) {
                    location.replace('http://localhost:9090/finalPojest/loginForm.jsp');
                } else if (result['key'] === 0) {
                    alert(result['message']);
                } else if (result['key'] === 1) {
                      alert(result['message']);
                }
            },
            error: function () {
                console.log("Error");
            }
        });

    });

});


