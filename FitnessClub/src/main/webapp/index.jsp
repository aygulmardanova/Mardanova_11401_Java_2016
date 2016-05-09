<%--
  Created by IntelliJ IDEA.
  User: aygulmardanova
  Date: 07.05.16
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome page</title>
    <style>
        body {
            margin-top: 50px;
        }

        h1 {
            font-family: 'Comic Sans', 'Comic Sans MS', 'Coronetscript', 'Florence', 'Parkavenue', cursive;
            text-align: center;
        }

        a {
            color: black;
            text-decoration: underline;
            font-family: 'Comic Sans', 'Comic Sans MS', 'Coronetscript', 'Florence', 'Parkavenue', cursive;
            font-size: 16pt;
            margin-left: 150px;
            margin-top: 30px;
            margin-bottom: 30px;
            font-style: italic;
        }

        a:hover {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Fitness Club</h1>
    <a href="/main"> Go to main page</a> <br/><br/><br/>
    <a href="/login"> Go to login page</a> <br/><br/><br/>
    <a href="/signup"> Go to registration page</a> <br/><br/><br/>
    <a href="/schedule"> Go to schedule</a> <br/><br/><br/>
    <a href="/about-us"> Go to about club page</a> <br/><br/><br/>
</body>
</html>
