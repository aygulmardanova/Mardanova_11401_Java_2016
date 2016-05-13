<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Buy subscription </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/prices.css">

    <style>
        .buy_submit {
            height: 28px;
            width: 90%;
            font-size: 14pt;
            font-family: 'Comic Sans', 'Comic Sans MS', 'Coronetscript', 'Florence', 'Parkavenue', cursive;
            padding: 0;
            border: 1px solid black;
            background-color: white;
            border-radius: 6px;
        }
        .buy_submit:hover {
            border: 2px solid black;
            box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
        }
    </style>
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


<div class="main">
    <div class="prices_table">
        <h3>Prices</h3>
        <table border="1">
            <tr>
                <th>Validity</th>
                <th>Price</th>
                <th></th>
            </tr>
        <#if subscriptions?has_content>
            <#list subscriptions as s>
                <tr>
                    <td>${s.validity} months</td>
                    <td>${s.price}</td>
                    <td>
                        <form action="/user/buy-subscr" method="post">
                            <input type="hidden" name="login" value="${login}">
                            <input type="hidden" name="subscr_id" value="${s.id}">
                            <input type="hidden" name="validity" value="${s.validity}">
                            <input type="submit" value="Buy" class="buy_submit">
                        </form>
                    </td>
                </tr>
            </#list>
        </#if>
        </table>
    </div>



</div>

</body>
</html>