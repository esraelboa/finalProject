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
        map: map
    });
    var infowindow = new google.maps.InfoWindow();
//places marker in spesfic position
    function placeMarker(latLng, desc) {
        marker.setPosition(latLng);
        marker.setDraggable(false);
        marker.setMap(map);
        infowindow.setContent(desc);
        infowindow.open(map, marker);
    }
    ;
    //places marker for commercial Realties in spesfic position   
    function placeCMarker(latLng, desc) {
        var marker = new google.maps.Marker({map: map});
        var infowindow = new google.maps.InfoWindow();
        marker.setPosition(latLng);
        marker.setDraggable(false);
        marker.setMap(map);
        infowindow.setContent(desc);
        infowindow.open(map, marker);
    }
    ;
    //search request 
    $('#search').submit(function (e) {
        //get fileds and marker values 
        var address = $('#address').val();
        var a = address.split(".");
        if (address === '') {
            alert('الحقل فارغ الرجاء ادخال العنوان الالكتروني للعقارات');
        } else if (a.length >= 4) {
            e.preventDefault();
            $.ajax({
                url: "http://localhost:9090/finalPojest/SearchServlet",
                type: "GET",
                data: {address: address},
                dataType: "json",
                success: function (result) {
                    if (result['key'] === 1) {
                        var latlng = {lng: result['Lat'], lat: result['Lng']};
                        map.setCenter(latlng);
                        map.setZoom(17);
                        placeMarker(latlng, '<h5 class="t">وصف مكان العقار :</h5>' + result['Description']);
                    } else if (result['key'] === 0) {
                        alert("لايوجد عقار بهذا العنوان");
                    }
                },
                error: function () {
                    console.log("Error");
                }
            });
        } else if (a.length < 4) {
            e.preventDefault();
            $.ajax({
                url: "http://localhost:9090/finalPojest/getCommercialRealties",
                type: "GET",
                data: {address: address},
                dataType: "json",
                success: function (result) {
                    if (result['key'] === 0) {
                        alert("لايوجد عقار بهذا العنوان");
                    } else {
                        var realties = result["realties"];
                        for (var i = 0; i < realties.length; i++) {
                            var latlng = {lng: realties[i].lat, lat: realties[i].lng};
                            map.setCenter(latlng);
                            var content='<div id="iw_container"><div class="iw-title">'+realties[i].description+'</div></div>';
                         //   placeCMarker(latlng, '<div class="card"> <h5 class="t">'+realties[i].description+'</h5>'+'<div class="card-body"> عرض تفاصيل </div></div>');
                       placeCMarker(latlng,content);
                        }
                    }
                },
                error: function () {
                    console.log("Error");
                }
            });
        }
    });
});
