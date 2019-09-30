package servlet;

import DataBase.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javaClasses.User;
import javaClasses.Validation;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

public class registerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("Application/json;charset=UTF-8");
        User user = new User();
        Validation val = new Validation();
        JSONObject jsobj = new JSONObject();
        PrintWriter out = response.getWriter();

        try {
            // get All parameters from request
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setPhoneNumber(request.getParameter("phoneNumber"));
            user.setEmail(request.getParameter("email"));
            user.setUserPassword(request.getParameter("password"));

            // checking validaty for firstName lastName phoneNumber email password
            if (val.val_name(user.getFirstName()) && (val.isRequired(user.getFirstName()))
                    && val.val_name(user.getLastName()) && val.isRequired(user.getLastName())
                    && val.val_phoneNumber(user.getPhoneNumber()) && val.isRequired(user.getPhoneNumber())
                    && val.val_email(user.getEmail()) && val.val_email(user.getEmail())
                    && val.val_password(user.getUserPassword()) && val.isRequired(user.getUserPassword())) {
            // insert user 
                int id = UserDAO.insertUser(user);
            //create user session 
                request.getSession().invalidate();
                HttpSession sassion = request.getSession();
                sassion.setAttribute("user", user);
            //fill json object in sussecc 
                jsobj.put("key", 1);
                jsobj.put("message", "sing up successfully");
            } else {
            //fill json object in failed 
                jsobj.put("key", 0);
                jsobj.put("message", "sing up not successfully try again");
            }
            out.write(jsobj.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());

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
