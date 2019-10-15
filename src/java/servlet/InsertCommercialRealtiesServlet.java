
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DataBase.CommercialRealtiesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javaClasses.CommercialRealties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author esra1996
 */
public class InsertCommercialRealtiesServlet extends HttpServlet {

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
        CommercialRealties  realtie=new  CommercialRealties();
        int reaaltiesId;
        try{
        HttpSession session =request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()){
           json.put("key", -1);
           json.put("message", "invalided session");
           
        }else{
          
            realtie.setRealtyName(request.getParameter("realtyName"));
            realtie.setLicenseNumber(Integer.parseInt(request.getParameter("licenseNumber")));
            realtie.setDescription(request.getParameter("description"));
            realtie.setResidentId(Integer.parseInt(request.getParameter("residentId")));
            realtie.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
           
       
     reaaltiesId = CommercialRealtiesDAO.insertCommercialRealties(realtie);

                if (reaaltiesId > 0) {
                    json.put("key", 1);
                    json.put("message", "realty inserted successfully");
                    json.put("id",reaaltiesId );
                } else {
                    json.put("key", 0);
                    json.put("message", "error ");
                }
        }
  response.getWriter().write(json.toString());

        }catch (Exception ex) {
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
