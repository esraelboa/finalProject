<%-- 
    Document   : index
    Created on : Sep 18, 2019, 1:16:32 PM
    Author     : esra
--%>
<%@page import="javaClasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ar" dir="rtl" >
    <head>        
        <title> Address Generator</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
                    <li class="nav-item pl-3">
                        <a class="nav-link active" href="index.jsp">الرئيسية</a>
                    </li>
                    <% if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {%>  
                    <li class="nav-item pl-3">
                        <a class="nav-link " href="#search">البحث عن عقار</a>
                    </li> 
                     <li class="nav-item pl-3">
                        <a class="nav-link " href="#footer">حول</a>
                    </li><%} %>
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
        <% if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {%>  
        <div id="slide" class="carousel slide carousel-fade" data-ride="carousel">
            <div class="carousel-inner w-100 h-20">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="image/logo2.png" class="d-block" alt="...">
                        <div class="carousel-caption d-none d-md-block">
                            <h3>احصل على عنوان الكتروني لعقارك </h3>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="image/search.png" class="d-block " alt="...">
                        <div class="carousel-caption d-none d-md-block">
                            <h3>يمكنك البحث والوصول الي اي عنوان على الخريطة </h3>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#slide" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#slide" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <%} else { %> 
        <div class="fun-section mt-2">
            <div class="card">
                <div class="row">
                    <div class="col-6 text-center">
                        <img src="image/addRealtyy.png" class="card-img-top w-25">
                        <p class="mb-2">اضف عقارك وتحصل على عنوان الكتروني له</p>
                        <a class="btn mb-2" href="addRealty.jsp"> اضافة عقار</a>   
                    </div> 
                    <div class="col-6 text-center">
                        <img src="image/ed.png" class="card-img-top w-25">
                        <p class="mb-2" style="padding-top: 1px;">يمكنك عرض وتحديث بيانات عقاراتك</p>

                        <a class="btn" href="displayUserRealties.jsp">ادارة العقارات</a>   
                    </div> 
                </div>
            </div>
            <%}%>
            <!--end of section one-->
            <!--section two-->
            <div  id="search" class="card mt-3 pb-2">                                
                <div class="card-body text-center">  
                    <h3 class="">بحث عن عقار</h3>  
                    <form class="search-box" id="search">  
                        <div class="input-group">
                            <div class="input-group-append">      
                                <input class="form-control mb-2" id="address" placeholder="عنوان العقار" type="search" data-toggle="tooltip" data-placement="top" title="الرجاء ادخال العنوان الرقمي للعقار الذي تريد البحث عنه ">
                                <input class="btn form-control" type="submit" value="بحث">
                            </div>
                        </div>
                    </form>   
                    <div id="map"></div>
                </div>
            </div> 
            <!--end of section two-->
        </div>
    </div>

    <!--نهاية محتوى الصفحة-->
    <!--start of footer-->
    <footer id="footer" class="page-footer bg-light mt-2">
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
    <script src="js/script.js"></script>
</body>
</html>
