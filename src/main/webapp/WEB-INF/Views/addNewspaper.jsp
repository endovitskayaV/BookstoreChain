<%--
  Created by IntelliJ IDEA.
  User: Valya
  Date: 09.12.2017
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новая газета</title>
</head>
<body>
<form action="addNewspaper" method="post">

    <input name="id" hidden value="${newspaper.getId()}" type="number"/>

    <p><b>Название:</b><br>
        <input name="name" value="${newspaper.getName()}" type="text"/>
    </p>
    <p><b>Год издания:</b><br>
        <input name="releaseYear" min="1" max="${maxYear}" value="${newspaper.getReleaseYear()}" type="number"/>
    </p>
    <p><b>Количество страниц:</b><br>
        <input name="issue" min="3" value="${newspaper.getIssue()}" type="number"/>
    </p>


    <button type="reset"> Отмена</button>
    <button type="submit">Сохранить</button>
</form>


</body>
</html>