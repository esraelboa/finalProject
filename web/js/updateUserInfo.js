$(document).ready(function () {

    $.ajax({
        url: "http://localhost:9090/finalPojest/getUserInfoServlet",
        type: "GET",
        data: {
        },
        dataType: "json",
        success: function (result) {
            $('#firstName').val(result['first name']);
            $('#lastName').val(result['last name']);
        },
        error: function () {
            console.log("Error");
        }
    });

    //    error html element
    $('#hidechangepassword').hide();
    $('#updateNameForm').hide();
    $('#hideUpdateName').hide();
    $('#changePassword').hide();
    $('#dontseep').hide();
    $('#dontseer').hide();
    $('#fname_error').hide();
    $('#lname_error').hide();
    $('#password_error').hide();
    $('#re_password_error').hide();

//boolen vars to check validty
    var error_fname = false,
            error_lname = false,
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

    function check_password() {
        var password = $("#password").val();
        if (password === '' || password === null) {
            $('#password_error').html("الحقل مطلوب");
            $('#password_error').show();
            error_password = true;
        }else{
               $('#password_error').hide();
        }
    }

    function check_re_password() {
        var password = $("#password").val(),
                re_password = $("#re_password").val();
        if (re_password === '' || re_password === null) {
            $('#re_password_error').html("الحقل مطلوب");
            $('#re_password_error').show();
            error_rePassword = true;
        }else{
             $('#re_password_error').hide();
        }
    }

    $('#showchangepassword').click(function (e) {
        e.preventDefault();
        $('#showchangepassword').hide();
        $('#hidechangepassword').show();
        $('#changePassword').show();

    });
    $('#hidechangepassword').click(function (e) {
        e.preventDefault();
        $('#showchangepassword').show();
        $('#hidechangepassword').hide();
        $('#changePassword').hide();

    });
    $('#showUpdateName').click(function (e) {
        e.preventDefault();
        $('#showUpdateName').hide();
        $('#hideUpdateName').show();
        $('#updateNameForm').show();

    });
    $('#hideUpdateName').click(function (e) {
        e.preventDefault();
        $('#showUpdateName').show();
        $('#hideUpdateName').hide();
        $('#updateNameForm').hide();

    });
    $('#seep').click(function (e) {
        e.preventDefault();
        $('#seep').hide();
        $("#password").attr("type", "text");
        $('#dontseep').show();
    });
    $('#dontseep').click(function (e) {
        e.preventDefault();
        $('#dontseep').hide();
        $("#password").attr("type", "password");
        $('#seep').show();
    });
    $('#seer').click(function (e) {
        e.preventDefault();
        $('#seer').hide();
        $("#re_password").attr("type", "text");
        $('#dontseer').show();
    });
    $('#dontseer').click(function (e) {
        e.preventDefault();
        $('#dontseer').hide();
        $("#re_password").attr("type", "password");
        $('#seer').show();
    });
    
    $('#updateNameForm').submit(function (e) {
        e.preventDefault();
        var firstname = $('#firstName').val(),
                lastname = $('#lastName').val();
        $.ajax({
            url: "http://localhost:9090/finalPojest/UpdateUserrServlet",
            type: "POST",
            data: {
                firstname: firstname,
                lastname: lastname
            },
            dataType: "json",
            success: function (result) {
                if (result['key'] === 0) {
                    alert("حاول مجددأ");
                } else {
                    alert("تم التعديل بنجاح");
                }
            },
            error: function () {
                console.log("Error");
            }
        });
    });
    $('#changePassword').submit(function (e) {
        e.preventDefault();
        var currentpassword = $('#password').val(),
                newpassword = $('#re_password').val();
        $.ajax({
            url: "http://localhost:9090/finalPojest/changePasswordServlet",
            type: "POST",
            data: {
                currentpassword: currentpassword,
                newpassword: newpassword
            },
            dataType: "json",
            success: function (result) {
                if (result['key'] === 0) {
                    alert("حاول مجددأ");
              
                } else if (result['key'] === 1){
                    alert("تم التعديل بنجاح");
                }
            },
            error: function () {
                console.log("Error");
            }
        });
    });
$('#cancelUpdateName').click(function(e){
       e.preventDefault();
        $('#showUpdateName').show();
        $('#hideUpdateName').hide();
        $('#updateNameForm').hide();
    
});
$('#cancelChangePassword').click(function(e){
        e.preventDefault();
        $('#showchangepassword').show();
        $('#hidechangepassword').hide();
        $('#changePassword').hide();
    
});

});

