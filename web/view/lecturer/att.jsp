<%-- 
    Document   : att
    Created on : Mar 15, 2023, 2:02:41 AM
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
        <form action="takeattend" method="POST">
            <table border="1px">
                <tr>
                    <td>Seq</td>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Absent/Present</td>
                    <td>Comment</td>
                </tr>
                <c:forEach items="${requestScope.atts}" var="a" varStatus="loop">
                    <tr>
                        <td>${loop.index}</td>
                        <td>${a.student.id}
                            <input type="hidden" name="sid" value="${a.student.id}" />
                            <input type="hidden" name="aid${a.student.id}" value="${a.id}" />
                        </td>
                        <td>${a.student.name}</td>
                        <td><input type="radio"
                                   <c:if test="${!a.status}">
                                   checked="checked"
                                   </c:if>
                                   name="status${a.student.id}" value="absent"/>Absent
                            <input type="radio"
                                   <c:if test="${a.status}">
                                   checked="checked"
                                   </c:if>
                                   name="status${a.student.id}" value="present"/>Present
                        </td>
                        <td><input type="text" value="${a.comment}" name="comment${a.student.id}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <input type="hidden" name="sessionid" value="${param.id}"/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
