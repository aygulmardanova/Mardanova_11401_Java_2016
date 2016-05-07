<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Our sorted trainers </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/trainers.css"/>

    <script type="text/javascript" src="../js/libs/jquery-1.7.1.min.js"></script>

    <script type="application/javascript">
        f = function (request, response) {
            $.ajax({
                type: 'POST',
                url: "/ajax/sort",
                data: {"sort": $("#categorySort").val()},
                dataType: "json",
                success: function (response_data) {
                    if (response_data.users.length != 0) {
                        $("#res").html("Sort results: ");
                        for (var i = 0; i < response_data.users.length; i++) {
                            $("#res").append("<li>" + response_data.users[i] + "</li>");
                        }
                    } else {
                        $("#res").html("No results");
                    }
                }
            })
        }
    </script>

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

<div class="trainers_main">

    <h1>Our trainers, sorted by Ajax</h1>

    <label for="categorySort" class="sort_label">Sort by:</label>
    <select id="categorySort" onchange="f()" class="sort_select">
        <option value="name,asc">by name asc</option>
        <option value="name,desc">by name desc</option>
        <option value="surn,asc">by surname asc</option>
        <option value="surn,desc">by surname desc</option>
    </select>
    <hr size="1px"/>

    <div id="res">
    </div>

</div>

</body>
