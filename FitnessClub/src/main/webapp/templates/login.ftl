<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Login page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">

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
        <#--<div class="info"> 8-917-123-456 <br> Kazan <br> <br></div>-->
        <a href="/login" class="button">Log in</a>
        <a href="/signup" class="button">Sign up</a>
    </div>

</div>

<div>

<#if error_msg??>
    <h2 class="message">${error_msg}</h2>
</#if>

<#if message??>
    <h2 class="message">${message}¬</h2>
</#if>

    <form action="/j_spring_security_check" method="post">
        <fieldset>
            <legend>Log in</legend>
            <label for="name" class="label_input">Login</label>
            <input type="text" name="j_username" id="name" class="input_text"/>
        <#--<label> Login <input name="name" type="text" placeholder=""/> </label>-->
            <br/>
            <label for="password" class="label_input">Password</label>
            <input type="password" name="j_password" id="password" class="input_text" placeholder="">
            <br/>
            <input type="checkbox" name="_spring_security_remember_me" value="Remember me" id="remember"/>
            <label for="remember" class="label_remember">Remember me</label>
            <br/>
            <input type="submit" value="Log in" class="input_submit">
        </fieldset>
    </form>
</div>


</body>
