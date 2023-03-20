<%-- 
    Document   : viewattend
    Created on : Mar 18, 2023, 6:42:31 PM
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
                    <a href="viewattend?gid=${g.id}"><li>(${g.name}-${g.course.id}-${g.course.name})</li></a>
                        </c:forEach>
            </ul>
        </div>
        <c:if test="${requestScope.attends ne null}">
            <table border="1px">
                <tr>
                    <td>NO</td>
                    <td>DATE</td>
                    <td>SLOT</td>
                    <td>ROOM</td>
                    <td>LECTURE</td>
                    <td>GROUP<br>NAME</td>
                    <td>ATTEDANCE<br>STATUS</td>
                    <td>LECTURER'S <br>COMMENT</td>
                </tr
            <c:forEach items="${requestScope.attends}" var="a" varStatus="loop">
                <tr>
                    <td>${loop.index}</td>
                    <td>${a.session.date}</td>
                    <td>${a.session.slot.id}_(${a.session.slot.start}-${a.session.slot.end}}</td>
                    <td>${a.session.room.id}</td>
                    <td>${a.session.lecturer.id}</td>
                    <td>${a.session.group.name}</td>
                    <td><c:if test="${a.session.status eq true and a.session.notyet eq false}">Present</c:if>
                        <c:if test="${a.session.status eq false and a.session.notyet eq false}">Absent</c:if>
                        <c:if test="${a.session.notyet eq true}">Future</c:if>
                        </td>
                        <td>${a.comment}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <a href="home">Return to homepage</a>
</body>
</html>
