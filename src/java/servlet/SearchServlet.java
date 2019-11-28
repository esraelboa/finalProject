/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DataBase.RealtyDAO;
import DataBase.ResidentDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClasses.Realty;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author esra
 */
public class SearchServlet extends HttpServlet {

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
            Realty realty = null;
            String input = request.getParameter("address");
            String[] a = input.split("[.,]");
            switch (a.length) {
                case 4:
                    realty = RealtyDAO.searchForAddress(request.getParameter("address"));
                    break;
                case 5:
                    realty = ResidentDAO.searchForAddress(request.getParameter("address"));
                    break;
                default:
                    response.sendRedirect("getCommercialRealties?address="+input);
                    break;
            }

            if (realty != null) {
                json.put("key", 1);
                json.put("message", "address is available");
                json.put("id", realty.getId());
                json.put("Lng", realty.getPosition().getLng());
                json.put("Lat", realty.getPosition().getLat());
                json.put("Description", realty.getDescription()+"");
            } else {
                json.put("key", 0);
                json.put("message", "error ,not exites address");
            }
            response.getWriter().write(json.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
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
