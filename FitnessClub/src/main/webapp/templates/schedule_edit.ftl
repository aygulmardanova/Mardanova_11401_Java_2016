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

<div class="admin_p" style="margin-top: 0">
    <a href="/schedule" class="back_schedule_href" style="margin-right: 0">Back to schedule page</a>
</div>

<div class="main">

<#if message??>
    <h2 class="added_message"> ${message}</h2>
</#if>

    <form action="/schedule/edit" method="post" class="schedule_edit_form">
        <select name="startTime" required id="startTime">
        <#list startTimes as time>
            <option value="${time}">${time}</option>
        </#list>
        </select>
        <select name="instr_id" required id="instructor">
        <#list instructors as instr>
            <option value="${instr.id}">${instr.user.name} ${instr.user.surname}</option>
        </#list>
        </select>
        <select name="class_id" required id="class">
        <#list classes as class>
            <option value="${class.id}">${class.name}</option>
        </#list>
        </select>
        <select name="weekDay" required id="weekDay">
        <#list weekDays as day>
            <option value="${day}">${day}</option>
        </#list>
        </select>
        <br/>
        <div class="add_class_submit">
            <input type="submit" value="Add"/>
        </div>
    </form>


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
                            <form class="delete_form" action="/schedule/delete" method="post">
                                <input type="hidden" name="startTime" value="${time}">
                                <input type="hidden" name="day" value="MONDAY">
                                <input type="submit" class="delete_submit" value="">
                            </form>
                            <p class="class_name">${monday[time].classByClassId.name}</p>
                            <a class="trainer_name"
                               href="/trainer/profile?id=${monday[time].instructor.id}">${monday[time].instructor.user.name}</a>
                        </#if>
                    </#if>
                </td>
                <td>
                    <#if tuesday??>
                        <#if tuesday[time]??>
                            <form class="delete_form" action="/schedule/delete" method="post">
                                <input type="hidden" name="startTime" value="${time}">
                                <input type="hidden" name="day" value="TUESDAY">
                                <input type="submit" class="delete_submit" value="">
                            </form>
                            <p class="class_name">${tuesday[time].classByClassId.name}</p>
                            <a class="trainer_name"
                               href="/trainer/profile?id=${tuesday[time].instructor.id}">${tuesday[time].instructor.user.name}</a>
                        </#if>
                    </#if>
                </td>
                <td>
                    <#if wednesday??>
                        <#if wednesday[time]??>
                            <form class="delete_form" action="/schedule/delete" method="post">
                                <input type="hidden" name="startTime" value="${time}">
                                <input type="hidden" name="day" value="WEDNESDAY">
                                <input type="submit" class="delete_submit" value="">
                            </form>
                            <p class="class_name">${wednesday[time].classByClassId.name}</p>
                            <a class="trainer_name"
                               href="/trainer/profile?id=${wednesday[time].instructor.id}">${wednesday[time].instructor.user.name}</a>
                        </#if>
                    </#if>
                </td>
                <td>
                    <#if thursday??>
                        <#if thursday[time]??>
                            <form class="delete_form" action="/schedule/delete" method="post">
                                <input type="hidden" name="startTime" value="${time}">
                                <input type="hidden" name="day" value="THURSDAY">
                                <input type="submit" class="delete_submit" value="">
                            </form>
                            <p class="class_name">${thursday[time].classByClassId.name}</p>
                            <a class="trainer_name"
                               href="/trainer/profile?id=${thursday[time].instructor.id}">${thursday[time].instructor.user.name}</a>
                        </#if>
                    </#if>
                </td>
                <td>
                    <#if friday??>
                        <#if friday[time]??>
                            <form class="delete_form" action="/schedule/delete" method="post">
                                <input type="hidden" name="startTime" value="${time}">
                                <input type="hidden" name="day" value="FRIDAY">
                                <input type="submit" class="delete_submit" value="">
                            </form>
                            <p class="class_name">${friday[time].classByClassId.name}</p>
                            <a class="trainer_name"
                               href="/trainer/profile?id=${friday[time].instructor.id}">${friday[time].instructor.user.name}</a>
                        </#if>
                    </#if>
                </td>
                <td>
                    <#if saturday??>
                        <#if saturday[time]??>
                            <form class="delete_form" action="/schedule/delete" method="post">
                                <input type="hidden" name="startTime" value="${time}">
                                <input type="hidden" name="day" value="SATURDAY">
                                <input type="submit" class="delete_submit" value="">
                            </form>
                            <p class="class_name">${saturday[time].classByClassId.name}</p>
                            <a class="trainer_name"
                               href="/trainer/profile?id=${saturday[time].instructor.id}">${saturday[time].instructor.user.name}</a>
                        </#if>
                    </#if>
                </td>
                <td>
                    <#if sunday??>
                        <#if sunday[time]??>
                            <form class="delete_form" action="/schedule/delete" method="post">
                                <input type="hidden" name="startTime" value="${time}">
                                <input type="hidden" name="day" value="SUNDAY">
                                <input type="submit" class="delete_submit" value="">
                            </form>
                            <p class="class_name">${sunday[time].classByClassId.name}</p>
                            <a class="trainer_name"
                               href="/trainer/profile?id=${sunday[time].instructor.id}">${sunday[time].instructor.user.name}</a>
                        </#if>
                    </#if>
                </td>
            </tr>
        </#list>
    </#if>
    </table>

</div>

</body>