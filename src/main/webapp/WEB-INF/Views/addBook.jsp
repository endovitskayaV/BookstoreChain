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
    <title>Новая книга</title>
</head>
<body>
<form action="editBook" method="post">

    <input name="id" hidden value="" type="number"/>

    <p><b>Название:</b><br>
        <input name="name" value="" type="text"/>
    </p>
    <p><b>Автор:</b><br>
        <input name="author" value="" type="text"/>
    </p>
    <p><b>Издатель:</b><br>
        <input name="publisher" value="" type="text"/>
    </p>
    <p><b>Год издания:</b><br>
        <input name="releaseYear" value="" type="number"/>
    </p>
    <p><b>Количество страниц:</b><br>
        <input name="pagesNumber" value="" type="number" min="3"/>
    </p>

    <button type="reset"> Отмена</button>
    <button type="submit">Сохранить</button>
</form>


</body>
</html>