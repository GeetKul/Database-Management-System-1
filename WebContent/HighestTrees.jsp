<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Highest Trees Cut by Contractor</title>
</head>
<body>

<div align="center">
<form action="getHighestTreesCutByContractor" method="post">
        <input type="submit" value="Click here" />
    </form>
    <a href="contractor.jsp" target="_self">Logout</a><br><br>
    <table border="1" cellpadding="5">
        <caption><h2>Highest Trees Cut by Contractor</h2></caption>
        <tr>
            <th>RequestID</th>
            <th>ClientID</th>
            <th>RequestDate</th>
            <th>NumberOfTrees</th>
            <th>Size</th>
            <th>Height</th>
        </tr>

        <c:forEach var="tree" items="${highestTrees}">
            <tr style="text-align:center">         
                <td><c:out value="${tree.requestID}" /></td>
                <td><c:out value="${tree.clientID}" /></td>
                <td><c:out value="${tree.requestDate}" /></td>
                <td><c:out value="${tree.numberOfTrees}" /></td>
                <td><c:out value="${tree.size}" /></td>
                <td><c:out value="${tree.height}" /></td>
            </tr>
        </c:forEach>

    </table>
</div>

</body>
</html>