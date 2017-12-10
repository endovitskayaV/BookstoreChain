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
    <meta charset="UTF-8"></meta>
    <title>Новый журнал</title>
</head>
<body>
<form action="editMagazine" method="post">

    <input name="id" hidden value="${magazine.getId()}" type="number"/>

    <p><b>Название:</b><br>
        <input name="name" value="${magazine.getName()}" type="text"/>
    </p>
    <p><b>Год издания:</b><br>
        <input name="releaseYear" value="${magazine.getReleaseYear()}" type="number"/>
    </p>
    <p><b>Номер:</b><br>
        <input name="issue" value="${magazine.getIssue()}" type="number"/>
    </p>
    <p><b>Количество страниц:</b><br>
        <input name="pagesNumber" value="${magazine.getPagesNumber()}" type="number" min="3"/>
    </p>


    <a href="addAvailabilityInfo?itemId=${magazine.getId()}&itemName=magazine&backAddr=${backAddr}"
       style="text-decoration: none; font-size: 20pt;">
        <b>+</b></a>

    <c:import url="editableCopiesPrice.jsp"/>

    <p>
        <button type="reset"> Отмена</button>
        <button type="submit">Сохранить</button>
    </p>
</form>


</body>
</html>