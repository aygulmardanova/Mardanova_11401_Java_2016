//password
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



