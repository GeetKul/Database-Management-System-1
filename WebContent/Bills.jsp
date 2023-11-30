<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>List of Bills</title>
</head>
<body>

<div align="center">
    <form action="Bills" method="post">
        <input type="submit" value="Click here" />
    </form>
    <a href="contractor.jsp" target="_self">Logout</a><br><br>

    <table border="1" cellpadding="5">
        <caption><h2>List of Bills</h2></caption>
        <tr>
            <th>BillID</th>
            <th>Amount</th>
            <th>GeneratedDate</th>
            <th>BillStatus</th>
            <th>PaymentStatus</th>
            <th>OrderID</th>
            <th>ContractorID</th>
        </tr>

        <c:forEach var="bill" items="${listBills}">
            <tr style="text-align:center">
                <td><c:out value="${bill.billId}" /></td>
                <td><c:out value="${bill.amount}" /></td>
                <td><c:out value="${bill.generatedDate}" /></td>
                <td><c:out value="${bill.billStatus}" /></td>
                <td><c:out value="${bill.paymentStatus}" /></td>
                <td><c:out value="${bill.orderID}" /></td>
                <td><c:out value="${bill.contractorID}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
