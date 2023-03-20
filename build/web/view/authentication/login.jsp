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
        <link rel="stylesheet" href="../view/css/stylelogin.css">
        <title>JSP Page</title>
    </head>
    <body>
         <div class="header">
            <div class="header-info"><h1>FPT University Attendance Taking</h1></div>

            <div class="header-img">
                <img src="../view/img/fpt-university-logo.png" alt="Image description"/>
            </div>
        </div>
        ${sessionScope.filtermess}
        <div class="login-main">
            <form method="post" action="login">
                <div>Username: <input type="text" name="username" required="" value="${param.username}"></div>
                <div>Password :  <input type="password" name="password" required="" value="${param.password}"></div>
                <button>Login</button>
                ${requestScope.message}
            </form>
        </div>
    </body>
</html>
