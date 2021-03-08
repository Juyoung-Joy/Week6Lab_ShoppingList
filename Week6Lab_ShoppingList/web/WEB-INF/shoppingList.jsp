<%-- 
    Document   : shoppingList
    Created on : Mar 7, 2021, 5:49:24 PM
    Author     : 824664
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>
            Hello, ${username} 
            <a href="ShoppingList?action=logout">Logout</a>
        </p>
        <h2>List</h2>
        <form action="ShoppingList" method="post">
            Add item: 
            <input type="text" name="addItem" value="">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
        </form>
        <br>
        <form action="ShoppingList" method="post">
            <c:forEach items="${items}" var="item">
                <input type="radio" name="item" value="${item}">${item} <br>
            </c:forEach>            
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete" >
        </form>
    </body>
</html>
