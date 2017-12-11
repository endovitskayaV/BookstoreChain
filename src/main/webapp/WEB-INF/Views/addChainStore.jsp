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
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<head>
    <title>Новая сеть</title>
</head>
<body>
<form action="addChainStore" method="post">

    <input name="id" hidden value="${chainStore.getId()}" type="number"/>

    <p><b>Название:</b><br>
        <input name="name" required value="${chainStore.getName()}" type="text"/>
    </p>


    <button type="reset"> Отмена</button>
    <button type="submit">Сохранить</button>
</form>

<br>
<a href="admin"> <i class="material-icons" style="font-size: 20pt;">home</i></a>
</body>
</html>