package servlet;

import javaClasses.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClasses.Validation;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

public class login_servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("Application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                JSONObject json = new JSONObject();
                Validation val = new Validation();
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                User user = new User();

                if (val.val_passwprd(request.getParameter("password")) && (val.val_email(request.getParameter("email"))) && (val.isRequired(request.getParameter("email")))) {
                    user = user.get_email_password(email, password);

                    if (user != null) {
                        // init session
                        // put user opject in the session
                        request.getSession().invalidate();
                        HttpSession sassion = request.getSession();
                        sassion.setAttribute("user", user);
                        json = user.jsonobject(user);
                        json.put("key", 1);
                        json.put("message", "login successfully");
                        out.write(json.toString());

                    }
                } else {
                    json.put("key", 0);
                    json.put("message", "login not successfully try again");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();
    }

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
