<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Valya
  Date: 28.10.2017
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
rel="stylesheet">

<head>
    <title>Admin</title>
</head>
<body>

Книги<br>
<a href="addBook"  style="text-decoration: none; font-size: 20pt;"> <b>+</b></a>
<table>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>
                <a href="editBook?id=${book.getId()}">  ${book.getName()}</a><br>
                <c:if test="${!book.getIsAvailable()}">
                    Нет в продаже
                </c:if>
                <c:if test="${book.getIsAvailable()}">
                    Доступно
                </c:if>
            </td>
            <td> <a href="deleteBook?id=${book.getId()}" onclick = "return confirm('Удалить?');" >
                <i class="material-icons"  style="font-size: 10pt;">delete</i></a></td>
        </tr>
    </c:forEach>
</table>
<br><br>

Газеты<br>
<a href="addNewspaper"  style="text-decoration: none; font-size: 20pt;"> <b>+</b></a>
<table>
    <c:forEach var="newspaper" items="${newspapers}">
        <tr>
            <td>
                <a href="editNewspaper?id=${newspaper.getId()}">  ${newspaper.getName()}</a> <br>№${newspaper.getIssue()}<br>
                <c:if test="${!newspaper.getIsAvailable()}">
                    Нет в продаже
                </c:if>
                <c:if test="${newspaper.getIsAvailable()}">
                    Доступно
                </c:if>
            </td>
            <td> <a href="deleteNewspaper?id=${newspaper.getId()}" onclick = "return confirm('Удалить?');" >
                <i class="material-icons"  style="font-size: 10pt;">delete</i></a></td>
        </tr>
    </c:forEach>
</table>
<br><br>


Журналы<br>
<a href="addMagazine"  style="text-decoration: none; font-size: 20pt;"> <b>+</b></a>
<table border="0">
    <c:forEach var="magazine" items="${magazines}">
        <tr>
            <td>
                <a href="editMagazine?id=${magazine.getId()}">  ${magazine.getName()}</a> <br> №${magazine.getIssue()}<br>
                <c:if test="${!magazine.getIsAvailable()}">
                    Нет в продаже
                </c:if>
                <c:if test="${magazine.getIsAvailable()}">
                    Доступно
                </c:if>
            </td>
            <td> <a href="deleteMagazine?id=${magazine.getId()}" onclick = "return confirm('Удалить?');" >
                <i class="material-icons"  style="font-size: 10pt;">delete</i></a></td>
        </tr>
    </c:forEach>
</table>
<br> <br>

Магазины<br>
<a href="addShop" style="text-decoration: none; font-size: 20pt;"><b>+</b></a>
<table>
<c:forEach var="chainStore" items="${chainStores}">
    <c:forEach var="shop" items="${shops}">
        <c:if test="${shop.getChainStoreId() == chainStore.getId()}">
        <tr>
            <td>
                <a href="editShop?id=${shop.getId()}">  ${chainStore.getName()}</a><br>
                    ${shop.getAddress()}
            </td>
            <td> <a href="deleteShop?id=${shop.getId()}" onclick = "return confirm('Удалить?');" >
                <i class="material-icons"  style="font-size: 10pt;">delete</i></a></td>
        </tr>
        </c:if>
    </c:forEach>
</c:forEach>
</table>
<br><br>
Сети магизинов<br>
<a href="addChainStore" style="text-decoration: none; font-size: 20pt;"> <b>+</b></a>
<table>
    <c:forEach var="chainStore" items="${chainStores}">

        <tr>
            <td>
                <p><a href="editChainStore?id=${chainStore.getId()}">  ${chainStore.getName()}</a><br></p>
            </td>
            <td> <a href="deleteChainStore?id=${chainStore.getId()}" onclick = "return confirm('Удалить?');" >
                <i class="material-icons"  style="font-size: 10pt;">delete</i></a>
            </td>
        </tr>

    </c:forEach>
</table>

</body>
</html>
