<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Clients with Completed Tree Cutting</title>
</head>
<body>

<div align="center">
    <form action="EasyClients" method="post">
        <input type="submit" value="Click here" />
    </form>
    <a href="contractor.jsp" target="_self">logout</a><br><br>

    <table border="1" cellpadding="5">
        <caption><h2>List the clients who simply accept David initial quotes</h2></caption>
        <tr>
            <th>ClientID</th>
            <th>FirstName</th>
            <th>LastName</th>
        </tr>

        <c:forEach var="client" items="${easyClients}">
            <tr style="text-align:center">
                <td><c:out value="${client.clientID}" /></td>
                <td><c:out value="${client.firstName}" /></td>
                <td><c:out value="${client.lastName}" /></td>
            </tr>
        </c:forEach>

    </table>

</div>

</body>
</html>
