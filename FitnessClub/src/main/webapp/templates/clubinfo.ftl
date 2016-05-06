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

<div>

    <h2>Club's name: ${clubname}</h2>
    <h2> Our prices</h2>
    <#if subscriptions?has_content>
        <#list subscriptions as s>
            <li>
                <ul> for ${s.validity} months - ${s.price} </ul>
            </li>
        </#list>
    </#if>

</div>
</body>
