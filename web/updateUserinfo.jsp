<%-- 
    Document   : updateUserinfo
    Created on : Nov 24, 2019, 2:19:41 PM
    Author     : esra
--%>
<%@page import="javaClasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ar" dir="rtl" >
    <head>        
        <title> تعديل بيانات مستخدم</title>
        <meta charset="UTF-8">
        <meta name=”viewport” content=”width=device-width, initial-scale=1.0">      
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap.min.css.map">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/updateUserinfoStyle.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Changa&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <!--check session validaty--> 
        <% // if((session.getAttribute("user") == null)||(session.getAttribute("user") == "")) {
//        response.sendRedirect("http://localhost:9090/finalPojest/loginForm.jsp");
//        }%>        
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
    <!--محتوى الصفحة-->

    <div class="container">
        <div class="card  mt-3 pb-2">
            <div class="card-title text-right pr-3 pt-4 pb-2">
                <h3><i class="fas fa-user-cog"></i> تعديل بيانات مستخدم</h3><hr>                       
            </div>  
            <div class="row mr-5">
                <i class="fas fa-user "></i>
                <h5 class="text-right  pr-1">تغيير اسم المستخدم</h5>
                <button id="showUpdateName" class=" btn text-right mr-2"><i  class="fas fa-angle-down"></i></button> 
                <button id="hideUpdateName" class=" btn text-right mr-2"><i  class="fas fa-angle-up"></i></button>
            </div>
            <form id="updateNameForm" class="updateForm mt-1" novalidate> 
                <div class="form-body">
                    <div class="form-row mt-2">
                        <div class="col-5">
                            <div class="from-group">
                                <input class=" form-control1" id="firstName" type="text"  placeholder="الاسم ">                           
                                <div id="fname_error" class="invalid-feedback"></div>
                            </div>
                        </div>          
                    </div>
                    <div class="form-row mt-3">
                        <div class="col-5">
                            <div class="from-group">                                                                        
                                <input class=" form-control1" id="lastName" type="text"  placeholder=" اللقب">                   
                                <div id="lname_error" class="invalid-feedback"></div>
                            </div>
                        </div>
                    </div></div>
                <div class="buttom">
                    <button  id="updateUserinfo" class=" submit btn mt-2 mb-2 " type="submit" >تعديل</button>
                    <button  id="cancelUpdateName" class="btn mt-2 mb-2 " >الغاء الامر</button>
                </div>
            </form>   
            <hr/>
            <div class="row mr-5 mt-3 mb-3">
                <i class="fas fa-key ml-2"></i>
                <h5 class="text-right ">تغيير كلمة السر</h5> 
                <button id="showchangepassword" class=" btn text-right mr-2"><i  class="fas fa-angle-down"></i></button> 
                <button id="hidechangepassword" class=" btn text-right mr-2"><i  class="fas fa-angle-up"></i></button>
            </div>
            <form id="changePassword" class="mb-3">
                <div class="form-body">
                    <div class="from-row">
                        <div class="col-5"> 
                            <div class="from-group mt-2">
                                <input class=" form-control1" id="password" type="password" name="password"  placeholder=" كلمة السر الحالية" required> 
                                <button id="seep" class="btn"  data-toggle="tooltip" data-placement="top" title="عرض كلمة المرور"> <i  class="far fa-eye"></i></button> 
                                <button class="btn" id="dontseep"data-toggle="tooltip" data-placement="top" title="الغاء عرض كلمة المرور"><i  class="far fa-eye-slash"></i></button>                                       
                                <div id="password_error" class="invalid-feedback"></div>
                            </div>
                        </div>                 
                    </div>
                    <div class="from-row">
                        <div class="col-5">
                            <div class="from-group mt-3">                         
                                <input class="form-control1" id="re_password" type="password" name="passwordComfirm"  placeholder=" كلمة السر الجديدة" required>
                                <button  class="btn"id="seer" class="btn"><i class="far fa-eye"></i>
                                </button> <button class="btn" id="dontseer"><i  class="far fa-eye-slash"></i></button>                                                     
                                <div id="re_password_error" class="invalid-feedback" ></div>
                            </div>                                   
                        </div>
                    </div></div>
                <div class="buttom">
                    <button  id="updatePassword" class=" submit btn mt-2 mb-2 " type="submit" >حفظ</button>
                    <button  id="cancelChangePassword" class="btn mt-2 mb-2 " >الغاء الامر</button>
                </div>            
            </form>       
        </div>

    </div>
    <!--end of section two-->

    <!--نهاية محتوى الصفحة-->
    <!--start of footer-->


    <!--end of footer-->

    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDj6bSQBi8o4w7eUm5a8cJ-EpTmf4n_WTo">
    </script>
    <script src="js/script.js"></script>
    <script src="js/updateUserInfo.js"></script>
</body>
</html>
