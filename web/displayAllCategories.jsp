<%-- 
    Document   : displayAllCategories
    Created on : Nov 27, 2019, 12:03:36 PM
    Author     : esra
--%>
<%@page import="javaClasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ar" dir="rtl" >
    <head>        
        <title> ادارة التصنيفات</title>
        <meta charset="UTF-8">
        <meta name=”viewport” content=”width=device-width, initial-scale=1.0">      
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap.min.css.map">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/categoriesStyle.css">
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
                    <li class="nav-item">
                        <a class="nav-link active" href="index.jsp">الرئيسية</a>
                    </li>
                    <li class="nav-item mt-1">
                        <form class="search-box" id="search"> 
                            <div class="input-group">
                                <div class="input-group-prepend">  
                                    <select id="catogories" class="form-control form-inline">
                                    </select>
                                    <input class="form-control form-inline" id="address" placeholder="العنوان الالكتروني للعقار" type="search" data-toggle="tooltip" data-placement="top" title="الرجاء ادخال العنوان الرقمي للعقار الذي تريد البحث عنه او الاسم التجاري ">
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
                    <a class="btn" href="LogoutServlet" onclick="">
                        تسجيل الخروج</a>                        
                </li>                    
            </ul>

            <% }%>
        </nav>
        <% User user = (User) session.getAttribute("user");
            if (!user.isIsAdmin()) {
                response.sendRedirect("http://localhost:9090/finalPojest/");
            }%>
        <!--محتوى الصفحة-->

        <div class="container">
            <div class="card mt-3 pb-2">
                <div class="card-body">
                    <div class="card-title text-right pr-3 pt-4 pb-2">
                        <h3><i class="fas fa-cog ml-2 "></i>ادارة التصنيفات  </h3><hr>                       
                    </div>  
                    <div class="row mr-5">
                        <h4 class="text-right  pr-1"> <i class="fas fa-plus-circle ml-2"></i>اضافة تصنيف جديد </h4>
                        <button id="showAddCategories" class=" btn text-right mr-2"><i  class="fas fa-angle-down"></i></button> 
                        <button id="hideAddCategories" class=" btn text-right mr-2"><i  class="fas fa-angle-up"></i></button>
                    </div>
                    <form id="insertCategory" class="text-right mr-5 mt-2">
                        <div class="form-body mr-5">
                            <div class="form-row">
                                <input id="name" class="form-control1" type="text" required placeholder="اسم التصنيف">
                            </div>
                            <div class="form-row mt-2 mb-2 text-center">
                                <div class="col-4">
                                    <input id="addCategory" class="btn" type="submit" value="حفظ">
                                    <input id="cancelAddCategory" class="btn" type="button" value="الغاء الامر">
                                </div>
                            </div>

                        </div>
                    </form>
                    <div class="row mr-5 mt-5 mb-3 mr-5">
                        <h4 class="text-right  pr-1"> <i class="fas fa-clipboard-list ml-2"></i>عرض جميع التصنيفات</h4>
                        <button id="showAllCategories" class=" btn text-right mr-2"><i  class="fas fa-angle-down"></i></button> 
                        <button id="hideAllCategories" class=" btn text-right mr-2"><i  class="fas fa-angle-up"></i></button>
                    </div>
                    <div id="allCategoriesSection">
                        <div class="row mr-5 text-center mb-3 "> 
                            <input id="input" class="input-group-text" type="search"  placeholder="بحث سريع...">
                        </div>  
                        <div class="row mr-5 ">                                
                            <div class="col-8 mr-4">
                                <div id="allCategories" class="table table-striped mr-5 mt-2 text-center" ></div>                   
                            </div>   
                        </div>  
                    </div>
                </div>    
            </div>
        </div>
        <div class="modal fade" id="UpdateCategoryName" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content"style="margin-top: 100px">
                    <div class="modal-header ">
                        <h5 class="modal-title" id="modalLabel">تعديل اسم التصنيف :</h5>
                        <button type="button" class="close mr-auto ml-2" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row mr-2">
                            <label> اسم التصنيف</label>
                        </div>
                        <div class="row mr-3 text-center">
                            <input id="inputname" class="mr-3" requrid>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button  id="updatecatName" type="button" class="btn ml-2">حفظ</button>
                        <button type="button" class="btn" data-dismiss="modal">الغاء الامر</button>
                    </div>
                </div>
            </div>
        </div>


        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDj6bSQBi8o4w7eUm5a8cJ-EpTmf4n_WTo">
        </script>
        <script src="js/script.js"></script>
        <script src="js/categoriesScript.js"></script>
    </body>
</html>
