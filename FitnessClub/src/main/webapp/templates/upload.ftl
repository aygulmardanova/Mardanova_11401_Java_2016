<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Uploading photo </title>

</head>

<body>

<#if photo??>
<p> You added photo with name - ${photo}</p>
<#else> Upload a photo, please
</#if>

<form method="POST" action="/upload/photo" enctype="multipart/form-data">
    File to upload: <input type="file" name="file"><br/>

    <input type="submit" value="Upload">
    Press here to upload the file!
</form>

<#if photosList??>
    <#list photosList as photo>
    <p>${photo.upload}</p>

    <p><img src="images/upload/${photo.upload}" alt="Can not download photo"></p>
    </#list>
</#if>
</body>

</html>
