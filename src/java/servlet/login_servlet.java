package servlet;

import DataBase.UserDAO;
import javaClasses.User;
import java.io.IOException;
import java.io.PrintWriter;
import javaClasses.Validation;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

public class login_servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("Application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            JSONObject json = new JSONObject();
            //HttpSession session = request.getSession(false);
            request.getSession().invalidate();
            Validation val = new Validation();
            //get user email and password from request
            String email = request.getParameter("email");
            String password = request.getParameter("password");

//            User user = new User();
            //check validty of user email and password 
            if (val.val_password(password) && (val.val_email(email)) && (val.isRequired(email))) {
               
                User user = UserDAO.get_email_password(email, password);

                if (user != null) {
                   // session.invalidate();
                    HttpSession session = request.getSession();
                    session.setAttribute("user",user);
                    json = user.jsonobject(user);
                    json.put("key", 1);
                    json.put("message", "login successfully");
                } else {
                    json.put("key", 0);
                    json.put("message", "email or password is wrong or not exists");
                }

            } else {
                json.put("key", 0);
                json.put("message", "login not successfully try again");
            }
            out.write(json.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
