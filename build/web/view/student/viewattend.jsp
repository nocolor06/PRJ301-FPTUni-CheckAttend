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
        }

        .timetable td{
            border-bottom: 1px solid black;
            border-collapse: collapse;
            padding: 3px;
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
                <li><a href="${pageContext.request.contextPath}/student/timetable">ViewTimeTable</a></li>
                <li><a href="${pageContext.request.contextPath}/student/viewattend">Report Attend Of Classes</a></li>
            </ul>
        </div>   
        <div class="form-logout">
            <form method="post" action="../auth/logout" >  
                <button>Logout</button>
            </form>
        </div>
    </div>
        <div>
            <p>List of your courses:</p>
            <ul>
                <c:forEach items="${requestScope.groups}" var="g">
                    <a href="viewattend?gid=${g.id}"><li>(${g.name}-${g.course.id}-${g.course.name})</li></a>
                        </c:forEach>
            </ul>
        </div>
        <c:if test="${requestScope.attends ne null}">
            <table border="1px" class="timetable">
                <tr class="timetable-head">
                    <td>NO</td>
                    <td>DATE</td>
                    <td>SLOT</td>
                    <td>ROOM</td>
                    <td>LECTURE</td>
                    <td>GROUP<br>NAME</td>
                    <td>ATTEDANCE<br>STATUS</td>
                    <td>LECTURER'S <br>COMMENT</td>
                </tr>
            <c:forEach items="${requestScope.attends}" var="a" varStatus="loop">
                <tr>
                    <td>${loop.index}</td>
                    <td>${a.session.date}</td>
                    <td>${a.session.slot.id}_(${a.session.slot.start}-${a.session.slot.end}}</td>
                    <td>${a.session.room.id}</td>
                    <td>${a.session.lecturer.id}</td>
                    <td>${a.session.group.name}</td>
                    <td><c:if test="${a.session.status eq true and a.session.notyet eq false}"><p style="color:green">Present</p></c:if>
                        <c:if test="${a.session.status eq false and a.session.notyet eq false}"><p style="color:green">Absent</p></c:if>
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
