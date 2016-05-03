<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Requests page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/profile.css">

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

        <a href="/user/profile">Hello, ${login}</a>
        <a href="/logout">Log out</a>

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
    <#if message??>
        <p class="message">${message}</p>
    </#if>
</@sec.authorize>

<div class="main">

    <div class="photo">
    <#if user.photo??>
        <img src="/images/users/${user.photo}" alt="No photo"/>
    <#else>
        <img src="/images/no_photo.jpg"/>
    </#if>
    </div>
    <div class="user_info">
        <p>Login: ${user.login}</p>
        <p>Email: ${user.email}</p>
        <p>Name: ${user.name}</p>
        <p>Surname: ${user.surname}</p>
        <p>Phone number: ${user.phoneNumber}</p>
    </div>
    <div class="profile_hrefs">
        <a href="/user/settings" class="settings_a">Settings</a>
    <@sec.authorize ifAnyGranted="ROLE_ADMIN">
        <div class="admin_p">
            <a href="/admin/requests" class="settings_a">Show requests</a>
        </div>
    </@sec.authorize>
    <#--<@sec.authorize ifAnyGranted="ROLE_INSTRUCTOR">
        <div class="admin_p">
            <a href="/admin/requests" class="settings_a">Extended profile</a>
        </div>
    </@sec.authorize>-->
    </div>

    <div class="instructor_info">
    <#if instructor.description??>
        <p>Description: ${instructor.description}</p>
    </#if>
    <#if instructor.awards??>
        <p>Awards: ${instructor.awards}</p>
    </#if>
    <#if instructor.qualification??>
        <p>Qualification: ${instructor.qualification}</p>
    </#if>
    <#if instructor.experience??>
        <p>Experience since ${instructor.experience}</p>
    </#if>
    </div>
</div>

</body>