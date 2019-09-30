<%-- 
    Document   : register
    Created on : Sep 30, 2019, 11:47:48 AM
    Author     : esra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <title>اشترك معنا الان</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/registerStyle.css">
          <link href="https://fonts.googleapis.com/css?family=Changa&display=swap" rel="stylesheet">
    </head>
     <% if((session.getAttribute("user") != null)||(session.getAttribute("user") != "")) {
        response.sendRedirect("http://localhost:8080/finalPojest/");
        }%>
    <body class="bg-light">
        <div class="container">           
            <div class="card card-container ">
                <form id="form_singup" class="singup-form mt-1" novalidate> 
                    <div class="form-header">
                        <h4>انشاء حساب جديد </h4><hr>                       
                    </div>    
                    <div class="form-body">
                        <div class="form-row mt-2">
                            <div class="col">
                                <div class="from-group">
                                    <!--<label for="firstName" class="col-6 control-label">الاسم :</label>-->
                                    <input class="col-5 form-control" id="firstName" type="text"  placeholder="الاسم ">                           
                                    <div id="fname_error" class="invalid-feedback"></div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="from-group">                                                                        
                                    <!--<label for="lastName" class="col-6 control-label">اللقب:</label>-->      
                                    <input class="col-5 form-control" id="lastName" type="text"  placeholder=" اللقب">                   
                                    <div id="lname_error" class="invalid-feedback"></div>
                                </div>
                            </div>
                        </div>

                        <div class="from-group mt-2">
                            <!--<label for="email" class="col-3 control-label">البريد الاكتروني:</label>-->                
                            <input class="col-5 form-control" id="email" type="email" name="email"  placeholder="البريد الالكتروني ">                                                               
                            <div id="email_error" class="invalid-feedback"></div>
                        </div>
                        <div class="from-group mt-2">
                            <!--<label for="phoneNumber" class="col-3 control-label" >رقم الهاتف :</label>-->
                            <input class="col-5 form-control" id="phone" type="tel" name="phoneNumber" placeholder=" رقم الهاتف">                                       
                            <div id="phone_error" class="invalid-feedback"></div>  
                        </div>

                        <div class="from-group mt-2">
                            <!--<label for="password" class="col-3 control-label">كلمة المرور:</label>-->
                            <input class=" col-5 form-control" id="password" type="password" name="password"  placeholder=" كلمة السر " required>                                        
                            <div id="password_error" class="invalid-feedback"></div>
                        </div>

                        <div class="from-group mt-2">                         
                            <!--<label for="passwordComfirm" class="col-3 control-label">تأكيد كلمةالمرور:-->
                                <!--<small>الحقل مطلوب </small></label>-->
                            <input class="col-5 form-control" id="re_password" type="password" name="passwordComfirm"  placeholder=" تأكيد كلمة السر" required>                                                        
                            <div id="re_password_error" class="invalid-feedback" ></div>
                        </div>                                   
                    </div>
                    <div class="buttom">
                    <button class="submit btn mt-2 mb-2 " type="submit" >انشاء حساب</button>
                    </div>
                </form>
            </div>
        </div>  
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>      
        <script src="js/registerScript.js"></script>
    </body>
</html>

