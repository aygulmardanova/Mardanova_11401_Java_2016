<%--
  Created by IntelliJ IDEA.
  User: Айгуль
  Date: 20.03.2016
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

  <form action='/process' method='POST'>
    <p><h2>Введите текст:</h2></p>
    <p><textarea rows="10" cols="45" name='text' placeholder="Ваш текст"></textarea></p>
    <b>Count of: </b>
    <select name='option'>
      <option>Symbols</option>
      <option>Words</option>
      <option>Sentences</option>
      <option>Paragraphs</option>
    </select>
    <br />
    <br />
    <input type='submit' value='Process' />
  </form>

</body>
</html>
