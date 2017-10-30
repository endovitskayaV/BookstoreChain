<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valya
  Date: 29.10.2017
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Magazines</title>
</head>
<body>
<table>
    <c:forEach var="magazine" items="${magazines}" >
        <tr>
            <td>
                <a href="magazineInfo?id=${magazine.getId()}"> ${magazine.getName()}</a><br>
                    ${magazine.getIssue()}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
