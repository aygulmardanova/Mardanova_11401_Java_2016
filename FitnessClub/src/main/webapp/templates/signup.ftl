<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Sign up page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">

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
    <form action="/signup" method="post"<#-- enctype="multipart/form-data"-->>
        <fieldset>
            <legend>Sign up</legend>
            <label> Login <input type="text" name="login" placeholder=""/> </label>
            <br/>
            <label> Password <input type="password" name="password" id="password" placeholder=""> </label>
            <br/>
            <label> Repeat your password
                <input type="password" name="password_repeat" id="password_repeat" placeholder="">
            </label>
            <br/>
            <label> Name <input type="text" name="name" placeholder=""> </label>
            <br/>
            <label> Surname <input type="text" name="surname" placeholder=""> </label>
            <br/>
            <label> Email <input type="text" name="email" placeholder=""> </label>
            <br/>
            <label> Phone number <input type="text" name="phone" placeholder=""> </label>
            <br/>
            <label> I'm a trainer <input type="checkbox" name="trainer" value="true"> </label>
            <br/>
            <input type="submit" value="Sign up">
        </fieldset>
    </form>
</div>


</body>

