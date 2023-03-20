/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Attend;
import model.Group;
import model.Student;
import model.User;

/**
 *
 * @author Asus
 */
public class ViewAttendController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        if (u.getRole() == 1) {
            request.getRequestDispatcher("../view/lecturer/home.jsp").forward(request, response);
        }
        StudentDBContext stuDB = new StudentDBContext();
        User user = (User) request.getSession().getAttribute("user");
        ArrayList<Group> groups = stuDB.getAllGroups(user.getId());
        String gid = request.getParameter("gid");
        boolean checkGroup = false;
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
                request.getRequestDispatcher("../view/student/viewattend.jsp").forward(request, response);
            }
            if (checkGroup == false) {
                request.getRequestDispatcher("../view/student/viewattend.jsp").forward(request, response);
            }
            ArrayList<Attend> attends = stuDB.getSesbyId(user.getId(), Integer.parseInt(gid));
            request.setAttribute("attends", attends);
        }
        request.setAttribute("groups", groups);
        request.getRequestDispatcher("../view/student/viewattend.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
