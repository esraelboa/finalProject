/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DataBase.CommercialRealtiesDAO;
import DataBase.ResidentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javaClasses.Category;
import javaClasses.CommercialRealties;
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
public class UpdateCommercialRealtyInfo extends HttpServlet {

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
               CommercialRealties commercialRealty =new CommercialRealties();
               commercialRealty.setId(Integer.parseInt(request.getParameter("id")));
               commercialRealty.setRealtyName(request.getParameter("realtyName"));
               commercialRealty.setDescription(request.getParameter("description"));
               Category category=new Category();
               category.setCatId(Integer.parseInt(request.getParameter("catId")));
               commercialRealty.setCategory(category);

                int effectedRow = CommercialRealtiesDAO.updateCommercialRealtyInfo(commercialRealty);
                if (effectedRow > 0) {
                    json.put("Key", 1);
                    json.put("Message", "updated successfully");
                } else {
                    json.put("Key", 0);
                    json.put("Message", "error try again");
                }
            }
            response.getWriter().write(json.toString());
        } catch (Exception e) {
            System.out.println(e);
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
