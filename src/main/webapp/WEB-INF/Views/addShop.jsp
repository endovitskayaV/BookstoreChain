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
    <title>Новый магазин</title>
</head>
<body>
<form action="addShop" method="post">

    <input name="id" hidden value="${shop.getId()}" type="number"/>

    <p><b>Адрес:</b><br>
        <input name="address" value="${shop.getAddress()}" type="text"/>
    </p>


    <select size="1" name="chainStoreId">
        <c:forEach var="chainStore" items="${chainStores}">
            <option value="${chainStore.getId()}" type="number"> ${chainStore.getName()}</option>
        </c:forEach>
    </select>

    <button type="reset"> Отмена</button>
    <button type="submit">Сохранить</button>
</form>


</body>
</html>