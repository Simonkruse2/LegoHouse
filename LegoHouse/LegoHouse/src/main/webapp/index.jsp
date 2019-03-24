<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="FrontController?origin=index"><img src="IMG/lego.jpg" alt=""/></a>
        <form action="FrontController?origin=checkLogin" method="post">
            Email:<br>
            <input type="text" name="email" placeholder="Email"><br><br>
            Password:<br>
            <input type="password" name="password" ><br><br>
            <input type="submit" value="Login"><br><br>
        </form>
        <a href="FrontController?origin=createUser" >Click here if you're not already a user</a>
    </body>
</html>
