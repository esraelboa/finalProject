<%-- 
    Document   : displayRealties
    Created on : Oct 2, 2019, 4:44:31 PM
    Author     : esra
--%>

<%@page import="javaClasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="rtl">
    <head>
        <title>عقاراتك</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/DisplayRealtyUser.css">
        <link href="https://fonts.googleapis.com/css?family=Changa&display=swap" rel="stylesheet"> 
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    </head>
    <body>    
        <!--check session validaty--> 
        <%  if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {
                response.sendRedirect("http://localhost:9090/finalPojest/loginForm.jsp");
            }%> 
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <!--logo-->
            <a class="navbar-brand" href="index.jsp">
                <img src="image/logo2.png"width="55px" height="60px" class="d-inline-block align-top" alt="website logo">                
            </a>
            <!-- page nav bar links-->
            <div class="main-bar justify-content-center">
                <ul class="navbar-nav ">
                    <li class="nav-item pl-3">
                        <a class="nav-link" href="index.jsp">الرئيسية</a>
                    </li>
                    <li class="nav-item ml-2">
                        <a class="nav-link active" id="myrealties" href="#realties">عقاراتي</a>
                    </li>
                    <li class="nav-item ml-3">
                        <a class="nav-link" href="displayResidentRealtyInfo.jsp">ساكن به</a>
                    </li>

                    <li class="nav-item mt-1 mr-2">
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
            <% if ((session.getAttribute("user") != null) || (session.getAttribute("user") != "")) {
                    User user = (User) session.getAttribute("user");
                    pageContext.setAttribute("name", user.getFirstName());
            %><ul class="navbar-nav mr-auto">
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
        </ul>

        <% }%>  
    </nav>
    <!--End of upper bar-->
    <div class="container">
        <div class="card mt-3 pb-2">
            <div class="card-body">
                <div class="card-title text-right pr-3 pt-4 pb-2">
                    <h3><i class="fas fa-cog ml-2 "></i>ادارة العقارات  </h3><hr>                       
                </div> 
                <div class="row mr-5">
                    <h4 class="text-right  pr-1">عقاراتي </h4>
                    <button id="showR" class=" btn text-right mr-2"><i  class="fas fa-angle-down"></i></button> 
                    <button id="hideR" class=" btn text-right mr-2"><i  class="fas fa-angle-up"></i></button>
                </div>
                <div id="realties">
                    <div class="row mr-5 ">                                
                        <div class="col-8 mr-4">
                            <div id="Rmessage" class=" text-center alert alert-warning" role="alert" >
                            </div>
                            <div class=" table table-striped mr-5 mt-2 text-center" id="realtiesData"></div>                   
                        </div>   
                    </div>  
                </div>
                <div class="row mr-5 mt-3">
                    <h4 class="text-right  pr-1">عقاراتي التجارية</h4>
                    <button id="showCR" class=" btn text-right mr-2"><i  class="fas fa-angle-down"></i></button> 
                    <button id="hideCR" class=" btn text-right mr-2"><i  class="fas fa-angle-up"></i></button>
                </div>
                <div id="Crealties">
                    <div class="row mr-5 ">                                
                        <div class="col-8 mr-4">
                            <div class=" table table-striped mr-5 mt-2 text-center" id="CRrealtiesData"></div> 
                            <div id="CRmessage" class=" text-center alert alert-warning" role="alert" >
                            </div>
                        </div>   
                    </div>  
                </div>
            </div>
        </div>
    </div>
    <!-- add Resident Modal -->
    <div class="modal fade" id="addResidentModal" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content" style="margin-top: 100px">
                <div class="modal-header ">
                    <h5 class="modal-title" id="modalLabel">اضافة عنوان فرعي:</h5>
                    <button type="button" class="close mr-auto ml-2" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="addResidentForm " class="add-form">
                        <div class="form-row">
                            <div class="col-4">    
                                <lable class="">البريد الالكتروني</lable>
                            </div>
                            <div class="col-6">
                                <input id="email"class="form-control" type="email" required>
                            </div>
                        </div>

                        <div class="form-row mt-2">
                            <div class="col-4">  
                                <label class="">العنوان</label>
                            </div>
                            <div class="col-6">
                                <input id="resAddress" class="form-control" type="text" required>
                            </div>   
                        </div>
                        <div class="form-row mt-2">
                            <div class="col-4">  
                                <label class="">نوع العقار</label>
                            </div>
                            <div class="col-6 text-right">
                                <input type="radio" name="Rtype" value="0">سكني 
                                <br>
                                <input type="radio" name="Rtype" value="1"> تجاري
                            </div>   
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button  id="addResident" type="submit" class="btn ml-2" >حفظ</button>
                    <button type="button" class="btn" data-dismiss="modal">الغاء الامر</button>
                </div>
            </div>
        </div>
    </div>

    <!-- add Resident Modal -->
    <div class="modal fade" id="addCommercialRealtiesModal" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content" style="margin-top: 100px">
                <div class="modal-header ">
                    <h5 class="modal-title" id="modalLabel">الرجاء ادخال بيانات العقار التجاري</h5>
                    <button type="button" class="close mr-auto ml-2" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="addCommercialRealtiesForm " class="add-form">
                        <div class="form-row">
                            <div class="col-4">    
                                <lable class="">إسم العقار</lable>
                            </div>
                            <div class="col-6">
                                <input id="realtyName"class="form-control" type="text" required="true"data-toggle="tooltip" title="ادخل الاسم التجاري لعقارك">
                            </div>
                        </div>

                        <div class="form-row mt-2">
                            <div class="col-4">  
                                <label class="">رقم الرخصة</label>
                            </div>
                            <div class="col-6">
                                <input id="CRlicensenumber" class="form-control" type="text" required="true" data-toggle="tooltip" title="ادخل رقم الرخصة لنشاطك التجاري">
                            </div>   
                        </div>
                        <div class="form-row mt-2">
                            <div class="col-4">  
                                <label class="">الوصف</label>
                            </div>
                            <div class="col-6">
                             
                            <textarea id="CRdescription" class="form-control" rows="3" data-toggle="tooltip" title="ادخل وصف لعقارك التجاري كامواعيد العمل"></textarea> 

                            </div>   
                        </div>

                        <div class="form-row mt-2">
                            <div class="col-4">
                                <label class="col-form-label">التصنيفات</label>
                            </div>                         
                            <div class="col-6">
                                <select id="catogoryList" class="form-control pt-0 pb-0"></select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button  id="addCommercialRealties" type="submit" class="btn ml-2">حفظ</button>
                    <button type="button" class="btn" data-dismiss="modal">الغاء الامر</button>
                </div>
            </div>
        </div>
    </div>
        <div class="modal fade" id="deleteRealtyModal" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content"style="margin-top: 100px">
                    <div class="modal-header ">
                        <h5 class="modal-title" id="modalLabel">حدف هذا العقار</h5>
                        <button type="button" class="close mr-auto ml-2" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row mr-2 text-right ml-2">
                            <p>
                                هذا العقار قد يحتوي على عناوين فرعية، هل انت متأكد من حدف هذا العقار ؟
                            </p>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button  id="deleteRealty" type="button" class="btn ml-2">نعم</button>
                        <button type="button" class="btn" data-dismiss="modal">الغاء الامر</button>
                    </div>
                </div>
            </div>
        </div>
</body>

<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
<script src="js/displayUserRealties.js"></script>
</html>
