<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Classes </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <link rel="stylesheet" type="text/css" href="../css/classes.css"/>
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

<div class="classes_main">

    <h1>Our classes</h1>
<#if classes?has_content>
    <#list classes as class>

        <div class="classes_div">
            <div class="classes_one_line">
                <#if class.photo??>
                    <img src="/images/classes/${class.photo}" width="600" height="300" alt="No photo">
                <#else> <img src="/images/no_photo.jpg" width="300" height="300">
                </#if>
                <h3>${class.name}</h3>
                <p>${class.description} </p>
                </p>

            </div>
        </div>

        <hr size=1px color="#ccc">
    </#list>
<#else>
    <p>There are no trainers in our club yet</p>
</#if>

</div>


</body>
