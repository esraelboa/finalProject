$(document).ready(function () {
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
    var infowindow = new google.maps.InfoWindow();
//places marker in spesfic position
    function placeMarker(latLng,desc) {
        marker.setPosition(latLng);
        marker.setDraggable(false);
        marker.setMap(map);
        infowindow.setContent(desc);
                    infowindow.open(map, marker); 
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
    //search request 
    $('#search').submit(function (e) {
        //get fileds and marker values 
        var Address = $('#address').val();
        if(Address===''){
             alert('الحقل فارغ الرجاء ادخال العنوان الالكتروني للعقارات');
        }else{
        e.preventDefault();
        $.ajax({
            url: "http://localhost:8080/finalPojest/SearchServlet",
            type: "POST",
            data: {address: Address},
            dataType: "json",
            success: function (result) {
                if (result['key'] === 1) {
                    var latlng = {lng: result['Lat'], lat: result['Lng']};
                    map.setCenter(latlng);
                    map.setZoom(17);
                    placeMarker(latlng,"وصف مكان العقار :<br>" + result['Description']);
                   
                } else if (result['key'] === 0) {
                    alert("لايوجد عقار بهذا العنوان");
                }
            },
            error: function () {
                console.log("Error");
            }
        });
        }
    });
});