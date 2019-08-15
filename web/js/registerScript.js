$(document).ready(function () {

//    error html element
    $('#fname_error').hide();
    $('#lname_error').hide();
    $('#email_error').hide();
    $('#phone_error').hide();
    $('#password_error').hide();
    $('#re_password_error').hide();

//boolen vars to check validty
    var error_fname = false,
            error_lname = false,
            error_email = false,
            error_phone = false,
            error_password = false,
            error_rePassword = false;
//  validtion event
    $('#firstName').focusout(function () {
        var firstName = $("#firstName").val(),
                fnameLength = firstName.length,
                error_element = $('#fname_error');
        error_fname = check_name(firstName, fnameLength, error_element);
    });
    $('#lastName').focusout(function () {
        var lastName = $("#lastName").val(),
                lastLength = lastName.length,
                error_element = $('#lname_error');
        error_lname = check_name(lastName, lastLength, error_element);

    });
    $('#email').focusout(function () {
        check_email();
    });
    $('#phone').focusout(function () {
        check_phone();
    });
    $('#password').focusout(function () {
        check_password();
    });
    $('#re_password').focusout(function () {
        check_re_password();
    });

//validtion check functions

    function check_name(name, nameLength, error_element)
    {
        var pattren = /^[A-Za-z]{3,20}$/;

        if (name === '' || name === null) {
            error_element.html("الحقل مطلوب");
            error_element.show();
            return true;
        } else {
            if (!(pattren.test(name))) {
                error_element.html("الاسم يجب ان يتكون من تلاته حروف او اكتر");
                error_element.show();
                return true;
            } else {
                error_element.hide();
                return false;
            }
        }
    }

    function check_email() {
        var pattren = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                email = $("#email").val();

        if (email === '' || email === null) {
            $("#email_error").html("الحقل مطلوب");
            $("#email_error").show();
            error_email = true;
        } else {
            if (!(pattren.test(email))) {
                $('#email_error').html("بريد الكتروني غير صحيح");
                $('#email_error').show();
                error_email = true;
            } else {
                $('#email_error').hide();
                error_email = false;
            }
        }
    }

    function check_phone() {
        var pattren = /^[0-9]{10}$/,
                phone = $("#phone").val();
        if (phone === '' || phone === null) {
            $('#phone_error').html("الحقل مطلوب");
            $('#phone_error').show();
            error_phone = true;
        } else {
            if (!(pattren.test(phone))) {
                $('#phone_error').html("رقم هاتف غير صحيح");
                $('#phone_error').show();
                error_phone = true;
            } else {
                $('#phone_error').hide();
                error_phone = false;
            }
        }

    }

    function check_password() {
        var password = $("#password").val();
        if (password === '' || password === null) {
            $('#password_error').html("الحقل مطلوب");
            $('#password_error').show();
            error_password = true;
        } else {
            if (password.length < 5) {
                $('#password_error').html("يجب ان تكون اكتر من5 احرف");
                $('#password_error').show();
                error_password = true;
            } else {
                $('#password_error').hide();
                error_password = false;
            }

        }
    }

    function check_re_password() {
        var password = $("#password").val(),
                re_password = $("#re_password").val();
        if (re_password === '' || re_password === null) {
            $('#re_password_error').html("الحقل مطلوب");
            $('#re_password_error').show();
            error_rePassword = true;
        } else {
            if (password !== re_password) {
                $('#re_password_error').html("كلمة مرور غير متطابقة");
                $('#re_password_error').show();
                error_rePassword = true;
            } else {
                $('#re_password_error').hide();
                error_rePassword = false;
            }
        }
    }

//    submiting the  registerion form
    $('#form_singup').submit(function (e) {
        var firstName = $("#firstName").val(),
                lastName = $("#lastName").val(),
                email = $("#email").val(),
                phone = $("#phone").val(),
                password = $("#Password").val(),
                passwordComfirm = $("#passwordComfirm").val();
        var error_fname = false,
                error_lname = false,
                error_email = false,
                error_phone = false,
                error_password = false,
                error_rePassword = false;

        error_fname = check_name(firstName, firstName.length, $('#fname_error'));
        error_lname = check_name(lastName, lastName.length, $('#lname_error'));
        check_email();
        check_phone();
        check_password();
        check_re_password();
        if (error_fname === false && error_lname === false && error_email === false && error_phone === false
                && error_password === false && error_rePassword === false) {
            e.preventDefault();
            $.ajax({
                url: "http://localhost:9090/testfinalproject/SignUp",
                type: "POST",
                dataType: "json",
                data: {
                    firstName: firstName,
                    lastName: lastName,
                    email: email,
                    phoneNumbeer: phone,
                    password: password
                },
                success: function (newData) {
                    if (newData["stuats"] === "1") {
                        console.log("success");

                    } else {
                        console.log("not success");
                    }

                },
                error: function () {
                    console.log("request filed");
                }
            });
        } else {
            console.log("filed");
            return false;
        }
    });
//submiting login form 
    $('#loginform').submit(function (e) {
        var Email = $("#email").val()
                , Password = $("#password").val();

        var error_email = false,
                error_password = false;

        check_email();
        check_password();

        if (error_email === false && error_password === false) {
            e.preventDefault();
            $.ajax({
                url: "http://localhost:9090/testfinalproject/servletlogin",
                type: "POST",
                data: {Email: Email, Password: Password},
                dataType: "json",
                success: function () {
                    console.log("success request");
                    location.replace("http://localhost:9090/testfinalproject/mainInterface.html");

                },
                error: function () {
                    console.log("Error");
                }

            });
        } else {
            console.log("filed");
            return false;
        }
    });
});
