<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valya
  Date: 28.10.2017
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${book.getName()}</title>
</head>
<body>
<h2>${book.getName()}</h2><br>
${book.getAuthor()}<br>
${book.getPublisher()}<br>
${book.getReleaseYear()}<br>
${book.getPagesNumber()} стр. <br>
<br><br>

Доступно:<br><br>
<c:forEach var="chainStore" items="${chainStores}">

    <b>${chainStore.getName()}</b><br>

    <c:forEach var="shop" items="${shops}">
        <c:if test="${shop.getChainStoreId() == chainStore.getId()}" >
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