
$(document).ready(function () {
//get select elemant 
    let cities = $('#cities');
    let incity = $('#incity');
    const citiesUrl = 'http://localhost:9090/finalPojest/getAllCityServlet?type=1';
    const incityUrl = 'http://localhost:9090/finalPojest/getAllCityServlet?type=2';
    fillList(cities, citiesUrl);
    fillList(incity, incityUrl);
    var realtyid;
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


//submit add realty form
    $('#addRealty').click(function (e) {
        //get fileds and marker values 

        var realtyNumber = $('#realtyNumber').val(),
                description = $('#description').val(),
                lng = marker.getPosition().lat(),
                lat = marker.getPosition().lng();
          e.preventDefault();
       if(realtyNumber!=='' && realtyNumber!==null){
        $.ajax({
            url: "http://localhost:9090/finalPojest/InsertRealtyServlet",
            type: "POST",
            data: {
                realtyNumber: realtyNumber,
                description: description,
                lng: lng,
                lat: lat
            },
            dataType: "json",
            success: function (result) {
                if (result['key'] === -1) {
                    location.replace('http://localhost:9090/finalPojest/loginForm.jsp');
                } else if (result['key'] === 0) {
                    alert("خطأ حاول مرة اخرى");
                } else if (result['key'] === 1) {
                    realtyid = result['id'];
                    location.href = 'http://localhost:9090/finalPojest/DisplayRealtyinfo.jsp' + "?id=" + realtyid;
                }
            },
            error: function () {
                console.log("Error");
            }
        });
    }else{
        alert('الرجاء التأكد من ادخال البيانات المطلوبة');
    }
    });

});


