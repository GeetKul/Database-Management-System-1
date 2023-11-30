<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>List of Orders of Work</title>
</head>
<body>

<div align="center">
     <form action="OrderOfWork" method="post">
      <input type = "submit" value = "Click here"/>
    <table border="1" cellpadding="5">
        <caption><h2>List of Orders of Work</h2></caption>
        <tr>
            <th>OrderID</th>
            <th>RequestID</th>
            <th>ClientID</th>
            <th>StartDate</th>
            <th>EndDate</th>
            <th>Status</th>
            <th>NumberOfTreesCut</th>
            <th>DateOfCut</th>
            <th>ContractorID</th>
        </tr>

        <c:forEach var="order" items="${listOrders}">
            <tr style="text-align:center">
                <td><c:out value="${order.orderID}" /></td>
                <td><c:out value="${order.requestID}" /></td>
                <td><c:out value="${order.clientID}" /></td>
                <td><c:out value="${order.startDate}" /></td>
                <td><c:out value="${order.endDate}" /></td>
                <td><c:out value="${order.status}" /></td>
                <td><c:out value="${order.numberOfTreesCut}" /></td>
                <td><c:out value="${order.dateOfCut}" /></td>
                <td><c:out value="${order.contractorID}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
