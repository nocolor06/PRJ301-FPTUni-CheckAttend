<%-- 
    Document   : home
    Created on : Mar 17, 2023, 4:32:29 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FU AT</title>
        <link rel="stylesheet" href="../view/css/styletimetable.css">
    </head>
    <body>
        <div class="header">
            <div class="header-info"><h1>FPT University Attendance Taking</h1></div>

            <div class="header-img">
                <img src="../view/img/fpt-university-logo.png" alt="Image description"/>
            </div>
        </div>
        
        <div class="options">
            <ul>
                <li><a href="${pageContext.request.contextPath}/lecturer/timetable">ViewTimeTable</a></li>
                <li><a href="${pageContext.request.contextPath}/lecturer/reportattend">Report Attend Of Classes</a></li>
            </ul>
        </div>
        <form method="post" action="../auth/logout">  
            <button>Logout</button>
        </form>
    </body>
</html>
