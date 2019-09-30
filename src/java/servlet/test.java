/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DataBase.ResidentDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author esra
 */
public class test extends HttpServlet {

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
            
           int i= ResidentDAO.updateResidentInfo("cat cat cat ",5);
           json.put("i", i);
  response.getWriter().write(json.toString());          
//           HttpSession session = request.getSession(false);
//            if (session == null || !request.isRequestedSessionIdValid()) {
//                json.put("key", -1);
//                json.put("message", "invalided session");
//            } else {
//                            //getting data from request and session    
//            // User user = (User) session.getAttribute("user");
//            Resident resident = new Resident();
//            String email = request.getParameter("email");
//            resident.setOwnerId(/*user.getId()*/7); 
//            resident.setRealtyId(Integer.parseInt(request.getParameter("realtyid")));
//            resident.setAddress(request.getParameter("address"));
//
//                //check email validalty         
//                if (new Validation().val_email(email)) {
//               
//                /*checking if there's email in user table with this value 
//                   by returning user id how has this email*/
//                    resident.setResidentId(ResidentDAO.checkResidentEmail(email));                    
//                    if (resident.getResidentId() != 0 & ResidentDAO.checkResidentIdCount(resident.getResidentId())) {
////                insert resident and create address to it
//                        int insertedID = ResidentDAO.insertResident(resident);
//                        json.put("inserted Resident ID", insertedID);
//                        json.put("key", 1);
//                        json.put("message", "Resident inserted successfully");
//                    } else {
//                        json.put("Key", 0);
//                        json.put("message", "resident email not found  or resident can not be in this realty");
//                    }
//                } else {
//                    json.put("Key", 0);
//                    json.put("message", "uncorrect email");
//                }            
//            }
//            response.getWriter().write(json.toString());
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
