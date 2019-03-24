<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="FrontController?origin=index"><img src="IMG/lego.jpg" alt=""/></a>
        <form action="FrontController?origin=makeUser" method="post">
            Email:<br>
            <input type="text" name="email"><br><br>
            Password:<br>
            <input type="password" name="password"><br><br>
            <input type="submit" value="Create User"><br><br>
        </form>
    </body>
</html>
