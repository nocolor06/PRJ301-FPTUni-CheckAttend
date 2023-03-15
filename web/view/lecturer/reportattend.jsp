<%-- 
    Document   : reportattend
    Created on : Mar 15, 2023, 6:08:00 PM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <table border="1px">
            <tr>
                <td>Student ID</td>
                <td>Student Name</td>
                <td>Absent</td>
            </tr>

            <c:forEach items="${requestScope.students}" var="s">
                <tr>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <td>${(s.absent/20)*100}%</td>
                </tr>        
            </c:forEach>
        </table>



    </body>
</html>
