<%-- 
    Document   : timetable.jsp
    Created on : Mar 18, 2023, 6:42:18 PM
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
        <div class="header">
            <div class="header-info"><h1>FPT University Attendance Taking</h1></div>

            <div class="header-img">
                <img src="../view/img/fpt-university-logo.png" alt="Image description"/>
            </div>
        </div>
        <div class="studentnput">
            <input type="hidden" name="student" value="${sessionScope.user.id}">
        </div>
        <table border="1px">
            <tr>
                <td><div class="formYw">
                        <form id="tableForm" action="timetable" method="POST">
                            YEAR:
                            <select id="yearSelect" name="year" onchange="this.form.submit()" onchange="">
                                <option value="2022"<c:if test="${requestScope.year eq 2022}">selected</c:if>>2022</option>
                                <option value="2023" <c:if test="${requestScope.year eq 2023}">selected</c:if>>2023</option>
                                <option value="2024" <c:if test="${requestScope.year eq 2024}">selected</c:if>>2024</option>
                                </select>
                                <br>
                                WEEK:
                                <select id="weekSelect" name="week" onchange="this.form.submit()">
                                    /*<c:forEach items="${requestScope.weeks}" var="w" varStatus="loop">
                                    <option value="${w}" <c:if test="${requestScope.fromandto eq w}">selected</c:if>>${requestScope.weeksto[loop.index]}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" id="hiddenInput" name="hiddenInput" value="">
                        </form>
                    </div></td>
                    <c:forEach items="${requestScope.dates}" var="d">
                    <td><div class="dateDay">
                            <fmt:formatDate value="${d}" pattern="EEEE" />
                        </div>
                        <br>
                        <div class="dateWeek">
                            <fmt:formatDate value="${d}" pattern="dd/MM"/>
                        </div>
                    </td>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slots}" var="st">
                <tr>
                    <td>Slot ${st.id}
                        </br>
                        (
                        <fmt:formatDate value="${st.start}" pattern="HH:mm" />
                        -
                        <fmt:formatDate value="${st.end}" pattern="HH:mm" />
                        )</td>
                        <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.s.groups}" var="g">
                                <c:forEach items="${g.sessions}" var="ses">
                                    <c:if test="${ses.date eq d and ses.slot.id eq st.id}">
                                        ${g.name}(${g.course.name}) <br/>
                                        ${ses.lecturer.name}-${ses.room.id} <br/>
                                        <c:if test="${ses.status eq false && ses.notyet eq false}">
                                            Absent
                                        </c:if>
                                        <c:if test="${ses.status ne false && ses.notyet eq false}">
                                            Attend
                                        </c:if>
                                        <c:if test="${ses.status eq false && ses.notyet eq true}">Not Yet</c:if>
                                            </br>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <a href="home">Return homepage</a>
    </body>
</html>
