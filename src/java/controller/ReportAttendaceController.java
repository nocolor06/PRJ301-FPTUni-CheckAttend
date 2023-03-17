/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LecturerDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Group;
import model.Student;
import model.User;

/**
 *
 * @author Asus
 */
public class ReportAttendaceController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDBContext db = new StudentDBContext();
        String gid = request.getParameter("gid");
        User user = (User) request.getSession().getAttribute("user");
        String lid = user.getId();
        LecturerDBContext ldb = new LecturerDBContext();
        ArrayList<Group> groups = ldb.getAllGroup(lid);
        boolean checkGroup = false;
        request.setAttribute("groups", groups);
        if (gid != null) {
            try {
                int gid_real = Integer.parseInt(gid);
                for (Group g : groups) {
                    if (g.getId() == gid_real) {
                        checkGroup = true;
                        break;
                    }
                }

            } catch (Exception e) {
                request.getRequestDispatcher("../view/lecturer/reportattend.jsp").forward(request, response);
            }
            if (!checkGroup) {
                request.getRequestDispatcher("../view/lecturer/reportattend.jsp").forward(request, response);
            }
            ArrayList<Student> students = db.reportAttend(Integer.parseInt(gid));
            request.setAttribute("students", students);
        }
        request.getRequestDispatcher("../view/lecturer/reportattend.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
