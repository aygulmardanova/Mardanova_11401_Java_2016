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

        <a href="/admin/profile">Hello, ${login}</a>
        <a href="/logout">Log out</a>

    </div>
</div>

<div class="main">

    <div class="photo">
    <#if photo??>
        <img src="/images/users/${photo}" alt="No photo"/>
    <#else>
        <img src="/images/no_photo.jpg"/>
    </#if>
    </div>
    <div class="info">
        <p>Login: ${user.login}</p>
        <p>Email: ${user.email}</p>
        <p>Name: ${user.name}</p>
        <p>Surname: ${user.surname}</p>
        <p>Phone number: ${user.phone_number}</p>
    </div>
    <a href = "/settings"></a>

</div>


</body>