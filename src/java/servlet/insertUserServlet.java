package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClasses.User;
import javaClasses.Validation;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

public class insertUserServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User us = new User();
        User user = new User();
        Validation val = new Validation();
        JSONObject jsobj = new JSONObject();
        PrintWriter out = response.getWriter();
        response.setContentType("Application/json;charset=UTF-8");

//        //check firtName :
        try {
//            if (request.getParameter("firstName") != null) {
//                jsobj.put("firstname", "you must enter first name");
//            }
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
                jsobj = user.jsonobject(user);
//                jsobj.put("key", 1);
//                jsobj.put("message", "login successfully");
                out.write(jsobj.toString());
//                out.write(jsobj.toString());

            } else {
//               jsobj.put("key", 0);
                    jsobj.put("message", "sin up not successfully try again");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        //check lastName :
//        try {
//            us.setLastName(request.getParameter("lastName"));
//            boolean valid = val.val_name(us.getLastName());
//            boolean vali = val.isRequired(us.getLastName());
//            if (val.val_name(request.getParameter("lastName"))) {
//
//                jsobj.put("val_name", valid);
//                jsobj.put("isRequired", vali);
//                out.write(jsobj.toString());
//            } else {
//
//                jsobj.put("val_name", valid);
//                jsobj.put("isRequired", vali);
//                out.write(jsobj.toString());
//            }
//        } catch (Exception e) {
//            out.write(e.getMessage());
//        }
//
//        //   check phoneNumber :
//        try {
//            us.setPhoneNumber(request.getParameter("phoneNumber"));
//
//            boolean valid = val.isRequired(us.getPhoneNumber());
//            boolean vali = val.val_phoneNumber(us.getPhoneNumber());
//            if (val.val_phoneNumber(request.getParameter("phoneNumber"))) {
//
//                jsobj.put("isRequired", valid);
//                jsobj.put("val_phoneNumber", vali);
//                out.write(jsobj.toString());
//
//            } else {
//
//                jsobj.put("isRequired", valid);
//                jsobj.put("val_phoneNumber", vali);
//                out.write(jsobj.toString());
//
//            }
//        } catch (Exception e) {
//            out.write(e.getMessage());
//        }
////
////
////        //check email :
//        try {
//            us.setEmail(request.getParameter("email"));
//
//            boolean valid = val.isRequired(us.getEmail());
//            boolean vali = val.val_email(us.getEmail());
//            if (val.val_email(request.getParameter("email")) {
//
//                jsobj.put("isRequired", valid);
//                jsobj.put("val_email", vali);
//                out.write(jsobj.toString());
//
//            } else {
//
//                jsobj.put("isRequired", valid);
//                jsobj.put("val_email", vali);
//                out.write(jsobj.toString());
//
//            }
//        } catch (Exception e) {
//            out.write(e.getMessage());
//        }
//
//        //check password :
//        try {
//            us.setUserPassword(request.getParameter("password"));
//            boolean valid = val.val_passwprd(us.getUserPassword());
////            boolean vali = val.isRequired(us.getUserPassword());
//            if (val.val_passwprd(request.getParameter("password"))) {
//
//                jsobj.put("val_passwprd", valid);
////                jsobj.put("isRequired", vali);
////                jsobj.put("my name", us.getFirstName());
//                out.write(jsobj.toString());
//            } else {
//
//                jsobj.put("val_passwprd", valid);
////                jsobj.put("isRequired", vali);
//                out.write(jsobj.toString());
//            }
//        } catch (Exception e) {
//            out.write(e.getMessage());
//        }
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
