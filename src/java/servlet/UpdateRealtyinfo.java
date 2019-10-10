
package servlet;

import DataBase.RealtyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClasses.Realty;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

public class UpdateRealtyinfo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Realty realty = new Realty();
        JSONObject json = new JSONObject();

//             try {
//            HttpSession session = request.getSession(false);
//            if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {
//                json.put("key", -1);
//                json.put("message", "invalided session");
//            } else {
//            int id = Integer.parseInt(request.getParameter("id"));
//            realty.setId(id);   
////            realty.setDescription(request.getParameter("description"));
//            realty.setDescription(request.getParameter("description"));
//             String dis = request.getParameter("description");
//            int effectedRow = RealtyDAO.updateeditRealtyinfo(dis , id);
////            json = realty.convetToJson(realty);
////            json.toString();
////            out.write(json.toString());
////            json.put("dis", realty.getDescription());
////            json.put("dis", "realty.getDescription()");
////            out.write(json.toString()); 
//            if (effectedRow > 0) {
////               // json = realty.convetToJson(realty);
////                json.put("id",request.getParameter("id") );
////                json.put("dis",request.getParameter("description") );
// json.put("id",request.getParameter("id") );
//                json.put("dis",request.getParameter("description") );
//                json.put("key", 1);
//                json.put("message", "تم التعديل");
//            } else {
//                json.put("key", 0);
//                json.put("message", "حدث خطأ في التعديل اعد المحاوله مره اخري");
//            }
//            response.getWriter().write(json.toString());
//            }} catch (Exception ex) {
//            Logger.getLogger(DisplayRealtyinfo.class.getName()).log(Level.SEVERE, null, ex);
//        }

   try {
        HttpSession session = request.getSession(false);
            if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {
                json.put("key", -1);
                json.put("message", "invalided session");
            } else {
            int id = Integer.parseInt(request.getParameter("id"));
            realty.setId(id);
           
//            realty.setDescription(request.getParameter("description"));
            realty.setDescription(request.getParameter("description"));
             String dis = request.getParameter("description");
            boolean b = RealtyDAO.updateeditRealtyinfo(realty);
//            json = realty.convetToJson(realty);
//            json.toString();
//            out.write(json.toString());
//            json.put("dis", realty.getDescription());
//            json.put("dis", "realty.getDescription()");
//            out.write(json.toString()); 
            if (b == true) {
               // json = realty.convetToJson(realty);
                json.put("id",request.getParameter("id") );
                json.put("dis",request.getParameter("description") );
                json.put("key", 1);
                json.put("message", "تم التعديل");
            } else {
                json.put("key", 0);
                json.put("message", "حدث خطأ في التعديل اعد المحاوله مره اخري");
            }
            response.getWriter().write(json.toString());
        }} catch (Exception ex) {
            Logger.getLogger(DisplayRealtyinfo.class.getName()).log(Level.SEVERE, null, ex);
        }}


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
