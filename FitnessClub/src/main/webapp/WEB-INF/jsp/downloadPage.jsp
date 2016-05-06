<%--
  Created by IntelliJ IDEA.
  User: aygulmardanova
  Date: 06.05.16
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Download Page PDF</title>
</head>
<body>

<h2>Spring MVC</h2>

<h3>Revenue Report</h3>

<table border="1px" cellpadding="8px">
    <tr>
        <td>Month</td><td>Revenue</td>
    </tr>

    <c:forEach items="${scheduleData}" var="current">
        <tr>
            <td><c:out value="${current.key}" /></td>
            <td><c:out value="${current.value}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
