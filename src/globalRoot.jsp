<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>List of Bills</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        $(document).ready(function () {
            $("button").on("click", function (e) {
                e.preventDefault(); // Prevent the default behavior of the anchor tag
                var url = $(this).find("a").attr("href");
                $("#content").load(url); // Load the content of the linked page into the element with id "content"
            });
        });
    </script>
<style>
	body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      text-align: center;
      margin: 20px;
    }

    button {
      background-color: #4CAF50;
      color: white;
      padding: 10px 20px;
      margin: 5px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
    }

    button a {
      text-decoration: none;
      color: inherit;
    }

    button:hover {
      background-color: #45a049;
    }
    
     #content {
            margin-top: 20px;
        }
</style>
<body>
	<button><a href="BadClients.jsp">Bad Clients</a></button>
	<button><a href="BigClients.jsp">Big Clients</a></button>
	<button><a href="clientStatistics.jsp">Client Statistics</a></button>
	<button><a href="EasyClients.jsp">Easy Clients</a></button>
	<button><a href="GoodClients.jsp">Good Clients</a></button>
	<button><a href="HighestTrees.jsp">Highest Tree</a></button>
	<button><a href="OverdueBills.jsp">Overdue Bills</a></button>
	<button><a href="OneTreeQuotes.jsp">One Tree Quotes</a></button>
	<button><a href="ProspectiveClients.jsp">Prospective Clients</a></button>
	
	<div id="content"></div>
</body>
</html>