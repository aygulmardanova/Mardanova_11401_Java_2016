<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Main page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/slider.css">

    <script type="text/javascript" src="../js/libs/jquery.js"></script>
    <script type="text/javascript" src="../js/scriptcode.js"></script>
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
        <a href="/login" class="button">Log in</a>
        <a href="/signup" class="button">Sign up</a>
    </div>
</div>

<img style="width: 1350px; height: 300px" src="../images/smile.jpg" alt="The image can not be found">

<div class="main">
    <div class="left_part">
        <h1 style="margin-left: 100px"> JOIN US! </h1>

        <h2 style="margin-left: 10px"> Subscriptions from ${min_price} rubles </h2>

        <h3 class="learnmore"><a href="/"> Learn more </a></h3>
    </div>

    <div class="wrapper">
        <div class="slider">
            <ul>
                <li><img src="../images/i/hall1.jpg" alt="Empty"/></li>
                <li><img src="../images/i/group.jpg" alt="Empty"/></li>
                <li><img src="../images/i/girl2.jpg" alt="Empty"/></li>
                <li><img src="../images/i/dance.jpg" alt="Empty"/></li>
                <li><img src="../images/i/hard.jpg" alt="Empty"/></li>
                <li><img src="../images/i/header01.jpg" alt="Empty"/></li>
            </ul>
        </div>
    </div>

    <div>
        <#list roles as r>
            <ul>${r.getId} - ${r.getName}</ul>
        </#list>
    </div>



<#--<noscript>
    <div><img src="//mc.yandex.ru/watch/20963398" style="position:absolute; left:-9999px;" alt=""></div>
</noscript>-->

</div>


</body>