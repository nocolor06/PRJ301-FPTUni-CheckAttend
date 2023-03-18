/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AttendDBContext;
import dal.LecturerDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Attend;
import model.Session;
import model.Student;
import model.User;

/**
 *
 * @author Asus
 */
public class TakeAttendanceController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TakeAttendanceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TakeAttendanceController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        if (u.getRole() != 1) {
            request.getRequestDispatcher("../view/student/home.jsp").forward(request, response);
        }
        AttendDBContext db = new AttendDBContext();
        int x;
        try {
            x = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            request.getRequestDispatcher("../view/lecturer/home.jsp").forward(request, response);
        }
        x = Integer.parseInt(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("user");
        LecturerDBContext ldb = new LecturerDBContext();
        ArrayList<Session> sessions = ldb.getSessions(user.getId());
        boolean checkSession = false;
        for (Session s : sessions) {
            if (s.getId() == x) {
                checkSession = true;
                break;
            }
        }
        if (checkSession == false) {
            request.getRequestDispatcher("../view/lecturer/home.jsp").forward(request, response);
        }
        ArrayList<Attend> atts = db.getAttsBySessionID(x);
        request.setAttribute("atts", atts);
        request.getRequestDispatcher("../view/lecturer/att.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] sids = request.getParameterValues("sid");
        int sessionid = Integer.parseInt(request.getParameter("sessionid"));
        ArrayList<Attend> atts = new ArrayList<>();
        for (String sid : sids) {
            Student s = new Student();
            s.setId(sid);
            Attend a = new Attend();
            a.setStudent(s);
            a.setStatus(request.getParameter("status" + sid).equals("present"));
            a.setComment(request.getParameter("comment" + sid));
            Timestamp today = new Timestamp(System.currentTimeMillis());
            a.setRecordTime(today);
            a.setId(Integer.parseInt(request.getParameter("aid" + sid)));
            atts.add(a);
        }
        AttendDBContext db = new AttendDBContext();
        db.updateAtts(atts, sessionid);
        response.sendRedirect("takeattend?id=" + sessionid);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
