<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Requests page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">

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
<#if pi??>
    <#list pi as instr>
        <img src="/images/users/${instr.photo}" alt="${instr.photo}"/>

        <p> ${instr.name} + ${instr.surname}</p>

        <p> ${instr.email}, ${instr.phone}</p>
        <br/>

        <form action="/admin/requests" method="post">
            <input type="hidden" name="user_id" value="${instr.id}"/>
            <input type="hidden" name="result" value="accept"/>
            <input type="submit" value="Accept"/>
        </form>
        <form action="/admin/requests" method="post">
            <input type="hidden" name="user_id" value="${instr.id}"/>
            <input type="hidden" name="result" value="reject"/>
            <input type="submit" value="Reject"/>
        </form>
    </#list>
<#else>
    <p>There are no new requests for you today</p>

    <p><a href="/admin/requests_schedule"> Check requests for changes in schedule</a></p>
</#if>


</div>


</body>