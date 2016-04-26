<#ftl encoding='UTF-8'>
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
        <a href="/login" class="button">Log in</a>
        <a href="/signup" class="button">Sign up</a>
    </div>

</div>

<div class="trainers_main">

    <h1>Our trainers</h1>
<#if instructors?has_content>
    <#list instructors as instr>
        <div class="trainer_line">
            <img src="/images/users/${instr.user.photo}" height="300" width="300"/>
        ${instr.user.name} ${instr.user.surname}:<br/>
            <p>Description: </p>
        ${instr.description} <br/>
            <p>Awards: </p>
        ${instr.awards} <br/>
            <p>Works since: </p>
        ${instr.experience} <br/>
        </div>
    </#list>
<#else>
    <p>There are no trainers in our club yet</p>
</#if>

</div>


</body>
