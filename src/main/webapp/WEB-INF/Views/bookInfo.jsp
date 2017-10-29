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
    <title>${bookName}</title>
</head>
<body>
<h2>${bookName}</h2><br>
${author}<br>
${publisher}<br>
${pagesNumber} стр<br>
${releaseYear}<br>
<br><br>

Доступно:<br>

<c:forEach var="shopAdd" items="${shopsAddresses}">

    <%int count=0;%>
    <c:forEach var="i" items="${shopAdd}" >

        <!-- лист в листе. в каждой ячейке большого листа лежит лист
        в кажлой ячейке малого листа сначала лежит имя сети, а потом несколько раз адреса магазинов, цена, число копий-->
        <%if (count==0) {%> <h2>${i}</h2><br><% count=1;}
        else if (count==1){ %> ${i} <% count=2;}
        else if (count==2){ %> ${i}р. <% count=3;}
                     else {%>  ${i} копий <br><%count=1;}%>

    </c:forEach>

</c:forEach>
</body>
</html>