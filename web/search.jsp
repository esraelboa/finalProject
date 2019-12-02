<%-- 
    Document   : search
    Created on : Nov 16, 2019, 8:09:47 PM
    Author     : esra
--%>
<%@page import="javaClasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ar" dir="rtl" >
    <head>        
        <title> بحث عن عقار</title>
        <meta charset="UTF-8">
        <meta name=”viewport” content=”width=device-width, initial-scale=1.0">   
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css?family=Changa&display=swap" rel="stylesheet"> 
    </head>
    <body>        
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
                                <input class="btn mr-2 " type="submit" value="بحث">
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
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <h5 class="align-baseline pt-2 pl-2">  مرحبا بك : ${name}  </h5>    
                </li> 
                <li class="nav-item pr-2">
                    <a class="btn" href="LogoutServlet" onclick="alert('logout successfully')">
                        تسجيل الخروج</a>                        
                </li>                    
            </ul>
        </ul>

        <% }%>

    </nav>
    <!--محتوى الصفحة-->

    <div class="container">
        <!--section one-->
        <div  id="search" class="card mt-3 pb-2">   
            <div class="row">
                <!--section two-->
                <div class="col text-right">
                    <h3 class="mt-3 mr-4">نتائج البحث..</h3>
                   <ul id="result" class="list-group ">                       
                   </ul>
                </div>   
                <div class="col" style="padding-top: 40px;">
                    <div class="card-body text-center">  
                        <div id="map"></div>
                    </div>
                </div>
            </div>

        </div></div>
    <!--end of section two-->

    <!--نهاية محتوى الصفحة-->
    <!--start of footer-->
    <footer id="footer" class="page-footer bg-light mt-2 fixed-bottom">
        <div class="footer-copyright text-center py-3">
            2019-2020
            <!--<p> DEV : EBE</p>-->
        </div>
    </footer>

    <!--end of footer-->

    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDj6bSQBi8o4w7eUm5a8cJ-EpTmf4n_WTo">
    </script>
   
    <script src="js/searchScript.js"></script>
     <script src="js/script.js"></script>
</body>
</html>

