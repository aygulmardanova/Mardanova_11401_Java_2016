<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Login page </title>

    <link rel="stylesheet" type="text/css" href="../../resources/css/header.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/login.css">

</head>

<body>


<div class="header">
    <img style="float: left" src="../../resources/images/dyn.jpg" alt="logo" width="180" height="180">

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
    <form action="/login" method="post">
        <fieldset>
            <legend>Log in</legend>
            <label> Login <input name="name" type="text" placeholder=""/> </label>
             <br/>
            <label> Password <input name="password" type="password" placeholder=""> </label>
             <br/>
            <input type="submit" value="Log in">
        </fieldset>
    </form>
</div>


</body>
