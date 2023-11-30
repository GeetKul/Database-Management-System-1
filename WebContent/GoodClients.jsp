<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>List of Good Clients</title>
</head>
<body>

<div align="center">
    <form action="getGoodClients" method="post">
        <input type="submit" value="Click here" />
    </form>
    <a href="contractor.jsp" target="_self">Logout</a><br><br>
    <h2>List of Good Clients</h2>

    <table border="1" cellpadding="5">
        <tr>
            <th>ClientID</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>GeneratedDate</th>
            <th>BillStatus</th>
            <th>PaymentStatus</th>
        </tr>

        <c:forEach var="client" items="${goodClients}">
            <tr style="text-align:center">
                <td><c:out value="${client.clientID}" /></td>
                <td><c:out value="${client.firstName}" /></td>
                <td><c:out value="${client.lastName}" /></td>
                <td><c:out value="${client.generatedDate}" /></td>
                <td><c:out value="${client.billStatus}" /></td>
                <td><c:out value="${client.paymentStatus}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
