<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Instructor settings page </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/profile.css">
    <link rel="stylesheet" type="text/css" href="../css/settings.css">
    <link rel="stylesheet" type="text/css" href="../css/settings_instr.css">

    <script type="text/javascript" src="../js/libs/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../js/validation.js"></script>


    <script>



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

        <a href="/user/profile">Hello, ${login}</a>
        <a href="/logout">Log out</a>

    </div>
</div>

<@sec.authorize ifAnyGranted="ROLE_INSTRUCTOR">
<div class="admin_p">
    <p>You are an instructor</p>
</div>
</@sec.authorize>

<#if message??>
<h3>${message}</h3>
</#if>

<div class="settings_main">

    <div class="settings_inline">
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
            <a href="/user/profile" class="settings_a">Profile</a>
        </div>
        <div class="profile_hrefs">
            <a href="/user/settings" class="settings_a">Main settings</a>
        </div>
    </div>

    <div class="instr_settings">
        <form action="/user/instr-settings" method="post">

            <div class="settings_inline">
                <div class="label_div">
                    <label for="description">Description</label>
                </div>
            <textarea name="description" id="description" class="description_text"
            <#if instructor.description??>
                      placeholder="${instructor.description}"
            <#else> placeholder="Describe yourself as a trainer"
            </#if>
            ></textarea>
            </div>

            <div class="settings_inline">
                <div class="label_div"><label for="awards">Awards</label></div>
            <textarea name="awards" id="awards" class="awards_text"
            <#if instructor.awards??>
                      placeholder="${instructor.awards}"
            <#else> placeholder="Write about your awards here"
            </#if>
            ></textarea>
            </div>

            <div class="settings_inline">
                <div class="label_div"><label for="qualification">Qualification</label></div>
            <textarea name="qualification" id="qualification" class="qualification_text"
            <#if instructor.qualification??>
                      placeholder="${instructor.qualification}"
            <#else> placeholder="Write about your qualification here"
            </#if>
            ></textarea>
            </div>

            <div class="settings_inline">
                <div class="label_div"><label for="experience">Experience since</label></div>
                <input type="text" name="experience" id="date" oninput="onchangeDate()" style="padding-left: 6px"
                       class="experience_input"
                <#if instructor.experience??>
                       placeholder="${instructor.experience}"
                <#else> placeholder="Your experience in 'dd.mm.yyyy' format"
                </#if>
                >
                <p class="wrong_date_msg" id="date_valid"></p>
            </div>

            <input type="hidden" name="user_id" value="${user.id}">
            <input type="submit" value="Change data" class="instr_settings_submit">
        </form>
    </div>

</div>

</body>