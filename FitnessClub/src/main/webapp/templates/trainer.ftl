<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Requests page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/profile.css">

</head>
<body>

<div class="header">
    <img style="float: left" src="../images/dyn.jpg" alt="logo" width="180" height="180">

    <div class="title">
        <b> ${clubname} </b> <br/>
        <b style="font-size: 16pt"> ${slogan} </b>
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

    <@sec.authorize ifAnyGranted="ROLE_ANONYMOUS">

        <a href="/login">Log in</a>
        <a href="/signup">Sign up</a>

    </@sec.authorize>
    <@sec.authorize access="isAuthenticated()">

        <a href="/user/profile">Hello, ${login}</a>
        <a href="/logout">Log out</a>

    </@sec.authorize>

    </div>
</div>

<@sec.authorize ifAnyGranted="ROLE_ADMIN">
<div class="admin_p">
    <p>You are an admin</p>
</div>
</@sec.authorize>

<@sec.authorize ifAnyGranted="ROLE_INSTRUCTOR">
<div class="admin_p">
    <p>You are an instructor</p>
</div>
</@sec.authorize>

<div class="main">

<#if instructor?has_content>
    <h1 class="instr_prof_h1"> ${instructor.user.name} ${instructor.user.surname}</h1>
    <div>
        <div class="trainer_one_line">
            <#if instructor.user.photo??>
                <img src="/images/users/${instructor.user.photo}" width="400" height="300" class="instr_prof_photo">
            <#else> <img src="/images/no_photo.jpg" width="300" height="300"  class="instr_prof_photo">
            </#if>
        </div>
        <div class="instr_prof_info">
            <p> <b>Qualification:</b>
                <#if instructor.qualification??> ${instructor.qualification}
                </#if></p>
            <p> <b>Awards:</b>
                <#if instructor.awards??> ${instructor.awards}
                </#if></p>
            <p> <b>Works since</b>
                <#if instructor.experience??> ${instructor.experience}
                </#if></p>
            <p> <b>Description:</b>
                <#if instructor.description??> ${instructor.description}
                </#if></p>
        </div>
    </div>
</#if>
</div>

</body>