<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Sign up page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/signup.css">

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
    <form action="/signup" method="post"<#-- enctype="multipart/form-data"-->>
        <fieldset>
            <legend>Sign up</legend>
            <input type="text" name="login" placeholder="Login"/>
            <br/>
            <input type="password" name="password" id="password" placeholder="Password">
            <br/>
            <input type="password" name="password_repeat" id="password_repeat" placeholder="Repeat your password">
            <br/>
            <input type="text" name="name" placeholder="Name">
            <br/>
            <input type="text" name="surname" placeholder="Surname">
            <br/>
            <input type="text" name="email" placeholder="Email address">
            <br/>
            <input type="text" name="phone" placeholder="Phone number">
            <br/>
            <input type="checkbox" name="trainer" id="trainer" value="true">
            <label for="trainer"> I'm a trainer </label>
            <br/>
            <input type="submit" value="Sign up">
        </fieldset>
    </form>
</div>


</body>

