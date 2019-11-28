/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DataBase.UserDAO;
import java.io.IOException;
import javaClasses.User;
import javaClasses.Validation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author esra1996
 */
@WebServlet(name = "UpdateUserrServlet", urlPatterns = {"/UpdateUserrServlet"})
public class UpdateUserrServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {
                json.put("key", -1);
                json.put("message", "invalided session");
            } else {
                
                Validation val = new Validation();
                User user = (User) session.getAttribute("user");
                int id = user.getId();
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                //check user validtion       
                if (val.val_name(firstname) && val.isRequired(firstname)
                        && val.val_name(lastname) && val.isRequired(lastname)){
                    int effectedRows = UserDAO.UpdateUser(id, firstname, lastname);
                    if (effectedRows > 0) {
                        json.put("Key", 1);
                        json.put("Message", "updated successfully");
                    } else {
                        json.put("Key", 0);
                        json.put("Message", "error try again");
                    }
                } else {
                    //fill json object in failed 
                    json.put("key", 0);
                    json.put("message", "error try again");
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
