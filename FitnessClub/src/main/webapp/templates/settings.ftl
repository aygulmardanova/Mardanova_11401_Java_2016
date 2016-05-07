<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Settings page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/profile.css">
    <link rel="stylesheet" type="text/css" href="../css/settings.css">

    <script type="text/javascript" src="../js/libs/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../js/validation.js"></script>

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

<#if message??>
    <h3>${message}</h3>
</#if>
<div class="settings_main">
    <form method="post" action="/user/settings" enctype="multipart/form-data">

        <div class="settings_inline" style="margin-bottom: 30px">
            <div class="settings_photo">
            <#if user.photo??>
                <img src="/images/users/${user.photo}" alt="No photo"/>
            <#else>
                <img src="/images/no_photo.jpg"/>
            </#if>
            </div>

            <input type="file" name="photo" value="New photo"><br/>

            <div class="hrefs">
                <a href="/user/profile" class="hrefs_a">Profile</a>
            </div>
        <@sec.authorize ifAnyGranted="ROLE_INSTRUCTOR">
            <div class="hrefs">
                <a href="/user/instr-settings" class="hrefs_a">Instructor settings</a>
            </div>
        </@sec.authorize>
        </div>

        <div class="settings_inline">
            <div class="label_div"><label for="login">Login</label></div>
            <input type="text" name="login" id="login" placeholder="${user.login}">
            <p class="pass_msg" id="login_valid"></p>
        </div>

        <div class="settings_inline">
            <div class="label_div"><label for="name">Name</label></div>
            <input type="text" name="name" id="name" placeholder="${user.name}">
        </div>

        <div class="settings_inline">
            <div class="label_div"><label for="surname">Surname</label></div>
            <input type="text" name="surname" id="surname" placeholder="${user.surname}">
        </div>

        <div class="settings_inline">
            <div class="label_div"><label for="email">Email</label></div>
            <input type="text" name="email" id="email" placeholder="${user.email}">
            <p class="pass_msg" id="valid"></p>
        </div>

        <div class="settings_inline">
            <div class="label_div"><label for="password">Password</label></div>
            <input type="password" name="old_password" id="old_password" placeholder="Enter your old password">
            <input type="password" name="new_password" id="password" placeholder="Enter new password">
            <input type="password" name="new_password_repeat" id="password_repeat" placeholder="Repeat your new password">
            <p class="pass_msg" id="correct" style="text-align: right; margin-right: 66px;"></p>
            <p class="pass_msg" id="info" style="text-align: right; margin-right: 66px;"></p>
        </div>

        <div class="settings_inline">
            <div class="label_div"><label for="phone">Phone number</label></div>
            <input type="text" name="phone" id="phone" placeholder="${user.phoneNumber}">
            <p class="pass_msg" id="phone_valid"></p>
        </div>

        <input type="hidden" name="user_id" value="${user.id}">
        <input type="submit" value="Change profile" class="submit">
    </form>
</div>

</body>