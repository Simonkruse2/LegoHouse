<%@page import="java.util.ArrayList"%>
<%@page import="Logic.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% HttpSession session1 = request.getSession();
            ArrayList<Order> myOrders = (ArrayList<Order>) session1.getAttribute("myOrders");
        %>
        <title>JSP Page</title>
    </head>
    <body>
        <a href="FrontController?origin=index"><img src="IMG/lego.jpg" alt=""/></a>
        <h1>My previous orders</h1>
        <h3>Enter orderID to view bricks for your order</h3>
        <form action="FrontController?origin=viewBricks" method="post">
            <input type="text" name="viewBricksID"><br>
            <input type="submit" value="View bricks"><br><br>
        </form>
        <table>
            <% for (Object order : myOrders) { %>
            <tr><% out.print(order); %></tr><br><br>
            <%}
            %>
        </table>
    </body>
</html>
