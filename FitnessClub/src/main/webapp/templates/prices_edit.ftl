<#ftl encoding='UTF-8'>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Edit prices </title>

    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/prices.css">
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

<div class="admin_p">
    <a href="/prices">Go to prices page</a>
</div>

<div class="main">
    <div class="prices_table">
        <h3>Prices</h3>
        <table border="1">
            <tr>
                <th>Validity</th>
                <th>Price</th>
            </tr>
        <#if subscriptions?has_content>
            <#list subscriptions as s>
                <form action="/admin/edit-prices" method="post">
                    <tr>
                        <td class="edit_td">${s.validity} months</td>
                        <td class="edit_td"><input type="text" name="price"
                                                   placeholder="${s.price}"></td>
                        <td class="buttons_td">
                            <input type="hidden" name="id" value="${s.id}"/>
                            <input type="submit" value="Save" class="save_submit">
                            <div class="delete_div">
                                <a href="/admin/delete-subscr/${s.id}" class="delete_href">Delete</a>
                            </div>
                        </td>
                    </tr>
                </form>
            </#list>
        </#if>

            <tr>
                <form action="/admin/add-subscr" method="post">
                    <td class="edit_td"><input type="text" name="validity" placeholder="Enter new validity"
                                               class="new_input"></td>
                    <td class="edit_td"><input type="text" name="price" placeholder="Enter new price" class="new_input">
                    </td>
                    <td class="buttons_td">
                        <input type="submit" value="Add subscription" class="add_submit">
                    </td>
                </form>
            </tr>

        </table>
    </div>

<@sec.authorize ifAnyGranted="ROLE_ADMIN">

</@sec.authorize>


</div>

</body>
</html>