<%-- 
    Document   : displayCommercialRealtyInfo
    Created on : Nov 29, 2019, 5:39:43 PM
    Author     : esra
--%>
<%@page import="javaClasses.User"%>
<%@page import="javaClasses.Realty"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>عقارك</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css.map">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/DisplayRealtyinfo_Style.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Changa&display=swap" rel="stylesheet">


    </head>
    <body >
        <!--check session validaty--> 
        <% if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {
                response.sendRedirect("http://localhost:9090/finalPojest/loginForm.jsp");
            }%>    
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <!--logo-->
            <a class="navbar-brand" href="index.jsp">
                <img src="image/logo2.png"width="55px" height="60px" class="d-inline-block align-top" alt="website logo">                
            </a>
            <!--main page links-->
            <div class="main-bar justify-content-center">
                <ul class="navbar-nav ">
                    <li class="nav-item">
                        <a class="nav-link " href="index.jsp">الرئيسية</a>
                    </li>
                    <li class="nav-item active ml-2">
                        <a class="nav-link" id="myrealties" href="displayUserRealties.jsp">عقاراتي</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ml-3" href="displayResidentRealtyInfo.jsp">ساكن به</a>
                    </li>
                    <li class="nav-item mt-1">
                        <form class="search-box" id="search"> 
                            <div class="input-group">
                                <div class="input-group-prepend">  
                                    <select id="catogories" class="form-control form-inline">
                                    </select>
                                    <input class="form-control form-inline" id="address" placeholder="العنوان الالكتروني للعقار" type="search" data-toggle="tooltip" data-placement="top" title="الرجاء ادخال العنوان الرقمي للعقار الذي تريد البحث عنه ">
                                    <input id="btn" class="btn form-control form-inline" type="submit" value="بحث">
                                </div>
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
                    <a class="btn" href="LogoutServlet" onclick="alert('logout successfully')">
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
                <h3 class="row text-right mt-3 mr-4">معلومات عقارك :</h3>
            </div>
            <div class="card-body">
                <div class="row mr-3">              
                    <div class="col-4  pr-0" style="padding-top: 0px;">
                        <div class="form-group text-right  " id="displayCRinfo">
                            <div class="form-row mb-3">
                                <div class="col-4">
                                    <label class="col-form-label">  رقم الرخصة:</label>
                                </div>   
                                <div class="col-form-label" id="licenseNumber" >                                      
                                </div>
                            </div>
                            <div class="form-row mb-3">
                                <div class="col-4">
                                    <label class="col-form-label">الاسم التجاري:</label> 
                                </div>  
                                <div class="col-form-label" >
                                    <div id="CRrealtyname"></div>  
                                </div>
                            </div>
                            <div class="form-row">
                                <label class="col-form-label ml-2">النوع:</label> 
                                <span id="cattype" class="badge badge-primary badge-pill " style="padding-top: 11px;padding-bottom: 10px;"></span>
                            </div>
                            <div class="form-row mb-3">
                                <div id="crdes" class="col-2">
                                    <label  class="col-form-label"> الوصف :</label> 
                                </div> 
                                <div class="col-form-label" id="CRdescription">
                                </div>   
                            </div>
                            <div class="col-4">
                                <button id="update" class="btn col-form-label"> تعديل</button>  
                            </div>
                        </div>
                        <div id="UpdateCRForm">
                            <form>
                                <div class="form-row">
                                        <label class="col-form-label">  رقم الرخصة:</label>
                                        <div class="col-form-label" id="CRlicenseNumber" >     
                                        </div>
                                    </div>
                                <div class="form-row">
                                     <label class="col-form-label">الاسم التجاري:</label>
                                     <input id="CRname" class="form-control" type="text" data-toggle="tooltip" data-placement="top" title="الرجاء ادخال اسم التجاري للعقار">
                                </div>
                                <div class="form-row">
                                     <label class="col-form-label">التصنيف</label>
                                     <select id="CRcatogories" class="form-control" required data-toggle="tooltip" data-placement="top" title="الرجاء اختيار نوع النشاط"></select>
                                </div>
                                 <div class="form-row">
                                     <label class="col-form-label">الوصف </label>
                                     <textarea id="CRdes" class="form-control" data-toggle="tooltip" data-placement="top" title="الرجاء ادخال وصف عام للموقع والنشاط التجاري"></textarea>
                                </div>
                                <div class="mt-3">
                                <button id="saveCRupdate" class="btn">حفظ</button>
                                <button id="cancelCRupdate" class="btn">الغاء الامر</button>
                                </div>
                            </form>
                        </div>                    
                    </div>          
                    <div class="col-8">
                        <div id="map"></div>
                    </div>
                </div>   

            </div>                      
        </div>
    </div>    
</div>

<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
<script src="js/displayCommercialRealtyInfo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDj6bSQBi8o4w7eUm5a8cJ-EpTmf4n_WTo"></script>

</html>

