<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Classes </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <link rel="stylesheet" type="text/css" href="../css/classes.css"/>
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

<@sec.authorize ifAnyGranted="ROLE_ADMIN">
<div class="admin_p" style="margin-top: 0">
    <a href="/classes" class="add_class_href">Go to all classes</a>
</div>
</@sec.authorize>

<div class="classes_main">

    <h1>New class adding</h1>

<#if message??>
    <h2>${message}</h2>
</#if>
    <form action="/admin/add-class" method="post" enctype="multipart/form-data">


        <div class="classes_inline">
            <div class="label_div"><label for="name">Name</label></div>
            <input type="text" name="name" id="name" style="padding-left: 6px"
                   class="name_input" placeholder="New class name">
        </div>

        <div class="classes_inline">
            <div class="label_div">
                <label for="name">Description</label>
            </div>
            <textarea name="description" id="description" class="description_text"
                      placeholder="Describe new class">
            </textarea>
        </div>

        <div class="classes_inline">
            <div class="classes_photo">
                <div class="label_div">
                    <label for="photo">Choose photo</label>
                </div>
                <input type="file" name="photo" value="New photo" id="photo"><br/>
            </div>
        </div>

        <input type="submit" value="Add class" class="add_class_submit">
    </form>
</div>


</body>
