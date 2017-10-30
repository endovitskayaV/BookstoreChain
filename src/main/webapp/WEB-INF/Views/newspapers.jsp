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
    <title>Newspapers</title>
</head>
<body>
<table>
    <c:forEach var="newspaper" items="${newspapers}" >
        <tr>
            <td>
                <a href="newspaperInfo?id=${newspaper.getId()}"> ${newspaper.getName()}</a><br>
                ${newspaper.getIssue()}
                <br><br>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>