<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Sign up page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/signup.css">

    <script type="text/javascript" src="../js/libs/jquery-1.7.1.min.js"></script>
    <#--<script type="text/javascript" src="../js/checkPass.js"></script>-->

    <script>

        //password
        function checkPass() {
            with (document)
                getElementById('info').innerHTML = (getElementById('password').value != getElementById('password_repeat').value) ?
                        'Password repeated incorrectly!' : '';
        }


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

    </script>

</head>

<body>

<div class="header">
    <img style="float: left" src="../images/dyn.jpg" alt="logo" width="180" height="180">

    <div class="title">
        <b> ${clubname} </b> <br/>
        <b style="font-size: 16pt"> Forever fit, forever strong! </b>
    </div>

    <div class="navigation">
        <a href="/main">Main</a>
        <a href="/trainers">Instructors</a>
        <a href="/schedule">Schedule</a>
        <a href="/prices">Prices</a>
        <a href="/about-us">About us</a>
    </div>

    <div class="buttons">
        <div class="info"> ${phone_number} <br> Kazan <br> <br></div>
        <a href="/login" class="button">Log in</a>
        <a href="/signup" class="button">Sign up</a>
    </div>
</div>

<div>

    <#if message??>
        <h2 class="message_h">${message}</h2>
    </#if>

    <form action="/signup" method="post"<#-- enctype="multipart/form-data"-->>
        <fieldset>
            <legend>Sign up</legend>
            <input type="text" name="login" placeholder="Login" required/>
            <br/>
            <input type="password" name="password" id="password" placeholder="Password" required>
            <br/>
            <input type="password" name="password_repeat" id="password_repeat" onchange="checkPass()" placeholder="Repeat your password" required>
            <p class="pass_msg" id="info"></p>
            <input type="text" name="name" placeholder="Name" required>
            <br/>
            <input type="text" name="surname" placeholder="Surname" required>
            <br/>
            <input type="text" name="email" id="email" placeholder="Email address" required>
            <p class="pass_msg" id="valid"></p>
            <input type="text" name="phone" placeholder="Phone number" required>
            <br/>
            <input type="checkbox" name="trainer" id="trainer" value="true">
            <label for="trainer"> I'm a trainer </label>
            <br/>
            <input type="submit" value="Sign up" id="submit">
        </fieldset>
    </form>
</div>

</body>

