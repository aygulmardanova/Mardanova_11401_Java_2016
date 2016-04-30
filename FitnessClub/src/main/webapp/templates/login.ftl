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
        <b> Fitness Club </b> <br/>
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
        <div class="info"> 8-917-123-456 <br> Kazan <br> <br></div>
        <a href="/login" class="button">Log in</a>
        <a href="/signup" class="button">Sign up</a>
    </div>

</div>

<div>
    <#if error_msg??>
        <p>${error_msg}</p>
    </#if>

    <form action="/j_spring_security_check" method="post">
        <fieldset>
            <legend>Log in</legend>
            <label for="name">Login</label>
            <input type="text" name="j_username" id = "name"/>
            <#--<label> Login <input name="name" type="text" placeholder=""/> </label>-->
             <br/>
            <label> Password <input name="j_password" type="password" placeholder=""> </label>
             <br/>
            <input type="checkbox" name="_spring_security_remember_me" value="Remember me">Remember me</input>
            <br/>
            <input type="submit" value="Log in">
        </fieldset>
    </form>
</div>


</body>
