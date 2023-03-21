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
    <style>
        body{
            background-image: url("../view/img/fpt-ho.png");
            background-repeat: no-repeat, repeat;
            background-size: cover;
        }
        .login-main{
            width: fit-content;
            border: 1px solid orange;
            background: grey;
            padding: 20px;
            position: fixed;
            top: 40%;
            left: 44%;
        }
        .header{
            display: flex;
            justify-content: center;
        }
        .header-info{
            padding-top: 1.5em;
            padding-left: 5em;
            text-transform: uppercase;
            font-size: 1em;
            font-weight: normal;
            font-family: sans-serif;
        }

        .header-img{
            padding-right: 5em;
        }
        .header img{
            width: 12em;
            height: 10em;
        }
        button{
            color:beige;
            background-color: orange;
            width: 100%;
            height: fit-content;
            padding: 10px 0;
            border-radius: 10px;
        }
        .input_p{
            color:beige;
            font-weight: bold;
            font-size: 18px;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        input{
            margin-top: 10px;
            margin-bottom: 10px;
            padding: 5px;
        }
    </style>
    <body>
        <div class="header">
            <div class="header-info"><h1>FPT University Attendance Taking</h1></div>
        </div>
        ${sessionScope.filtermess}
        <div class="login-main">
            <form method="post" action="login">
                <div><label class="input_p">Username </label></br><input type="text" name="username" required="" value="${param.username}" placeholder="Enter your username"></div>
                <div><label class="input_p">Password  </label></br> <input type="password" name="password" required="" value="${param.password}" placeholder="Enter your password"></div>
                <button>Login</button>
                ${requestScope.message}
            </form>
        </div>
    </body>
</html>
