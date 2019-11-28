<%-- 
    Document   : loginForm
    Created on : Sep 18, 2019, 12:51:56 PM
    Author     : esra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <title>تسجيل الدخول</title>
        <meta charset="UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css.map">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="fontawesome.min.css">
        <link rel="stylesheet" href="css/loginStyle.css"> 
        <link href="https://fonts.googleapis.com/css?family=Changa&display=swap" rel="stylesheet">        
    </head> 
    <body>
  <!--check session validaty--> 
        <% if((session.getAttribute("user") == null)||(session.getAttribute("user") == "")) {
            
        } else {response.sendRedirect("http://localhost:9090/finalPojest/loginForm.jsp");
        }%>
        <form name="login" id="loginform" action="loginForm.html" class="login-form needs-validation" novalidate>
            <h1> تسجيل الدخول </h1>
            <div class="textb">
                <input type="email" id="email" name="uname" placeholder="البريد الالكتروني" required >
                <div id="email_error" class="invalid-feedback"></div>
            </div> 
            <div class="textb" >
                <input type="password" id="password" name="pass" placeholder="كلمة السر " required>
                <div id="password_error" class="invalid-feedback"></div>

            </div> 
            <div>
                    <input type="submit" name="submit" class="logbtn" id="btn" value="تسجيل الدخول">

            </div>

            <div class="bottom-text">
                ليس لديك حساب ؟<a href="register.jsp">اشتراك الأن</a>
            </div>
        </form>  
      
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/registerScript.js"></script>

    </body>
</html>
