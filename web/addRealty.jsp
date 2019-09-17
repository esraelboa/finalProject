<%-- 
    Document   : addRealty
    Created on : Sep 16, 2019, 10:41:14 AM
    Author     : esra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="ar" dir="rtl" >
    <head>
        <title>اضافة عقار</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css.map">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/stylecssbs.css">
        <link rel="stylesheet" href="css/addRealty_Style.css">
        <link href="https://fonts.googleapis.com/css?family=Changa&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <!--check session validaty--> 
        <% if((session.getAttribute("user") == null)||(session.getAttribute("user") == "")) {
//        response.sendRedirect("http://localhost:8080/finalPojest/loginForm.html");
        }%>
        <!--start of upper bar-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">
                <img src="img/logo.png"width="40px" height="40px" class="d-inline-block align-top" alt="website logo">                
            </a>
        </nav>
        <!--End of upper bar-->
        <div class="container">
            <div class="card mt-3 pb-2">
                <div class=" card-title">
                    <h3 class="row text-right mt-3 mr-4">معلومات الموقع</h3>
                    <p class="text-right">الرجاء ادخال البيانات المطلوبة وتحديد موقع عقارك على الخريطة</p>
                </div>
                <div class="card-body">
                    <div class="row mr-5 ">              
                        <div class="col-4 mt-2 pr-0">
                            <form class="add-realty form-group text-right mr-3 " >
                                <div class="form-row mb-3">
                                    <div class="col-4">
                                        <label class="col-form-label"> المدينة:</label>
                                    </div>                         
                                    <div class="col-8">
                                        <select id="cities" class="form-control pt-0 pb-0"></select>
                                    </div>
                                </div>
                                <div class="form-row mb-3">
                                    <div class="col-4">
                                        <label class="col-form-label">المنطقة :</label> 
                                    </div>  
                                    <div class="col-8">
                                        <select id="incity" class="form-control pt-0 pb-0"></select>
                                    </div>
                                </div>
                                <div class="form-row mb-3">
                                    <div class="col-4">
                                        <label class="col-form-label">رقم المبنى:</label>   
                                    </div>   
                                    <div class="col-8">
                                        <input id="realtyNumber" class="form-control pt-0 pb-0 rnumber" pattern= "[0-9]+" required data-toggle="tooltip" title="ادخل رقم العقار المسجل في السجل العقاري"> 
                                    </div> 
                                </div>
                                <div class="form-row">
                                    <label class="pr-1">وصف لموقع العقار:</label>  
                                </div>
                                <div class="from-row mb-3">
                                    <textarea id="description" class="form-control" rows="3" data-toggle="tooltip" title="ادخل وصف للمكان الموجود به العقار"></textarea> 
                                </div>
                                <input class="btn float-left" type="submit" value="اضافة">  
                            </form>
                        </div>          
                        <div class="col-8"data-toggle="tooltip" data-placement="top" title="حد موقع عقارك على الخريطة">
                            <div id="map"></div>
                        </div>
                    </div>                       
                </div>
            </div>    
        </div>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/addRealtyScript.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDoo2pQ0D9cwpMAgIoHkilDLP2O5psQ4Wg"></script>
    </body>
</html>
