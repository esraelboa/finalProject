<%-- 
    Document   : addRealty
    Created on : Sep 16, 2019, 10:41:14 AM
    Author     : esra
--%>

<%@page import="javaClasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="ar" dir="rtl" >
    <head>
        <title>اضافة عقار</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css.map">
        <link rel="stylesheet" href="css/bootstrap.min.css">
      <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/addRealty_Style.css">
        <link href="https://fonts.googleapis.com/css?family=Changa&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <!--check session validaty--> 
        <% if((session.getAttribute("user") == null)||(session.getAttribute("user") == "")) {
        response.sendRedirect("http://localhost:9090/finalPojest/loginForm.jsp");
        }%>
        <!--start of upper bar-->
             <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <!--logo-->
            <a class="navbar-brand" href="index.jsp">
                <img src="image/logo2.png"width="55px" height="60px" class="d-inline-block align-top" alt="website logo">                
            </a>
            <!--main page links-->
            <div class="main-bar justify-content-center">
                <ul class="navbar-nav ">
                    <li class="nav-item">
                        <a class="nav-link active" href="index.jsp">الرئيسية</a>
                    </li>
                     <li class="nav-item mt-1">
                        <form class="search-box" id="search"> 
                            <div class="form-row form-inline" >
                                <select id="catogories" class="form-control">
                                </select>
                                <input class="form-control mr-2" id="address" placeholder="العنوان الالكتروني للعقار" type="search" data-toggle="tooltip" data-placement="top" title="الرجاء ادخال العنوان الرقمي للعقار الذي تريد البحث عنه او الاسم التجاري ">
                                <input id="btn" class="btn mr-2 " type="submit" value="بحث">
                            </div>
                        </form> 
                    </li>
                </ul>
            </div>  
            <% if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {%>  
            <div class="nav-item mr-5 pr-5 mr-auto">  
                <a class="btn login" href="loginForm.jsp"> تسجيل الدخول </a> 
            </div>
            <%} else {
                User user = (User) session.getAttribute("user");
                pageContext.setAttribute("name", user.getFirstName());
            %>
            <ul class="navbar-nav mr-auto ">
                <li class="nav-item dropdown">
                    <a class="nav-link align-baseline pt-2 pl-2 dropdown-toggle"  id="n" data-toggle="dropdown">
                        مرحبا بك : ${name}  </a>    
                    <div  class="dropdown-menu" aria-labelledby="n">
                        <a class="dropdown-item" href="updateUserinfo.jsp">
                            <i class="fas fa-user-cog"></i>
                            تعديل بيانات حساب</a>
                    </div>
                </li> 
                <li class="nav-item pr-2">
                    <a class="btn" href="LogoutServlet" >
                        تسجيل الخروج</a>                        
                </li>                    
            </ul>
        </ul>

        <% }%>

    </nav>
        <!--End of upper bar-->
        <div class="container">
            <div class="card mt-3 pb-2">
                <div class=" card-title">
                    <h3 class="row text-right mt-2 mr-4">معلومات الموقع</h3>
                    <p class="text-right">الرجاء ادخال البيانات المطلوبة وتحديد موقع عقارك على الخريطة</p>
                </div>
                <div class="card-body" style="padding-top: 0px;">
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
                                        <div id="realtyNumberError" class="invalid-feedback"></div>
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
                                <button id="addRealty" class="btn float-left">اضافة</button>  
                            </form>
                        </div>          
                        <div class="col-8" data-toggle="tooltip" data-placement="top" title="حد موقع عقارك على الخريطة">
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
          <script src="js/script.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDj6bSQBi8o4w7eUm5a8cJ-EpTmf4n_WTo"></script>
    </body>
</html>
