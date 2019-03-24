<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% HttpSession session1 = request.getSession();
            int[] brickList = (int[]) session1.getAttribute("viewBricksList");
        %>
        <title>JSP Page</title>
    </head>
    <body>
        <a href="FrontController?origin=index"><img src="IMG/lego.jpg" alt=""/></a>
        <h1>Bricks for your order</h1>
        <table>
            <h2>Side 1 & 3</h2><br>
            <h3>Bricks x4</h3>
            <tr><% out.print(brickList[0]); %></tr><br>
            <h3>Bricks x2</h3>
            <tr><% out.print(brickList[1]); %></tr><br>
            <h3>Bricks x1</h3>
            <tr><% out.print(brickList[2]); %></tr><br>
            <h2>Side 2 & 4</h2><br>
            <h3>Bricks x4</h3>
            <tr><% out.print(brickList[3]); %></tr><br>
            <h3>Bricks x2</h3>
            <tr><% out.print(brickList[4]); %></tr><br>
            <h3>Bricks x1</h3>
            <tr><% out.print(brickList[5]); %></tr><br>
        </table>
        
    </body>
</html>
