<%-- 
    Document   : timetable
    Created on : Mar 10, 2023, 4:08:36 PM
    Author     : sonnt
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <td></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>${d} <br/>
                        <fmt:formatDate value="${d}" pattern="EEEE" />

                    </td>
                </c:forEach>
            <tr/>
            <c:forEach items="${requestScope.slots}" var="s">
                <tr>
                    <td>
                        Slot ${s.id}
                        </br>
                        (
                        <fmt:formatDate value="${s.start}" pattern="HH:mm" />
                        -
                        <fmt:formatDate value="${s.end}" pattern="HH:mm" />
                        )
                    </td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.sessions}" var="ses">
                                <c:if test="${ses.slot.id eq s.id and ses.date eq d}">
                                    ${ses.group.name}-${ses.group.course.id} <br/>
                                    ${ses.room.id} 
                                    <c:set var="name" value="${ses.name}"> </c:set>
                                    <c:set var="takeattend" value="Take Attend"> </c:set>

                                    <c:if test="${name eq takeattend}">-<a href="takeattend?id=${ses.id}">${ses.name}</a></c:if>
                                    <c:if test="${!(name eq takeattend)}">-${ses.name}</c:if>


                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                <tr/> 
            </c:forEach>

        </table>
    </body>
</html>
