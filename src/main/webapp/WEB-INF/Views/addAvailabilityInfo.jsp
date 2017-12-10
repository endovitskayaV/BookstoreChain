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
    <title></title>
</head>
<body>
<c:if test="${chainStores.isEmpty()}">
    Доступно во всех магазинах
</c:if>


<form action="addAvailabilityInfo" method="post">

    <input name="backAddr" hidden value="${backAddr}" type="text"/>

    <input name="itemName" hidden value="${concreteProductInShop.getItemName()}" type="text"/>
    <input name="itemId" hidden value="${concreteProductInShop.getItemId()}" type="number"/>


    <c:if test="${!chainStores.isEmpty()}">

    <select size="1" name="shopId">
        <c:forEach var="chainStore" items="${chainStores}">
            <optgroup label="${chainStore.getName()}">

                <c:forEach var="shop" items="${shops}">

                    <c:if test="${shop.getChainStoreId() == chainStore.getId()}">
                        <option value="${shop.getId()}" type="number"> ${shop.getAddress()}
                        </option>
                    </c:if>
            </c:forEach>

            </optgroup>
        </c:forEach>
    </select>



    <p><b>Количество экземпляров:</b><br>
        <input name="copiesNumber" value="${concreteProductInShop.getCopiesNumber()}" type="number"/>
    </p>
    <p><b>Цена:</b><br>
        <input name="price" value="${concreteProductInShop.getPrice()}" type="number"/>
    </p>

    <button type="reset"> Отмена</button>
    <button type="submit">Сохранить</button>
    </c:if>

</form>


</body>
</html>