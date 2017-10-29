<%--
  Created by IntelliJ IDEA.
  User: Valya
  Date: 28.10.2017
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<table>
    <c:forEach var="book" items="${books}" >
        <tr>
            <td>
                <%int count=1;%>
                <p>
                <c:forEach var="id_name_auth" items="${book}">

                    <!-- лист в листе. в каждой ячейке большого листа лежит лист
                    в кажлой ячейке малого листа сначала лежит id name auth книги-->
                    <% if(count==1 ) {%>   <c:set var="ahref" value="${id_name_auth}" />
                    <% count=2;} else if(count==2){%> <a href="bookInfo?id=${ahref}"> ${id_name_auth}</a><br>
                    <%count=3;}  else{ %> ${id_name_auth} <%count=1;}%>

            </c:forEach>
                </p>
                <br>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ page language="java" import="java.util.*, java.lang.*" %>

</body>
</html>



<!--<table>
<c:forEach var="book" items="${books}" >
    <tr>
    <td>
    <%int count=1;%>
    <p>
    <c:forEach var="id_name_auth" items="${book}">
        <% if(count==1 ) {%>  <a href="bookInfo?id=1"> ${id_name_auth}</a><br>
        <% count=2;} else {%> ${id_name_auth} <%count=1;}%>
    </c:forEach>
    </p>
    </td>
    </tr>
</c:forEach>
</table>-->