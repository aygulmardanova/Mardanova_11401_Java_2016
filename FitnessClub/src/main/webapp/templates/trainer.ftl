<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Requests page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/admin_profile.css">

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

<div class="main">

    <#--<div class="photo">
    <#if instructor.user.photo??>
        <img src="/images/users/${instructor.user.photo}" alt="No photo"/>
    <#else>
        <img src="/images/no_photo.jpg"/>
    </#if>
    </div>
    <div class="info">
        <p>Name: ${instructor.user.name}</p>
        <p>Surname: ${instructor.user.surname}</p>
        <p>Qualification: ${instructor.qualification}</p>
        <p>Works since: ${instructor.experience}</p>
        <p>Awards: ${instructor.awards}</p>
        <p>Description: ${instructor.description}</p>
    </div>-->


    <h1> ${instructor.name} ${instructor.surname}</h1>
<#if instructor?has_content>
    <div>
        <div class="trainer_one_line">
            <#if instructor.user.photo??>
                <img src="/images/recipes/${instructor.user.photo}" width="300" height="200">
            <#else> <img src="/images/no_photo.jpg" width="300" height="200">
            </#if>
        </div>

        <p> ${instructor.user.name} ${instructor.user.surname} </p>

        <p> Qualification:
            <#if instructor.qualification??> ${instructor.qualification}
            </#if></p>
        <p> Awards:
            <#if instructor.awards??> ${instructor.awards}
            </#if></p>
        <p> Works since
            <#if instructor.experience??> ${instructor.experience}
            </#if></p>
        <p> Description:
            <#if instructor.description??> ${instructor.description}
            </#if></p>
    </div>
</#if>

</body>