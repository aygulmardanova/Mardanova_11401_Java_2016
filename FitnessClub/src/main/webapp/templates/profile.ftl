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
    <#if message??>
    <p class="message">${message}</p>
    </#if>
</@sec.authorize>

<#if success??>
<h3>${success}</h3>
</#if>

<#if bought??>
<h3>${bought}</h3>
</#if>

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
        <div class="admin_p">
            <a href="/user/settings" class="settings_a">Settings</a>
        </div>
    <@sec.authorize ifAnyGranted="ROLE_USER">
        <div class="admin_p">
            <a href="/user/buy-subscr" class="settings_a">Buy subscription</a>
        </div>
    </@sec.authorize>
    <@sec.authorize ifAnyGranted="ROLE_ADMIN">
        <div class="admin_p">
            <a href="/admin/requests" class="settings_a">Show requests</a>
        </div>
    </@sec.authorize>
    <@sec.authorize ifAnyGranted="ROLE_ADMIN">
        <div class="admin_p">
            <a href="/admin/add-class" class="settings_a">Add new class</a>
        </div>
    </@sec.authorize>
    <@sec.authorize ifAnyGranted="ROLE_ADMIN">
        <div class="admin_p">
            <a href="/admin/schedule" class="settings_a">Modify schedule</a>
        </div>
    </@sec.authorize>
    <@sec.authorize ifAnyGranted="ROLE_ADMIN">
        <div class="admin_p">
            <a href="/admin/edit-prices" class="settings_a">Edit prices</a>
        </div>
    </@sec.authorize>
    <@sec.authorize ifAnyGranted="ROLE_INSTRUCTOR">
        <div class="admin_p">
            <a href="/user/instr-settings" class="settings_a">Instructor settings</a>
        </div>
    </@sec.authorize>
    </div>

</div>

<div class="main">

<@sec.authorize ifAnyGranted="ROLE_USER">
    <h2>Your purchases</h2>
    <ul>
        <#if user.purchases??>
            <#list user.purchases as p>
                <li> ${p.subscription.validity} months since ${p.buyDate}</li>
            </#list>
        </#if>
    </ul>
</@sec.authorize>

<@sec.authorize ifAnyGranted="ROLE_INSTRUCTOR">
    <div class="instructor_info">

        <table>
            <#if instructor.description??>
                <tr>
                    <td class="name_td"> Description:</td>
                    <td> ${instructor.description}</td>
                </tr>
            </#if>
            <#if instructor.awards??>
                <tr>
                    <td class="name_td"> Awards:</td>
                    <td> ${instructor.awards}</td>
                </tr>
            </#if>
            <#if instructor.qualification??>
                <tr>
                    <td class="name_td"> Qualification:</td>
                    <td> ${instructor.qualification}</td>
                </tr>
            </#if>
            <#if instructor.experience??>
                <tr>
                    <td class="name_td"> Experience since</td>
                    <td> ${instructor.experience}</td>
                </tr>
            </#if>
        </table>

    </div>
</@sec.authorize>
</div>

</body>