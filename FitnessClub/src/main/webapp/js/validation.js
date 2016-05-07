//login
$(document).ready(function() {
    $('#login').blur(function() {
        if($(this).val() != '') {
            var pattern = /^[a-zA-Z][a-zA-Z0-9-_\.]{3,20}$/i;
            if(pattern.test($(this).val())) {
                $(this).css({'border' : '1px solid #569b44'});
                $('#login_valid').text('');
            } else if ($(this).val().length < 4) {
                $(this).css({'border' : '1px solid black'});
                $('#login_valid').text('Login length should be > 4');
            } else if ($(this).val().length > 20) {
                $(this).css({'border' : '1px solid black'});
                $('#login_valid').text('Login length should be < 20');
            } else {
                $(this).css({'border' : '1px solid black'});
                $('#login_valid').text('Irregular symbols in login');
            }
        } else {
            $(this).css({'border' : '1px solid black'});
            $('#login_valid').text('Login should not be empty');
        }
    });
});

//password
$(document).ready(function() {
    $('#password').blur(function() {
        if($(this).val() != '') {
            var pattern = /^[a-zA-Z0-9]{5,20}$/i;
            if(pattern.test($(this).val())) {
                $(this).css({'border' : '1px solid #569b44'});
                $('#correct').text('');
            } else if ($(this).val().length < 5) {
                $(this).css({'border' : '1px solid black'});
                $('#correct').text('Password length should be > 5');
            } else if ($(this).val().length > 20) {
                $(this).css({'border' : '1px solid black'});
                $('#correct').text('Password length should be < 20');
            } else {
                $(this).css({'border' : '1px solid black'});
                $('#correct').text('Irregular symbols in password');
            }
        } else {
            $(this).css({'border' : '1px solid black'});
            $('#correct').text('Password should not be empty');
        }
    });
});

//Repeated password
$(document).ready(function() {
    $('#password_repeat').blur(function() {
        var p1 = $("#password").val();
        var p2 = $("#password_repeat").val();
        if(p2 != '') {
            if (p1 != p2) {
                $(this).css({'border' : '1px solid black'});
                $('#info').text('Password repeated incorrectly');
            } else {
                $(this).css({'border' : '1px solid #569b44'});
                $('#info').text('');
            }
        } else {
            $(this).css({'border' : '1px solid black'});
            $('#info').text('Repeat your password');
        }
    });
});

//email
$(document).ready(function() {
    $('#email').blur(function() {
        if($(this).val() != '') {
            var pattern = /^([a-z0-9_\.-])+@[a-z0-9-]+\.([a-z]{2,4}\.)?[a-z]{2,4}$/i;
            if(pattern.test($(this).val())){
                $(this).css({'border' : '1px solid #569b44'});
                $('#valid').text('');
            } else {
                $(this).css({'border' : '1px solid black'});
                $('#valid').text('Incorrect email');
            }
        } else {
            $(this).css({'border' : '1px solid black'});
            $('#valid').text('Email field should not be empty');
        }
    });
});

//mobile phone
$(document).ready(function() {
    $('#phone').blur(function() {
        if($(this).val() != '') {
            var pattern = /^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$/i;
            if(pattern.test($(this).val())){
                $(this).css({'border' : '1px solid #569b44'});
                $('#phone_valid').text('');
            } else {
                $(this).css({'border' : '1px solid black'});
                $('#phone_valid').text('Incorrect phone number');
            }
        } else {
            $(this).css({'border' : '1px solid black'});
            $('#phone_valid').text('Phone number should not be empty');
        }
    });
});

//date
$(document).ready(function () {
    $('#date').blur(function () {
        if ($(this).val() != '') {
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
                $(this).css({'border': '1px solid #569b44'});
                $('#date_valid').text('');
            } else {
                $(this).css({'border': '1px solid black'});
                $('#date_valid').text('Incorrect date format');
            }
        }
    });
});



