<%-- 
    Document   : home.jsp
    Created on : Mar 18, 2023, 6:35:21 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../view/css/styletimetable.css">
        <title>FU AT</title>
    </head>
    <body>
        <div class="header">
            <div class="header-info"><h1>FPT University Attendance Taking</h1></div>

            <div class="header-img">
                <img src="../view/img/fpt-university-logo.png" alt="Image description"/>
            </div>
        </div>
        <div>
            <ul>
                <a href="${pageContext.request.contextPath}/student/timetable"><li>View Weekly Time Table</li></a>
                <a href="${pageContext.request.contextPath}/student/viewattend"><li>View Attend</li></a>
            </ul>
        </div>
        <form method="post" action="../auth/logout">  
            <button>Logout</button>
        </form>
    </body>
</html>
