<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Schedule </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/schedule.css">
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

<@sec.authorize ifAnyGranted="ROLE_ADMIN">
    <div class="admin_p" style="margin-top: 0">
        <a href="/admin/schedule">Modify schedule</a>
    </div>
</@sec.authorize>


    <div class="download_href_div">
        <a href="/schedule/download?output=pdf" class="download_href">Download schedule in PDF format</a>
    </div>
    <table>
        <thead>
        <tr>
            <td>Time \ Weekday</td>
            <td>MONDAY</td>
            <td>TUESDAY</td>
            <td>WEDNESDAY</td>
            <td>THURSDAY</td>
            <td>FRIDAY</td>
            <td>SATURDAY</td>
            <td>SUNDAY</td>
        </tr>
        </thead>
    <#if startTimes??>
        <#list startTimes as time>
            <tr>
                <td>${time}.00-${time}.55</td>
                <td>
                    <#if monday??>
                        <#if monday[time]??>
                    <p class="class_name">${monday[time].classByClassId.name} </p>
                    <p class="trainer_name">${monday[time].instructor.user.name}</p>
                    </#if>
                    </#if>
                </td>
                <td>
                    <#if tuesday??>
                        <#if tuesday[time]??>
                    <p class="class_name">${tuesday[time].classByClassId.name}</p>
                    <p class="trainer_name">${tuesday[time].instructor.user.name}</p>
                    </#if>
                    </#if>
                </td>
                <td>
                    <#if wednesday??>
                        <#if wednesday[time]??>
                    <p class="class_name">${wednesday[time].classByClassId.name} </p>
                    <p class="trainer_name">${wednesday[time].instructor.user.name}</p>
                    </#if>
                    </#if>
                </td>
                <td>
                    <#if thursday??>
                        <#if thursday[time]??>
                    <p class="class_name">${thursday[time].classByClassId.name} </p>
                    <p class="trainer_name">${thursday[time].instructor.user.name}</p>
                    </#if>
                    </#if>
                </td>
                <td>
                    <#if friday??>
                        <#if friday[time]??>
                    <p class="class_name">${friday[time].classByClassId.name} </p>
                    <p class="trainer_name">${friday[time].instructor.user.name}</p>
                    </#if>
                    </#if>
                </td>
                <td>
                    <#if saturday??>
                        <#if saturday[time]??>
                    <p class="class_name">${saturday[time].classByClassId.name} </p>
                    <p class="trainer_name">${saturday[time].instructor.user.name}</p>
                    </#if>
                    </#if>
                </td>
                <td>
                    <#if sunday??>
                        <#if sunday[time]??>
                    <p class="class_name">${sunday[time].classByClassId.name} </p>
                    <p class="trainer_name">${sunday[time].instructor.user.name}</p>
                    </#if>
                    </#if>
                </td>
            </tr>
        </#list>
    </#if>
    </table>

<#if monday??>
    <p>${monday["9"].classByClassId.name}</p>
</#if>


</div>

</body>