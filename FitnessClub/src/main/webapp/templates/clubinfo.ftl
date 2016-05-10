<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> About club page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <link rel="stylesheet" type="text/css" href="../css/clubinfo.css">

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

<div class="admin_p">
    <a href="/about-us/download" class="about_us_pdf_href">Download info about club in PDF format</a>
</div>

<div class="clubinfo_main">

    <h1>${clubname}</h1>
    <h2>${slogan}</h2>
    <h2 class="club_info"> Contacts: </h2>
    <ul>
        <li>Phone: ${phone_number}</li>
        <li>Email: ${email}</li>
        <li>Address: ${address}</li>
    </ul>

    <h2 class="club_info"> Work hours: </h2>
    <ul>
        <li>Mn-Fr: ${work_week_open} to ${work_week_close}</li>
        <li>Sat-Sn: ${weekend_open} to ${weekend_close}</li>
    </ul>
    <h3> Classes continues for ${class_duration} minutes</h3>


    <h2 class="club_info"> Subscriptions </h2>
<#if subscriptions?has_content>

    <ul>
        <#list subscriptions as s>
            <li> for ${s.validity} months - ${s.price} </li>
        </#list>
    </ul>
</#if>

</div>
</body>
