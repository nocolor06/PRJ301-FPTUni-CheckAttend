<%-- 
    Document   : login
    Created on : Mar 17, 2023, 8:57:30 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        ${sessionScope.filtermess}
        <form method="post" action="login">
            <div>Username: <input type="text" name="username" required="" value="${param.username}"></div>
            <div>Password: <input type="password" name="password" required="" value="${param.password}"></div>
            <button>Login</button>
            ${requestScope.message}
        </form>
    </body>
</html>
