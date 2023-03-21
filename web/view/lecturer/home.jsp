<%-- 
    Document   : home
    Created on : Mar 17, 2023, 4:32:29 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FU AT</title>
        <link rel="stylesheet" href="../view/css/styletimetable.css">
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
            margin-bottom: 0.2em;
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
    </body>
</html>
