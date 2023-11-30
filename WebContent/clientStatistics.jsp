<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Statistics of Clients</title>
</head>
<body>

<div align="center">
    <form action="getClientsStatistics" method="post">
        <input type="submit" value="Click here" />
    </form>
    <a href="contractor.jsp" target="_self">Logout</a><br><br>
    <h2>Clients Statistics</h2>

    <table border="1" cellpadding="5">
        <tr>
            <th>ClientID</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>TotalTreesCut</th>
            <th>TotalDueAmount</th>
            <th>TotalPaidAmount</th>
            <th>DateOfWorkDone</th>
        </tr>

        <c:forEach var="client" items="${clientStatisticsList}">
            <tr style="text-align:center">
                <td><c:out value="${client.clientID}" /></td>
                <td><c:out value="${client.firstName}" /></td>
                <td><c:out value="${client.lastName}" /></td>
                <td><c:out value="${client.totalTreesCut}" /></td>
                <td><c:out value="${client.totalDueAmount}" /></td>                        
                <td><c:out value="${client.totalPaidAmount}" /></td>
                <td><c:out value="${client.dateOfWorkDone}" /></td>
            </tr>
        </c:forEach>
        
    </table>
</div>

</body>
</html>
