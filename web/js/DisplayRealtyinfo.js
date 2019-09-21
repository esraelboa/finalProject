$(document).ready(function () {
   
   
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
    
};
var id= getUrlVars()["id"];
// alert(id);
//  alert("Page is loaded");
  
//   $('#realtyNumber').html("123456");





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
//        e.preventDefault();
        $.ajax({
            url: "http://localhost:8080/finalPojest/DisplayRealtyinfo",
            type: "GET",
            data: {
                realtyid :realtyid            
            },
            dataType: "json",
            success: function (result) {
//                  $('#realtyNumber').html(realtyid);
                  $('#realtyNumber').text(result['realtynumber']);
                  $('#address').text(result['address']);
                  $('#description').text(result['description']);
                  
           var latlng = {lng: result['lat'], lat: result['lng']};
                    map.setCenter(latlng);
                    map.setZoom(17);
                    placeMarker(latlng)
                  alert(result['lat']);
                  alert(result['lng']);
                  
            },
            error: function () {
                console.log("Error");
            }
        });

   
//    realtyNumber: realtyNumber,
//                description: description,
//                lng: lng,
//                lat: lat
    
    
    });

