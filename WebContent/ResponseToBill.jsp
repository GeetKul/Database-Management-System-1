<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>List of ResponseToBills</title>
</head>
<body>

<div align="center">
    <form action="ResponseToBill" method="post">
        <input type="submit" value="Click here" />
    </form>
    <a href="contractor.jsp" target="_self">Logout</a><br><br>

    <table border="1" cellpadding="5">
        <caption><h2>List of ResponseToBills</h2></caption>
        <tr>
            <th>ResponseToBillID</th>
            <th>BillID</th>
            <th>ClientID</th>
            <th>ResponseDate</th>
            <th>Note</th>
            <th>PaymentStatus</th>
        </tr>

        <c:forEach var="responseToBill" items="${listResponseToBills}">
            <tr style="text-align:center">
                <td><c:out value="${responseToBill.responseToBillID}" /></td>
                <td><c:out value="${responseToBill.billID}" /></td>
                <td><c:out value="${responseToBill.clientID}" /></td>
                <td><c:out value="${responseToBill.responseDate}" /></td>
                <td><c:out value="${responseToBill.note}" /></td>
                <td><c:out value="${responseToBill.paymentStatus}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
