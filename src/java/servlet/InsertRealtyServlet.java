/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DataBase.RealtyDAO;
import java.io.IOException;
import javaClasses.Marker;
import javaClasses.Realty;
import javaClasses.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author esra
 */
public class InsertRealtyServlet extends HttpServlet {

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
        Realty realty = new Realty();
        Marker marker = new Marker();
        JSONObject json = new JSONObject();
        int realtyid=0;

        try {
            HttpSession session = request.getSession(false);
            if (session == null || !request.isRequestedSessionIdValid()) {
                json.put("key", -1);
                json.put("message", "invalided session");
            } else {
                User user = (User) session.getAttribute("user");
                realty.setRealtyNumber(Integer.parseInt(request.getParameter("realtyNumber")));
                marker.setLng(Double.parseDouble(request.getParameter("lng")));
                marker.setLat(Double.parseDouble(request.getParameter("lat")));
                realty.setPosition(marker);
                realty.setOwnerid(Integer.parseInt(request.getParameter("ownerId"))/*user.getId()*/);
                realty.setDescription(request.getParameter("description"));
                realtyid = RealtyDAO.insertRealty(realty);

                if (realtyid != 0) {
                    realty.setId(realtyid);
                    json.put("key", 1);
                    json.put("message", "realty inserted successfully realty id is : " + realtyid);
                } else {
                    json.put("key", 0);
                    json.put("message", "error try again");
                }
            }
            response.getWriter().write(json.toString());

        } catch (Exception ex) {
            System.out.println(ex.toString());
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
