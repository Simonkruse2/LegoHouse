<%@page import="Logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% HttpSession session1 = request.getSession();
            User u = (User) session1.getAttribute("user");
        %>
        <title>JSP Page</title>
    </head>
    <body>
        <a href="FrontController?origin=index"><img src="IMG/lego.jpg" alt=""/></a>
        <p>Logged in as: <% out.print(u.getEmail());%></p>
        <a href="FrontController?origin=myOrders"><button>Previous Orders</button></a><br><br>
        <h2>Fill out the form to place an order</h2>
        <form action="FrontController?origin=calcBricks" method="post">
            Length:<br>
            <input type="text" name="length"><br><br>
            Width:<br>
            <input type="text" name="width"><br><br>
            Height:<br>
            <input type="text" name="height"><br><br>
            Banding:<br>
            <input type="radio" name="band" value="1" checked> ½-stensforbandt<br>
            <input type="radio" name="band" value="2"> 1/4-stensforbandt<br>
            <input type="radio" name="band" value="3"> Blok forbandt<br>
            <input type="radio" name="band" value="4"> Kryds forbandt<br>
            <input type="radio" name="band" value="5"> Engelsk forbandt <br><br>
            <input type="submit" value="Calculate bricks"><br><br>
        </form>

    </body>
</html>
