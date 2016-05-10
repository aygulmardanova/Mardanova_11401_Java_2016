correctLogin = function () {
    var login = $("#login").val();
    if (login != '') {
        var pattern = /^[a-zA-Z][a-zA-Z0-9-_\.]{3,20}$/i;
        $.ajax({
            type: 'POST',
            url: "/checkLogin",
            data: {"login": login},
            dataType: "text",
            async: false,
            success: function (response_data) {
                if (response_data == 'true') {
                    $("#login").css({'border': '1px solid black'});
                    $("#login_valid").html("Login is busy");
                } else {
                    if (pattern.test(login)) {
                        $('#login').css({'border': '1px solid #569b44'});
                        $('#login_valid').text('');
                    } else if (login.length < 4) {
                        $('#login').css({'border': '1px solid black'});
                        $('#login_valid').text('Login length should be > 3');
                    } else if (login.length > 20) {
                        $('#login').css({'border': '1px solid black'});
                        $('#login_valid').text('Login length should be < 20');
                    } else {
                        $('#login').css({'border': '1px solid black'});
                        $('#login_valid').text('Irregular symbols in login');
                    }
                }
            }
        });
    } else {
        $('#login').css({'border': '1px solid black'});
        $('#login_valid').text('Login should not be empty');
    }
}

correctPass = function () {
    var pass = $("#password").val();
    var pattern = /^[a-zA-Z0-9]{5,20}$/i;
    if (pass != '') {
        if (pattern.test(pass)) {
            $('#password').css({'border': '1px solid #569b44'});
            $('#correct').text('');
        } else if (pass.length < 5) {
            $('#password').css({'border': '1px solid black'});
            $('#correct').text('Password length should be > 4');
        } else if (pass.length > 20) {
            $('#password').css({'border': '1px solid black'});
            $('#correct').text('Password length should be < 20');
        } else {
            $('#password').css({'border': '1px solid black'});
            $('#correct').text('Irregular symbols in password');
        }
    } else {
        $('#password').css({'border': '1px solid black'});
        $('#correct').text('Password should not be empty');
    }
}

correctRepeat = function () {
    var pass1 = $("#password").val();
    var pass2 = $("#password_repeat").val();
    if (pass2 != '') {
        if (pass1 != pass2) {
            $('#password_repeat').css({'border': '1px solid black'});
            $('#info').text('Password repeated incorrectly');
        } else {
            $('#password_repeat').css({'border': '1px solid #569b44'});
            $('#info').text('');
        }
    } else {
        $('#password_repeat').css({'border': '1px solid black'});
        $('#info').text('Repeat your password');
    }
}

correctEmail = function () {
    var email = $("#email").val();
    if (email != '') {
        var pattern = /^([a-z0-9_\.-])+@[a-z0-9-]+\.([a-z]{2,4}\.)?[a-z]{2,4}$/i;
        if (pattern.test(email)) {
            $('#email').css({'border': '1px solid #569b44'});
            $('#valid').text('');
        } else {
            $('#email').css({'border': '1px solid black'});
            $('#valid').text('Incorrect email');
        }
    } else {
        $('#email').css({'border': '1px solid black'});
        $('#valid').text('Email field should not be empty');
    }
}

correctPhone = function () {
    var phone = $("#phone").val();
    if (phone != '') {
        var pattern = /^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$/i;
        if (pattern.test(phone)) {
            $('#phone').css({'border': '1px solid #569b44'});
            $('#phone_valid').text('');
        } else {
            $('#phone').css({'border': '1px solid black'});
            $('#phone_valid').text('Incorrect phone number');
        }
    } else {
        $('#phone').css({'border': '1px solid black'});
        $('#phone_valid').text('Phone number should not be empty');
    }
}

correctDate = function () {
    var date = $("#date").val();
    if (date != '') {
        var arrD = document.getElementById('date').value.split(".");
        arrD[1] -= 1;
        var d = new Date(arrD[2], arrD[1], arrD[0]);

        function TstDate() {
            if ((d.getFullYear() == arrD[2]) && (d.getMonth() == arrD[1]) && (d.getDate() == arrD[0])) {
                return true;
            } else {
                return false;
            }
        }

        var S = TstDate()
        if (S) {
            $('#date').css({'border': '1px solid #569b44'});
            $('#date_valid').text('');
        } else {
            $('#date').css({'border': '1px solid black'});
            $('#date_valid').text('Incorrect date format');
        }
    }
}









