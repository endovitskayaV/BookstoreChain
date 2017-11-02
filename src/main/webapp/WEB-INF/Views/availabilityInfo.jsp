<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valya
  Date: 30.10.2017
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<c:if test="${chainStores.isEmpty()}">
    Нет в продаже
</c:if>
<c:if test="${chaiStores.size()>0}">
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
                    ${priceCopies.getPrice()} руб.   ${priceCopy.getCopiesNumber()} шт<br>
                </c:if>
            </c:forEach>

        </c:if>
    </c:forEach>

    <br>
</c:forEach>
</body>
</html>
