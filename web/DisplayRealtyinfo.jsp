<%-- 
    Document   : displayRealtyinfo
    Created on : 18/09/2019, 8:49:55 PM
    Author     : sooma
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
        <link rel="stylesheet" href="css/stylecssbs.css">
        <link rel="stylesheet" href="css/DisplayRealtyinfo_Style.css">
        <link href="https://fonts.googleapis.com/css?family=Changa&display=swap" rel="stylesheet">
      

    </head>
    <body >
        <!--check session validaty--> 
        <% if((session.getAttribute("user") == null)||(session.getAttribute("user") == "")) {
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
                    <li class="nav-item pl-3">
                        <a class="nav-link active" href="index.jsp">الرئيسية</a>
                    </li>
         <% if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {%>  
                    <li class="nav-item pl-3">
                        <a class="nav-link " href="#footer">حول</a>
                    </li>
               
                    <li class="nav-item pl-3">
                        <a class="nav-link " href="#search">البحث عن عقار</a>
                    </li> <%} %>
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
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <h5 class="align-baseline pt-2 pl-2">  مرحبا بك : ${name}  </h5>    
                </li> 
         
                <li class="nav-item pr-2">
                    <a class="btn" href="LogoutServlet" onclick="alert('logout successfully')">
                        تسجيل الخروج</a>                        
                </li>                    
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
                    <div class="row mr-5 ">              
                        <div class="col-4 mt-2 pr-0">
                            <form class="Display-realty form-group text-right mr-3 " id="display">
                                <div class="form-row mb-3">
                                    <div class="col-4">
                                        <label class="col-form-label">  رقم العقار</label>
                                    </div>   
                                    <div class="col-form-label" id="realtyNumber" >                                      
                                    </div>
                                </div>
                                <div class="form-row mb-3">
                                    <div class="col-4">
                                        <label class="col-form-label">العنوان الالكتروني:</label> 
                                    </div>  
                                    <div class="col-form-label" id="address">
                                    </div>
                                </div>
<!--                                <div class="form-row mb-3">
                                    <div class="col-4">
                                        <label class="col-form-label"> الوصف العقار:</label>   
                                    </div> 
                                      <div class="col-form-label" id="description">
                                    </div> 
                                </div>-->
                               <!--<input class="btn float-left" type="submit" value="اضافة">-->
                         <div class="form-row mb-3">
                                    <div class="col-4">
                                        <label id="des" class="col-form-label"> الوصف :</label> 
                                          
                                    </div> 
                                    <div class="col-form-label" id="description">
                                    <!--<textarea id="description" class="form-control" rows="3" data-toggle="tooltip" title="ادخل التعديل "></textarea>-->
                                    </div>    
                                     <div class="form-row mb-3">
                                    <div class="col-4">
                                        <label id="lid" class="col-form-label"> تعديل الوصف:</label> 
                                          
                                    </div> 
                                    <!--<div class="col-form-label" id="description">-->
                                    <textarea  class="form-control" id="updatedescription"  title="ادخل التعديل "></textarea>
                                    </div>    
                                </div>
                                <div class="col-4">
                                      <div>
                                        <input id="update" class="col-form-label" value="تعديل">  
                                    </div>
                                    <div>
                                        <input id="subupdate" class="col-form-label"  name="submit" type="submit" value="حفظ">  
                                    </div>
                                    </div>
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
        <script src="js/DisplayRealtyinfo.js"></script>
         <script src="js/updateRealtyinfo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDj6bSQBi8o4w7eUm5a8cJ-EpTmf4n_WTo"></script>
   
</html>
