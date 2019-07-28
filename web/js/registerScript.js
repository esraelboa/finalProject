//         window.addEventListener('load', function () {
//            // Get the forms we want to add validation styles to
//            var forms = document.getElementsByClassName('needs-validation');
//            // Loop over them and prevent submission
//            var validation = Array.prototype.filter.call(forms, function (form) {
//                form.addEventListener('submit', function (event) {
//                    if (form.checkValidity() === false) {
//                        event.preventDefault();
//                        event.stopPropagation();
//                    }
//                    form.classList.add('was-validated');
//                }, false);
//            });
//        }, false);
//        var password = document.getElementById("password")
//                , confirm_password = document.getElementById("passwordComfirm");
//
//        function validatePassword() {
//            if (password.value !== confirm_password.value) {
//                confirm_password.setCustomValidity("Passwords Don't Match");
//            } else {
//                confirm_password.setCustomValidity('');
//            }
//        }
//        password.onchange = validatePassword;
//        confirm_password.onkeyup = validatePassword;
$(document).ready(function () {
    var firstName = $("#firstName").val(),
            lastName = $("#lastName").val(),
            email = $("#email").val(),
            phone = $("#phone").val(),
            password = $("#password").val(),
            passwordComfirm = $("#passwordComfirm").val();
//        check validty
//        1:true send request and handle response
//          1.1 if response is ok redirct and open session with user id
//          1.2 if not give user error message 
//        2: false give user error message
    $('form').on('submit', function (e) {
//        console.log(this.checkValidity());
        e.preventDefault();
        var firstName = $("#firstName").val();
        $.ajax({
            url: "http://localhost:9090/testfinalproject/SignUp",
            type: "POST",
            dataType: "json",
            data: {
                firstName: firstName,
                lastName: lastName,
                email: email,
                phone: phone,
                password: password
            },
            success: function (newData) {
                if (newData["stuats"] === "1") {
                    console.log("success");
                    //window.location.replace("http://localhost:9090/testfinalproject/");
                    //window.location.href ="http://localhost:9090/testfinalproject/";               
                } else {
                    console.log("not success");
                }

//              console.log(newData);             
//            var obj = JSON.stringify(newData/*"{\"success\":1}"*/);
//              console.log(obj);
//               var obj1 = JSON.parse(obj);
//               console.log(obj1);  
//               var obj2 = JSON.parse("{\"1\":\"success\"}");
//               console.log(obj2);  
            },
            error: function () {
                console.log("request filed");
            }
        });

    });

//   $("form").submit(function(){
//       var firstName = $("#firstName").val();    
//       $.ajax({
//          url:"http://localhost:9090/testfinalproject/SignUp",
//          type:"POST",
//          dataType:"json",
//          data:{FirstName:firstName},
//          success:function(newData){
//              console.log(newData);
//              $(".result").text(firstName);
//          },
//          error:function(){
//              console.log("request filed");
//          }
//       });
//   }); 

});