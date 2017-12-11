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
    <title>Новый магазин</title>
</head>
<body>
<form action="editShop" method="post">

    <p><b>Сеть:</b><br>
    <select size="1" name="chainStoreId">
        <c:forEach var="chainStore" items="${chainStores}">

        <c:if test="${shop.getChainStoreId() == chainStore.getId()}">
            <option selected value="${chainStore.getId()}" type="number"> ${chainStore.getName()}</option>
        </c:if>

            <c:if test="${shop.getChainStoreId() != chainStore.getId()}">
                <option  value="${chainStore.getId()}" type="number"> ${chainStore.getName()}</option>
            </c:if>

        </c:forEach>
    </select>

    <input name="id" hidden value="${shop.getId()}" type="number"/>

    <p><b>Адрес:</b><br>
        <input name="address" value="${shop.getAddress()}" type="text"/>
    </p>
<br>
    <button type="reset"> Отмена</button>
    <button type="submit">Сохранить</button>
</form>

<br>
<a href="admin"> <i class="material-icons" style="font-size: 20pt;">home</i></a>
</body>
</html>