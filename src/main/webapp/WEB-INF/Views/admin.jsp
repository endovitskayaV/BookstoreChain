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
<head>
    <title>Admin</title>
</head>
<body>

Книги<br>
<a href="addBook"> <b>+</b></a>
<table border="1">
    <tr><td>id</td><td>Название</td></tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>
                <a href="editBook?id=${book.getId()}">  ${book.getName()}</a><br>
                    ${book.getAuthor()}
            </td>
            <td> <a href="deleteBook?id=${book.getId()}" onclick = "return confirm('Удалить?');" >Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<br><br>

Газеты<br>
<a href="addBook"> <b>+</b></a>
<table border="1">
    <tr><td>id</td><td>Название</td></tr>
    <c:forEach var="newspaper" items="${newspapers}" >
        <tr>
            <td>${newspaper.getId()}</td>
            <td>
                <a href="bookInfo?id=${newspaper.getId()}"> ${newspaper.getName()}</a><br>
                    ${newspaper.getIssue()}
            </td>
            <td><a href=""> edit</a></td>
            <td> <a href=""> delete</a></td>
        </tr>
    </c:forEach>
</table>
<br> <br>
Журналы<br>
<a href="addBook"> <b>+</b></a>
<table border="1">
    <tr><td>id</td><td>Название</td></tr>
    <c:forEach var="magazine" items="${magazines}" >
        <tr>
            <td>${magazine.getId()}</td>
            <td>
                <a href="bookInfo?id=${magazine.getId()}"> ${magazine.getName()}</a><br>
                    ${magazine.getIssue()}
            </td>
            <td><a href=""> edit</a></td>
            <td> <a href=""> delete</a></td>
        </tr>
    </c:forEach>
</table>
<br> <br>
Магазины<br>
<a href="addRow">add</a>
<table border="1">
    <tr><td>id</td><td>Название</td><td>Адрес</td></tr>
    <c:forEach var="shop" items="${shops}" >
        <tr>
            <td>${shop.getId()}</td>
            <td><a href="bookInfo?id=${shop.getId()}"> ${shop.getName()}</a></td>
            <td>${shop.getAddress()}</td>
            <td><a href=""> edit</a></td>
            <td> <a href=""> delete</a></td>
        </tr>
    </c:forEach>
</table>
<br><br>
Сети магизинов<br>
<a href="addBook"> <b>+</b></a>
<table border="1">
    <tr><td>id</td><td>Название</td></tr>
    <c:forEach var="chainStore" items="${chainStores}" >
        <tr>
            <td>
                <a href="bookInfo?id=${chainStore.getId()}"> ${chainStore.getName()}</a>
            </td>
            <td> <a href=""> delete</a></td>
        </tr>
    </c:forEach>
</table>
Книги в магазинах<br>
<a href="addRow">add</a>
<table border="1">
    <tr><td>id книги</td> <td>id магазина</td> <td>цена</td> <td>шт</td></tr>
    <c:forEach var="concreteBookShop" items="${concreteBooksShops}">
        <tr>
            <td>${concreteBookShop.getBookId()}</td>
            <td>${concreteBookShop.getShopId()}</td>
            <td>${concreteBookShop.getPrice()}</td>
            <td>${concreteBookShop.getCopiesNumber()}</td>
            <td><a href=""> edit</a></td>
            <td> <a href=""> delete</a></td>
        </tr>
    </c:forEach>
</table>
Журналы в магазинах<br>
<a href="addRow"> add</a>
<table border="1">
    <tr><td>id журнала</td> <td>id магазина</td> <td>цена</td> <td>шт</td></tr>
    <c:forEach var="concreteMagazineShop" items="${concreteMagazinesShops}">
        <tr>
            <td>${concreteMagazineShop.getMagazineId()}</td>
            <td>${concreteMagazineShop.getShopId()}</td>
            <td>${concreteMagazineShop.getPrice()}</td>
            <td>${concreteMagazineShop.getCopiesNumber()}</td>
            <td><a href=""> edit</a></td>
            <td> <a href=""> delete</a></td>
        </tr>
    </c:forEach>
</table>
Газеты в магазинах<br>
<a href="addRow"> add</a>
<table border="1">
    <tr><td>id газеты</td> <td>id магазина</td> <td>цена</td> <td>шт</td></tr>
    <c:forEach var="concreteNewspaperShop" items="${concreteNewspapersShops}" >
        <tr>
            <td>${concreteNewspaperShop.getNewspaperId()}</td>
            <td>${concreteNewspaperShop.getShopId()}</td>
            <td>${concreteNewspaperShop.getPrice()}</td>
            <td>${concreteNewspaperShop.getCopiesNumber()}</td>
            <td><a href=""> edit</a></td>
            <td> <a href=""> delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
