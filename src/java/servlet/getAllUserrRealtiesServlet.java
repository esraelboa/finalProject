/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DataBase.RealtyDAO;
import java.io.IOException;
import java.util.ArrayList;
import javaClasses.Realty;
import javaClasses.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author esra1996
 */
@WebServlet(name = "getAllUserrRealtiesServlet", urlPatterns = {"/getAllUserrRealtiesServlet"})
public class getAllUserrRealtiesServlet extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        JSONObject json = new JSONObject();
        try {

             HttpSession session = request.getSession(false);
            if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {
                json.put("key", -1);
                json.put("message", "invalided session");
            } else {
                User user = (User) session.getAttribute("user");
                int id = user.getId();
                ArrayList<Realty> realties = RealtyDAO.getAllRealty(id);
                if (realties.size() > 0) {
                    JSONArray ja = new Realty().displayAllRealty(realties);
                    json.put("realties", ja);
                } else {
                    json.put("key", 0);
                    json.put("message", "You have no realty");
                }

            }
            response.getWriter().write(json.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());

        }

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
