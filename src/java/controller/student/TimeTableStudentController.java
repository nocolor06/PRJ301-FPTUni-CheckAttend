/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import dal.LecturerDBContext;
import dal.StudentDBContext;
import dal.TimeSlotDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import model.Group;
import model.Session;
import model.Student;
import model.TimeSlot;
import model.User;
import util.DateTimeHelper;

/**
 *
 * @author Asus
 */
public class TimeTableStudentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lid = request.getParameter("student");
        Date from = Date.valueOf(request.getParameter("from"));
        Date to = Date.valueOf(request.getParameter("to"));
        ArrayList<Date> dates = DateTimeHelper.getListDates(from, to);
        TimeSlotDBContext dbSlot = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = dbSlot.all();
        LecturerDBContext lecDb = new LecturerDBContext();
        ArrayList<Session> sessions = lecDb.getSessions(lid);
        request.setAttribute("slots", slots);
        request.setAttribute("dates", dates);
        request.setAttribute("sessions", sessions);
        System.out.println(sessions.size());
        request.getRequestDispatcher("../view/lecturer/timetable.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        if (u.getRole() == 1) {
            request.getRequestDispatcher("../view/lecturer/home.jsp").forward(request, response);
        }
        ArrayList<String> weeks = DateTimeHelper.getWeekStartEndDates(LocalDate.now().getYear(), " ");
        ArrayList<String> weeksto = DateTimeHelper.getWeekStartEndDates(LocalDate.now().getYear(), " to ");
        request.setAttribute("weeks", weeks);
        request.setAttribute("weeksto", weeksto);
        request.setAttribute("year", LocalDate.now().getYear());
        String lid = u.getId();

        LocalDate currentDate = LocalDate.now();
        LocalDate startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        Date sqlStartDate = Date.valueOf(startOfWeek);
        Date sqlEndDate = Date.valueOf(endOfWeek);
        ArrayList<Date> dates = DateTimeHelper.getListDates(sqlStartDate, sqlEndDate);

        TimeSlotDBContext dbSlot = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = dbSlot.all();
        StudentDBContext stuDb = new StudentDBContext();
        Student s = stuDb.getTimeTable(lid, sqlStartDate, sqlEndDate);
//        LecturerDBContext lecDb = new LecturerDBContext();
//        ArrayList<Session> sessions = lecDb.getSessions(lid);

        String fromandto = sqlStartDate.toString().substring(5) + " " + sqlEndDate.toString().substring(5);
        System.out.println(fromandto);
        System.out.println(s.getGroups().get(1).getSessions().toString());
        request.setAttribute("weeksto", weeksto);
        request.setAttribute("weeks", weeks);
        request.setAttribute("mid", " to ");
        request.setAttribute("slots", slots);
        request.setAttribute("dates", dates);
        request.setAttribute("s", s);
        for (Group g : s.getGroups()) {
            System.out.println(g.getId() +":" );
            for (Session ses : g.getSessions()) {
                System.out.print(ses.getId()+" ");
            }
            System.out.println("");
        }
//        request.setAttribute("sessions", sessions);
        request.setAttribute("lecturer", lid);
        request.setAttribute("fromandto", fromandto);
        request.getRequestDispatcher("../view/student/timetable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lid = request.getParameter("student");
//        Date from = Date.valueOf(request.getParameter("from"));
//        Date to = Date.valueOf(request.getParameter("to"));
        if (lid == null) {
            User u = (User) request.getSession().getAttribute("user");
            lid = u.getId();
        }
        Date from, to;
        String year = request.getParameter("year");
        String fromandto = request.getParameter("week");
        if (fromandto == null || year == null) {
            from = Date.valueOf("2023-03-06");
            to = Date.valueOf("2023-03-12");
        } else {
            String[] x = fromandto.split(" ");
            from = Date.valueOf(year + "-" + x[0]);
            to = Date.valueOf(year + "-" + x[1]);
        }
        ArrayList<Date> dates = DateTimeHelper.getListDates(from, to);
        ArrayList<String> weeks = DateTimeHelper.getWeekStartEndDates(Integer.parseInt(year), " ");
        ArrayList<String> weeksto = DateTimeHelper.getWeekStartEndDates(Integer.parseInt(year), " to ");
        TimeSlotDBContext dbSlot = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = dbSlot.all();
        StudentDBContext stuDb = new StudentDBContext();
        Student s = stuDb.getTimeTable(lid, from, to);
//        LecturerDBContext lecDb = new LecturerDBContext();
//        ArrayList<Session> sessions = lecDb.getSessions(lid);

        request.setAttribute("weeksto", weeksto);
        request.setAttribute("weeks", weeks);
        request.setAttribute("mid", " to ");
        request.setAttribute("slots", slots);
        request.setAttribute("dates", dates);
        request.setAttribute("s", s);
//        request.setAttribute("sessions", sessions);
        request.setAttribute("lecturer", lid);
        request.setAttribute("year", year);
        request.setAttribute("fromandto", fromandto);
//        System.out.println(sessions.size());
        request.getRequestDispatcher("../view/student/timetable.jsp").forward(request, response);
    }
}
