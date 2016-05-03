<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Requests page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/profile.css">
    <link rel="stylesheet" type="text/css" href="../css/settings.css">

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
</@sec.authorize>

<div class="settings_main">

    <div class="settings_inline" style="margin-bottom: 30px">
        <div class="settings_photo">
        <#if user.photo??>
            <img src="/images/users/${user.photo}" alt="No photo"/>
        <#else>
            <img src="/images/no_photo.jpg"/>
        </#if>
        </div>

        <form class="photo_form" method="POST" action="/user/settings" enctype="multipart/form-data">
            <input type="file" name="photo" value="New photo"><br/>

            <input type="submit" value="Change photo" class="submit">
        </form>
    </div>

    <div class="settings_inline">
        <div>
            <form method="post" action="/user/settings">
                <label for="login">Login</label>
                <input type="text" name="login" id="login" placeholder="${user.login}">
                <input type="hidden" name="field" value="login">
                <input type="submit" value="Change login">
            </form>
        </div>
    </div>

    <div class="settings_inline">
        <div>
            <form method="post" action="/user/settings">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" placeholder="${user.name}">
                <input type="hidden" name="field" value="name">
                <input type="submit" value="Change name">
            </form>
        </div>
    </div>

    <div class="settings_inline">
        <div>
            <form method="post" action="/user/settings">
                <label for="surname">Surname</label>
                <input type="text" name="surname" id="surname" placeholder="${user.surname}">
                <input type="hidden" name="field" value="surname">
                <input type="submit" value="Change surname">
            </form>
        </div>
    </div>

    <div class="settings_inline">
        <div>
            <form method="post" action="/user/settings">
                <label for="email">Email</label>
                <input type="text" name="email" id="email" placeholder="${user.email}">
                <input type="hidden" name="field" value="email">
                <input type="submit" value="Change email">
            </form>
        </div>
    </div>

    <div class="settings_inline">
        <div>
            <form method="post" action="/user/settings">
                <label for="phone">Phone number</label>
                <input type="text" name="phone" id="phone" placeholder="${user.phoneNumber}">
                <input type="hidden" name="field" value="phone">
                <input type="submit" value="Change phone number">
            </form>
        </div>
    </div>

</div>

</body>