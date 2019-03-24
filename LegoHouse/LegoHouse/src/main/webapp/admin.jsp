<%@page import="Logic.Order"%>
<%@page import="Logic.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% HttpSession session1 = request.getSession();
            ArrayList<User> userList = (ArrayList<User>) session1.getAttribute("userList");
            ArrayList<Order> orderList = (ArrayList<Order>) session1.getAttribute("orderList");
        %>
        <title>JSP Page</title>
    </head>
    <body>
        <a href="FrontController?origin=index"><img src="IMG/lego.jpg" alt=""/></a>

        <h2>Enter orderID to ship order</h2>
        <form action="FrontController?origin=shipOrder" method="post">
            OrderID:<br>
            <input type="text" name="shipOrder"><br><br>
            <input type="submit" value="Ship order"><br><br>
        </form>
        <h2>Enter orderID to view bricks for order</h2>
        <form action="FrontController?origin=viewBricks" method="post">
            OrderID:<br>
            <input type="text" name="viewBricksID"><br><br>
            <input type="submit" value="View bricks"><br><br>
        </form>

        <h2>List of all orders</h2>
        <table>
            <% for (Object orders : orderList) { %>
            <tr><% out.print(orders); %></tr><br><br>
            <%}
            %>
        </table>

        <h2>Enter an email to remove the user</h2>
        <form action="FrontController?origin=removeUser" method="post">
            Email:<br>
            <input type="text" name="email"><br><br>
            <input type="submit" value="Remove user"><br><br>
        </form>
        <table>
            <% for (Object user : userList) { %>
            <tr><% out.print(user); %></tr><br><br>
            <%}
            %>
        </table>
    </body>
</html>
