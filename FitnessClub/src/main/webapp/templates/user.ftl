<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

    <#list users as user>
        ${user.login}
        ${user.surname}
    </#list>

</body>
</html>