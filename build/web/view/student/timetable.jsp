<%-- 
    Document   : timetable.jsp
    Created on : Mar 18, 2023, 6:42:18 PM
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
        <div class="header">
            <div class="header-info"><h1>FPT University Attendance Taking</h1></div>

            <div class="header-img">
                <img src="../view/img/fpt-university-logo.png" alt="Image description"/>
            </div>
        </div>
        <div class="studentnput">
            <input type="text" value="${sessionScope.user.id}"  readonly="">
            <input type="hidden" name="student" value="${sessionScope.user.id}">
        </div>

    </body>
</html>
