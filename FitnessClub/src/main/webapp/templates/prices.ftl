<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Our prices </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/prices.css">
</head>

<body>


<div class="header">
    <img style="float: left" src="../images/dyn.jpg" alt="logo" width="180" height="180">

    <div class="title">
        <b> Fitness Club </b> <br/>
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
        <div class="info"> 8-917-123-456 <br> Kazan <br> <br></div>

    <@sec.authorize ifAnyGranted="ROLE_ANONYMOUS">

        <a href="/login">Log in</a>
        <a href="/signup">Sign up</a>

    </@sec.authorize>
    <@sec.authorize access="isAuthenticated()">

        <a href="/profile">Hello, ${login}</a>
        <a href="/logout">Log out</a>

    </@sec.authorize>

    </div>
</div>

<div class="main">
    <div class="prices_table">
        <h3>Prices</h3>
        <table border="1">
            <!--<caption>
                Prices
            </caption>-->
            <tr>
                <th>Validity</th>
                <th>Price</th>
            </tr>
            <#if subscriptions?has_content>
                <#list subscriptions as s>
                    <tr>
                        <th>${s.validity}</th>
                        <th>${s.price}</th>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>

</div>


</body>
</html>