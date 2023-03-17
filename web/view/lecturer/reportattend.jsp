<%-- 
    Document   : reportattend
    Created on : Mar 15, 2023, 6:08:00 PM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <ul>
                <c:forEach items="${requestScope.groups}" var="g">
                    <a href="reportattend?gid=${g.id}"><li>(${g.name}-${g.course.id}-${g.course.name})</li></a>
                        </c:forEach>
            </ul>
        </div>

        <c:if test="${requestScope.students ne null}">
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
                        <td>
                            <fmt:formatNumber value="${(s.absent/20)*100}" type="number" pattern="#,##0.00" />
                            %</td>
                    </tr>        
                </c:forEach>
            </table>
        </c:if>
        <a href="home">Return to homepage</a>



    </body>
</html>
