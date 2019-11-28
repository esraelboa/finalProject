$(document).ready(function () {

    //get Address form url function
    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
            vars[key] = value;
        });
        return vars;
    }
    ;
//get address by calling funtion
    var addressFromUrl = '';
    addressFromUrl = getUrlVars()["address"].replace(/%20/g, " ");
    var catid = getUrlVars()["catid"];
    console.log('here bitch' + catid);
//testing lenght of address to know where's belonges to
    var a = addressFromUrl.split('.');
    if (catid !== '0') {
   searchforCRwithCategoryidRequest(catid);
    } else {
        if (addressFromUrl.length === 0) {
//        alert('الحقل فارغ الرجاء ادخال العنوان الالكتروني للعقار ');
        } else {
            if (a.length >= 4) {
                searchforAddressRequest(addressFromUrl);
            } else if (a.length === 1) {
                searchforCRNamesRequest(addressFromUrl);
            }
        }
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
            $('#catogories').html(optiones);
        });
        $('select option[value="0"]').attr("selected", true);
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
        map: map
    });
    var infowindow = new google.maps.InfoWindow();
//places marker in spesfic position
    function placeMarker(latLng, desc) {
        marker.setPosition(latLng);
        marker.setDraggable(false);
        marker.setMap(map);
    }
    ;
//places marker for commercial Realties in spesfic position   
    function placeCRMarker(latLng, desc) {
        var marker = new google.maps.Marker({map: map});
        var infowindow = new google.maps.InfoWindow();
        marker.setPosition(latLng);
        marker.setDraggable(false);
        marker.setMap(map);
        infowindow.setContent(desc);
        infowindow.open(map, marker);
    }
    ;

//search for main or sub address 
    function searchforAddressRequest(address) {
        $.ajax({
            url: "http://localhost:9090/finalPojest/SearchServlet",
            type: "GET",
            data: {address: address},
            dataType: "json",
            success: function (result) {
                if (result['key'] === 1) {
                    var listConent = '';
                    var content = '';
                    $('#result').empty();
                    content = '<p>وصف الموقع:  ' + result['Description'] + '</p>';
                    listConent += '<li class="list-group-item pl-0">' + content + '</li>';
                    $('#result').html(listConent);
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
    }
    ;

    function  searchforCRNamesRequest(address) {
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
                    fillResultListWithCR(realties);
                    for (var i = 0; i < realties.length; i++) {
                        var latlng = {lng: realties[i].lat, lat: realties[i].lng};
                        map.setCenter(latlng);
                        var content = '<div id="iw_container"><div class="iw-title">' + realties[i].description + '</div></div>';
                        //   placeCMarker(latlng, '<div class="card"> <h5 class="t">'+realties[i].description+'</h5>'+'<div class="card-body"> عرض تفاصيل </div></div>');
                        placeCRMarker(latlng, content);
                    }
                }
            },
            error: function () {
                console.log("Error");
            }
        });
    }

    function  searchforCRwithCategoryidRequest(categoryid) {
        $.ajax({
            url: "http://localhost:9090/finalPojest/getCommercialRealtiesByCategory",
            type: "GET",
            data: {catId: categoryid},
            dataType: "json",
            success: function (result) {
                if (result['key'] === 0) {
                    alert("لايوجد عقارات بهذا التصنيف");
                } else {
                    var realties = result["realties"];
                    fillResultListWithCR(realties);
                    for (var i = 0; i < realties.length; i++) {
                        var latlng = {lng: realties[i].lat, lat: realties[i].lng};
                        map.setCenter(latlng);
                        var content = '<div id="iw_container"><div class="iw-title">' + realties[i].description + '</div></div>';
                        placeCRMarker(latlng, content);
                    }
                }
            },
            error: function () {
                console.log("Error");
            }
        });
    }
    function fillResultListWithCR(result) {
        var listConent = '';
        var content = '';
        $('#result').empty();
        for (var i = 0; i < result.length; i++) {
            var url = 'http://localhost:9090/finalPojest/DisplayCommercialRealtyInfo?id=' + result[i].realtyid;
            $.getJSON(url, function (data) {
                content = '<h6>' + data.realtyName + '</h6> <span class="badge badge-primary badge-pill">' + data.category + '</span><p>وصف الموقع:  ' + data.description + '</p>';
                listConent += '<li class="list-group-item pl-0">' + content + '</li>';
                $('#result').html(listConent);
            });

        }
    }

    function fillResultList(realtyid) {
        var listConent = '';
        var content = '';
        $('#result').empty();
        var url = 'http://localhost:9090/finalPojest/DisplayRealtyinfo?realtyid=' + realtyid;
        $.getJSON(url, function (data) {
            content = '<h6>' + data.address + '</h6><p>وصف الموقع:  ' + data.description + '</p>';
            listConent += '<li class="list-group-item pl-0">' + content + '</li>';
            $('#result').html(listConent);
        });

    }
    //search request 
    $('#search').submit(function (e) {
        e.preventDefault();
        //get fileds 
        var selectedOpt = $('#catogories option:selected').val();
        var address = $('#address').val();
        console.log(selectedOpt);
        var a = address.split(".");
        if (address === '' | selectedOpt === '0') {
//            alert('الحقل فارغ الرجاء ادخال العنوان الالكتروني للعقار او اختر تصنيف');
        } else {
            console.log(address);
            var ad = address.split(".");
            if (ad.length === 0) {
                alert('الحقل فارغ الرجاء ادخال العنوان الالكتروني للعقار');
            } else if (ad.length >= 4) {
                searchforAddressRequest(address);
            } else if (ad.length < 4) {
                searchforCRNamesRequest(address);
                if (selectedOpt !== '0') {
                    searchforCRwithCategoryidRequest(selectedOpt);
                }
            }
        }
    });
});
//});