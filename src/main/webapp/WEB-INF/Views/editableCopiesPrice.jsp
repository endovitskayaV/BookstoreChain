<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valya
  Date: 10.12.2017
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<c:if test="${chainStores.isEmpty()}">
    Нет в продаже
</c:if>
<c:if test="${!chainStores.isEmpty()}">
    Доступно:
</c:if>
<br><br>
<c:forEach var="chainStore" items="${chainStores}">

    <b>${chainStore.getName()}</b><br>

    <c:forEach var="shop" items="${shops}">
        <c:if test="${shop.getChainStoreId() == chainStore.getId()}">
            ${shop.getAddress()}

            <c:forEach var="priceCopies" items="${priceCopiesList}">
                <c:if test="${priceCopies.getShopId() == shop.getId()}" >

                    <input name="concreteProductInShopArr[${i}].itemName" hidden value="${itemName}" type="text"/>

                    <input name="concreteProductInShopArr[${i}].shopId" hidden value="${shop.getId()}" type="number"/>
                    <input name="concreteProductInShopArr[${i}].itemId" hidden value="${itemId}" type="number"/>


                    <input name="concreteProductInShopArr[${i}].price" value="${priceCopies.getPrice()}" type="number" style="width: 50px;"/>руб.
                    <input name="concreteProductInShopArr[${i}].copiesNumber" value="${priceCopies.getCopiesNumber()}"  type="number" style="width: 40px;"/>шт.
                    <br>

                    <c:set var="i">${i+1}</c:set>

                </c:if>
            </c:forEach>

        </c:if>
    </c:forEach>

</c:forEach>
<br>
</body>
</html>
