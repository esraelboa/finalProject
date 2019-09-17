package servlet;

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
        User us = new User();
        User user = new User();
        Validation val = new Validation();
        JSONObject jsobj = new JSONObject();
        PrintWriter out = response.getWriter();
        //check firtName :
        try {

            us.setFirstName(request.getParameter("firstName"));
            us.setLastName(request.getParameter("lastName"));
            us.setPhoneNumber(request.getParameter("phoneNumber"));
            us.setEmail(request.getParameter("email"));
            us.setUserPassword(request.getParameter("password"));

            //firstName lastName phoneNumber email password
            if (val.val_name(request.getParameter("firstName")) && (val.isRequired(us.getFirstName()))
                    && val.val_name(request.getParameter("lastName")) && val.isRequired(us.getLastName())
                    && val.val_phoneNumber(request.getParameter("phoneNumber")) && val.isRequired(us.getPhoneNumber())
                    && val.val_email(request.getParameter("email")) && val.val_email(us.getEmail())
                    && val.val_passwprd(request.getParameter("password")) && val.isRequired(us.getUserPassword())) {

                int id = user.insertuser(us);
//                jsobj.put("The ID for the new User", id);
                request.getSession().invalidate();
                HttpSession sassion = request.getSession();
                sassion.setAttribute("user", user);
//                jsobj = user.jsonobject(user);
                jsobj.put("user id",id);
                jsobj.put("key", 1);
                jsobj.put("message", "sin up successfully");

            } else {
                jsobj.put("key", 0);
                jsobj.put("message", "sin up not successfully try again");
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
