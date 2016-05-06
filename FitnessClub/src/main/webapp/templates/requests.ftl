<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Requests page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/trainers.css">

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

<div class="trainers_main">
<#if pi?has_content>
    <h3>Probably instructors. Please, accept or reject the requests</h3>
    <#list pi as user>

        <div class="trainers_div">
            <div class="trainer_one_line">
                <#if user.photo??>
                    <img src="/images/users/${user.photo}" width="200" height="200">
                <#else> <img src="/images/no_photo.jpg" width="200" height="200">
                </#if>

                <div class="trainer_info">
                    <p> ${user.name} ${user.surname}</p>
                    <p> ${user.email}</p>
                    <p> ${user.phoneNumber}</p>
                </div>

                <div class="trainer_buttons">
                    <form action="/admin/requests" method="post">
                        <input type="hidden" name="user_id" value="${user.id}"/>
                        <input type="hidden" name="result" value="accept"/>
                        <input type="submit" value="Accept"/>
                    </form>
                    <form action="/admin/requests" method="post">
                        <input type="hidden" name="user_id" value="${user.id}"/>
                        <input type="hidden" name="result" value="reject"/>
                        <input type="submit" value="Reject"/>
                    </form>
                </div>
            </div>
        </div>

        <hr size=1px color="#ccc">
    </#list>
<#else>
    <h4>No probably instructors have registered yet</h4>

    <p><a href="/admin/requests_schedule" class="check_href"> Check requests for changes in schedule</a></p>
</#if>

</div>

</body>