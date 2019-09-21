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
                error_element = $('#fname_error');
        error_fname = check_name(firstName, error_element);
    });
    $('#lastName').focusout(function () {
        var lastName = $("#lastName").val(),
                error_element = $('#lname_error');
        error_lname = check_name(lastName, error_element);

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

    function check_name(name, error_element)
    {
        var pattren = /^[A-Za-z]{3,25}$/;

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
            if (password.length < 8 || password.lenght > 25) {
                $('#password_error').html(" يجب ان تكون اكتر من8 احرف");
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
                phoneNumber = $("#phone").val(),
                password = $("#password").val(),
                passwordComfirm = $("#password").val();
        var error_fname = false,
                error_lname = false,
                error_email = false,
                error_phone = false,
                error_password = false,
                error_rePassword = false;
        error_fname = check_name(firstName, $('#fname_error'));
        error_lname = check_name(lastName, $('#lname_error'));
        check_email();
        check_phone();
        check_password();
        check_re_password();
        if (error_fname === false && error_lname === false && error_email === false && error_phone === false
                && error_password === false && error_rePassword === false) {
            e.preventDefault();
            $.ajax({
                url: 'http://localhost:8080/finalPojest/registerServlet',
                type: "POST",
                data: {
                    firstName: firstName,
                    lastName: lastName,
                    email: email,
                    phoneNumber: phoneNumber,
                    password: password
                },
                dataType: "json",
                success: function (result) {
                    if (result['key'] === 1) {
                        alert("تمت عملية الاشتراك بنجاح");
                        location.replace('http://localhost:8080/finalPojest/mainInterface.html');
                    } else if (result['key'] === 0) {
                        alert("حاول مرة اخرى مع بيانات اخرى");
                    }
                },
                error: function () {
                    alert("check your internet conction");
                }
            });

        } else {
            console.log("filed");
            return false;

        }
    });
//submiting login form 
    $('#loginform').submit(function (e) {
        var email = $("#email").val()
                , password = $("#password").val();

        var error_email = false,
                error_password = false;

        check_email();
        check_password();
        if (error_email === false && error_password === false) {
            e.preventDefault();
            $.ajax({
                url: 'http://localhost:8080/finalPojest/login_servlet',
                type: "POST",
                data: {email: email,
                    password: password},
                dataType: "json",
                success: function (result) {
                   if (result['key'] === 1) {
                        alert(result['message']);
                        location.replace('http://localhost:8080/finalPojest/index.jsp');
                    } else if (result['key'] === 0) {
                        alert(result['message']);
                    }
                },
                error: function () {
                    console.log("Error");
                    alert("error");
                }
            });
        } else {
            console.log("filed");
            return false;
        }
    });
});
