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
        <link rel="stylesheet" href="../view/css/styletimetable.css">
        <title>FU AT</title>
    </head>
    <style>
        .header{
            display: flex;
            justify-content: space-between;
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

        .lecturerinput{
            display: flex;
            justify-content: center;
            margin-bottom: 1em;
            margin-left: 5em;
            margin-top:5em;
        }
        .timetable{
            margin-left: auto;
            margin-right: auto;
            border: 1px solid black;
            border-collapse: collapse;
            width: 100%;
            height: 50%;
        }

        .timetable td{
            border-bottom: 1px solid black;
            border-collapse: collapse;
        }
        .timetable-head{
            background-color: orange;
        }
        .timetable td{
            border-left: white solid 1px;
        }
        .dateWeek{
            border-top: white solid 1px;
        }
        .main{
            display: flex;
        }
        .options ul{
            display: flex;
            list-style: none;
            text-align: center;
            margin-top: 3em;
            margin-left: 3em;
            font-style: normal;

        }
        .options li{
            margin-right: 5em;
            text-align: center;
            font-style: normal;
        }
        .form-logout{
            margin-top:3em;
        }
    </style>
    <body>
        <div class="main">
            <div class="header">
                <div class="header-info"><h1>FPT University Attendance Taking</h1></div>
            </div>

            <div class="options">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/lecturer/timetable">ViewTimeTable</a></li>
                    <li><a href="${pageContext.request.contextPath}/lecturer/reportattend">Report Attend Of Classes</a></li>
                </ul>
            </div>   
            <div class="form-logout">
                <form method="post" action="../auth/logout" >  
                    <button>Logout</button>
                </form>
            </div>
        </div>
        <div class="lecturerinput">
            <label>Lecturer: </label>
            <input type="text" name="lid" value="${sessionScope.user.id}" readonly >
        </div>
        <table class="timetable">
            <tr class="timetable-head">
                <td>
                    <div class="formYw">
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
                    </div>
                </td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>
                        <div class="dateDay">
                            <fmt:formatDate value="${d}" pattern="EEEE" />
                        </div>
                        <br>
                        <div class="dateWeek">
                            <fmt:formatDate value="${d}" pattern="dd/MM"/>
                        </div>
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
                            <p>-
                                <c:forEach items="${requestScope.sessions}" var="ses">
                                    <c:if test="${ses.slot.id eq s.id and ses.date eq d}">
                                        ${ses.group.name}-${ses.group.course.id} <br/>
                                        ${ses.room.id} 
                                        <c:set var="name" value="${ses.name}"> </c:set>
                                        <c:set var="takeattend" value="Take Attend"> </c:set>
                                        <c:if test="${name eq takeattend}">-<a href="takeattend?id=${ses.id}" style="color: green">${ses.name}</a></c:if>
                                        <c:if test="${!(name eq takeattend)}"><a href="checkattend?id=${ses.id}" style="color:green">-${ses.name}</c:if>
                                        </c:if>
                                    </c:forEach>
                            </p>
                        </td>
                    </c:forEach>
                <tr/> 
            </c:forEach>
        </table>
        <a href="home">Return to homepage</a>
    </body>
</html>
