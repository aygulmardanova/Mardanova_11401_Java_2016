<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Our trainers </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <link rel="stylesheet" type="text/css" href="../css/trainers.css"/>

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
    <@sec.authorize ifAnyGranted="ROLE_ANONYMOUS">

        <a href="/login">Log in</a>
        <a href="/signup">Sign up</a>

    </@sec.authorize>
    <@sec.authorize access="isAuthenticated()">

        <a href="/profile">Hello, ${login}</a>
        <a href="/logout">Log out</a>

    </@sec.authorize>
    </div>

</div>

<div class="trainers_main">

    <h1>Our trainers</h1>
<#if instructors?has_content>
    <#list instructors as instr>


        <div>
            <div class = "trainer_one_line">
                <#if instr.user.photo??>
                    <img  src = "/images/users/${instr.user.photo}" width = "200" height = "200">
                <#else> <img src = "/images/no_photo.jpg" width = "200" height = "200">
                </#if>
            <p class = "trainer_href"><a href = "/trainer/${instr.id}"> ${instr.user.name} ${instr.user.surname} </a></p>

            </div>
        </div>

        <hr size=1px">
    </#list>
<#else>
    <p>There are no trainers in our club yet</p>
</#if>

</div>


</body>