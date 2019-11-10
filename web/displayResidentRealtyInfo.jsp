<%-- 
    Document   : addResidentModal
    Created on : Oct 4, 2019, 8:00:41 PM
    Author     : esra
--%>

<%@page import="javaClasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="rtl">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>سكني</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/DisplayRealtyUser.css">
        <link href="https://fonts.googleapis.com/css?family=Changa&display=swap" rel="stylesheet"> 

    </head>
    <body>
        <!--check session validaty--> 
        <%  if((session.getAttribute("user") == null)||(session.getAttribute("user") == "")) {
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
                    <li class="nav-item  ml-2">
                        <a class="nav-link" id="myrealties" href="displayUserRealties.jsp">عقاراتي</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#residentRealtyData">ساكن به</a>
                    </li>
            </div>
            <%  
                User user = (User) session.getAttribute("user");
                pageContext.setAttribute("name", user.getFirstName());
            %><ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <h5 class="align-baseline pt-2 pl-2">  مرحبا بك : ${name}  </h5>    
                </li> 

                <li class="nav-item pr-2">
                    <a class="btn" href="LogoutServlet" onclick="alert('logout successfully')">
                        تسجيل الخروج</a>                        
                </li>                    
            </ul>
        </ul>
    </nav>
    <!--End of upper bar-->
    <!--start of body container-->
    <div class="container">
        <div class="card mt-3 pb-2">
            <div class="card-body">
                <div class="row mr-5 ">                                
                    <div class="col-8 mr-4">
                        <div class=" mr-5 mt-2 text-center" >
                            <table class="table">
                                <tr>
                                    <th>العنوان</th>
                                    <th>وصف موقع العقار</th>
                                    <th> </th>
                                </tr>
                                <tr>
                                    <td id="address"></td> 
                                    <td >
                                        <div id="residentId" hidden></div>
                                        <div id="description"></div>
                                    </td>              
                                    <td>
                                        <button id="updateDescrpition" class="btn" data-toggle="modal" data-target="#residentRealtyData">تعديل الوصف</button>
                                    </td>
                                </tr>
                            </table>
                        </div> 
                    </div> 
                </div>  
            </div>
        </div>
    </div>
    <!--end of container-->
    <!-- UpdateModal -->
    <div class="modal fade" id="residentRealtyData" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content"style="margin-top: 100px">
                <div class="modal-header ">
                    <h5 class="modal-title" id="modalLabel">تعديل وصف موقع العقار:</h5>
                    <button type="button" class="close mr-auto ml-2" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="row mr-2">

                        <label> وصف موقع العقار</label>
                    </div>
                    <div class="row mr-3">
                        <textarea id="textdescription" cols="35" rows="2"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button  id="update" type="button" class="btn ml-2">حفظ</button>
                    <button type="button" class="btn" data-dismiss="modal">الغاء الامر</button>
                </div>
            </div>
        </div>
    </div>
    </body>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/displayResidentRealtyInfo.js"></script>
</html>
