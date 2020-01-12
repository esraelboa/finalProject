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
        <title>عنون عقارك</title>
        <meta charset="UTF-8">
        <meta name=”viewport” content=”width=device-width, initial-scale=1.0">      
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap.min.css.map">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
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
                    <li class="nav-item ml-2">
                        <a class="nav-link active" href="index.jsp">الرئيسية</a>
                    </li>
                    <% if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {
                        } else {
                            User user = (User) session.getAttribute("user");
                            if (user.isIsAdmin()) {%> 
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle"  id="addRealtyList" data-toggle="dropdown">
                        اضافة عقار </a>    
                    <div  class="dropdown-menu" aria-labelledby="addRealtyList">
                        <a class="dropdown-item" href="addRealty.jsp"> اضافة عقار </a> 
                        <a class="dropdown-item" href="addCommercialRealty.jsp">اضافة عقار تجاري</a>
                    </div>
                </li> 
                    <%}
                        }%>
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
            <ul class="navbar-nav mr-auto ">          
                <% if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {%>  
                <li class="nav-item mr-5 pr-5"> 
                    <a class="btn login" href="register.jsp"> سجل الان</a> 
                    <a class="btn login" href="loginForm.jsp"> دخول </a> 
                </li>
                <%} else {
                    User user = (User) session.getAttribute("user");
                    pageContext.setAttribute("name", user.getFirstName());
                %>
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
                    <a class="btn" href="LogoutServlet" onclick="">
                        تسجيل الخروج</a>                        
                </li>                    
            </ul>
            <% }%>

        </nav>
        <!--محتوى الصفحة-->

        <div class="container ">
            <!--section one-->
            <% if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {%>  
            <div class="row justify-content-center mt-3">
            </div>
            <div class="row mt-2">
                <div id="slide" class="carousel slide carousel-fade w-100" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="image/logo2.png" class="d-block" alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h3  style="margin-bottom: 170px;margin-right: 220px;padding-right: 100px;">احصل على عنوان الكتروني لاي عقار تملكه بعنون عقارك الان</h3>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img src="image/search.png" class="d-block " alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h3 style="margin-bottom: 150px;margin-right: 220px;padding-right: 100px;">يمكنك بعنون عقارك البحث والوصول الي اي عنوان على الخريطة </h3>
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
            </div>

            <div class=" row justify-content-center mt-3 bg-light p-5">
                <div class="col">
                    <img src="image/mpp.jpg" style="width: 400px;">
                </div>
                <div class="col text-right"style="color: #000; padding:50px">
                    <h2 >
                        ماهو العنوان الالكتروني ؟
                    </h2>
                    <hr>
                    <p>

                        رمز بسيط وسهل يتكون من اربع خانات تعبر عن المنطقة الجغرافية في ليبيا ومكان عقارك على الخريطة وايضا وصف اضافي للوصول الدقيق لمكان
                        عقارك
                    </p>

                </div>
            </div>
            <div class=" row justify-content-center mt-4 bg-light p-5">

                <h2 style="color: #000;">مايمكنك فعله مع عنون عقارك</h2>
            </div>
            <div class=" row justify-content-center bg-light p-3" style="color: #000;">
                <div class="col-6 text-center mt-2">
                    <h5><i class="fas fa-map-marked-alt" style="color: #35A0B1;"></i> تكوين عنوان لعقاراتك</h5>
                    <p>من أهم مايفعله عنون عقارك هو تكوين عنوان الكتروني على الخريطة للعقارات التي تملكها وذلك بتحديد موقعه على الخريطة و ادخال
                        بياناته ووصف لموقعه   </p>
                </div>
                <div class="col-6 text-center mt-2">    
                    <h5><i class="fas fa-search-location" style="color: #35A0B1;" ></i> البحث عن اي عنوان الكتروني</h5>
                    <p>
                        بكل سهولة يمكنك الوصول الى اي عقار سكني بوضع رمز العنوان الالكتروني والبحث عنه
                    </p>
                </div>
                <div class="col-6 text-center mt-3">
                    <h5><i class="fas fa-user-plus" style="color: #35A0B1;" ></i> اضافة عناويين فرعية</h5>
                    <p>يمكنك اضافة عنوان فرعي لعقارك سواء كان نوعه سكني ام تجاري واعطائه لمستأجر عقارك لاستخدامه </p>
                </div>
                <div class="col-6 text-center mt-3">   
                    <h5><i class=" fas fa-shield-alt" style="color: #35A0B1;" ></i>الخصوصية</h5>
                    <p> لا يمكن لأي احد ان يصل الى موقع عقارك الا عبر اعطائه عنوانك الالكتروني </p>
                </div>
            </div>
            <div class=" row justify-content-center mt-4 bg-light p-4">
                <h2 style="color: #000;">لماذا عنون عقارك</h2>
            </div>
            <div class=" row justify-content-center bg-light p-4 text-center" style="color: #000;">
                عنون عقارك يتيح لك كل ماتحتاجه للوصول لك و الى اي مكان على الخريطة
            </div>
            <div class=" row justify-content-center bg-light p-4 text-center" style="color: #000;">
                <div class="col">
                    <img src="image/cmp.png" style="width: 400px;"> 
                </div>
                <div class="col">
                    <ul class="text-right">
                        <li>رمر مميز وبسيط لكل عقار</li>
                        <li> الوصول بكل سهولة الى مكان العقار</li>
                        <li> الوصول الى الاماكن التجارية باسمائها وايضا تصنيفاتها</li>
                        <li>عملي ... يشمل العشوائيات وتطور البناء والمناطق خارج المخططات </li>
                    </ul>
                </div>
            </div>
            <%} else {
                User user = (User) session.getAttribute("user");
                if (!user.isIsAdmin()) { %> 
            <div class="fun-section mt-5">
                <div class="row mt-3">
                    <div class="col-6 text-center">    
                        <div class="card">
                            <img src="image/addRealtyy.png" class="card-img-top w-50" style="margin-right: 150px">
                            <div class="card-body">
                                <p class="mb-2">اضف عقارك وتحصل على عنوان الكتروني له</p>
                                <button class="btn dropdown-toggle"  data-toggle="dropdown"> اضافة عقار </button>
                                <div class="dropdown-menu text-right">
                                    <a class="dropdown-item" href="addRealty.jsp"> اضافة عقار </a> 
                                    <a class="dropdown-item" href="addCommercialRealty.jsp">اضافة عقار تجاري</a>
                                </div>

                            </div>
                        </div>
                    </div> 
                    <div class="col-6 text-center">    
                        <div class="card">
                            <img src="image/ed.png" class="card-img-top w-50" style="margin-right: 150px">
                            <div class="card-body"style="margin-bottom: 8px;">
                                <p class="mb-2" style="padding-top: 1px;">يمكنك عرض وتحديث بيانات عقاراتك</p>
                                <a class="btn" href="displayUserRealties.jsp">ادارة العقارات</a>   
                            </div>
                        </div>            
                    </div>
                </div>

            </div>

            <%} else {%>
            <div class="fun-section mt-5 mb-5 pt-2">
                <div class="row mt-3">

                    <div class="col-6 text-center">    
                        <div class="card">
                            <img src="image/cateditt.png" class="card-img-top w-50" style="margin-right: 150px">
                            <div class="card-body">
                                <a class="btn mb-2" href="displayAllCategories.jsp"> ادارة التصنيفات</a> 
                            </div>
                        </div>
                    </div> 
                    <div class="col-6 text-center">    
                        <div class="card">
                            <img src="image/ed.png" class="card-img-top w-50" style="margin-right: 150px">
                            <div class="card-body"style="margin-bottom: 8px;">
                                <a class="btn mt" href="displayUserRealties.jsp">ادارة العقارات</a>   
                            </div>
                        </div>            
                    </div>
                </div>

            </div>
            <%}
                }%>
        </div>
        <!--end of section two-->

        <!--نهاية محتوى الصفحة-->
        <!--start of footer-->
        <footer id="footer" class="page-footer bg-light mt-5 ">
            <div class="footer-copyright text-center py-3">
                2019-2020
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
