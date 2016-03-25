<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница поиска</title>
</head>
<body>

  <form action="http://${action}" method="get" name="search">
    <h2>Поиск в ${system}:</h2>
    <input type="text" name="${name}" placeholder="Поиск"/>
    <input type="submit" value="Найти ${system}"/>

  </form>

</body>
</html>
