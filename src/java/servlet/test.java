/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DataBase.CategoryDAO;
import DataBase.CommercialRealtiesDAO;
import static DataBase.CommercialRealtiesDAO.checklicenseNumber;
import DataBase.RealtyDAO;
import java.io.IOException;
import javaClasses.Category;
import javaClasses.CommercialRealties;
import javaClasses.Marker;
import javaClasses.Realty;
import javaClasses.Resident;
import javaClasses.User;
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
        Realty realty = new Realty();
        Marker marker = new Marker();
        Resident resident = new Resident();
        CommercialRealties crealty = new CommercialRealties();
        int realtyid = 0;
        try {

            HttpSession session = request.getSession();
            if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {
                json.put("key", -1);
                json.put("message", "invalided session");
            } else {
                //get all data 
                User user = (User) session.getAttribute("user");
                realty.setRealtyNumber(Integer.parseInt(request.getParameter("realtyNumber")));
                marker.setLng(Double.parseDouble(request.getParameter("lng")));
                marker.setLat(Double.parseDouble(request.getParameter("lat")));
                realty.setPosition(marker);
                realty.setOwnerid(user.getId());
                realty.setDescription(request.getParameter("description"));
                resident.setOwnerId(user.getId());
                resident.setResidentId(user.getId());
                resident.setAddress(request.getParameter("address"));
                resident.setRealtyType(1);
                crealty.setRealtyName(request.getParameter("realtyName"));
                crealty.setLicenseNumber(Integer.parseInt(request.getParameter("licenseNumber")));
                crealty.setDescription(request.getParameter("CrDescription"));
                Category category = new Category();
                category.setCatId(Integer.parseInt(request.getParameter("categoryId")));
                crealty.setCategory(category);
                //check data
                if (checklicenseNumber(crealty.getLicenseNumber()) && RealtyDAO.checkRealtyNumber(realty)) {
                    realtyid = RealtyDAO.insertRealty(realty);

                    if (realtyid != 0) {
                        resident.setRealtyId(realtyid);
                        crealty.setResident(resident);
                        int id = CommercialRealtiesDAO.insertCommercialRealties(crealty);

                        if (id != 0) {
                            json.put("key", 1);
                            json.put("message", "realty inserted successfully");
                            json.put("id", id);
                        } else {
                            json.put("key", 0);
                            json.put("message", "error try again");
                        }
                    } else {
                        json.put("key", -2);
                        json.put("message", "Realty Number Douplicated");
                    }
                } else {
                    json.put("key", -3);
                    json.put("message", "Douplicate License Number,error try again");
                }

            }

            response.getWriter().write(json.toString());

        } catch (Exception ex) {
            System.out.println(ex.toString());

        }
//            HttpSession session = request.getSession(false);
//            if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {
//                json.put("key", -1);
//                json.put("message", "invalided session");
//            } else {
//                String name = request.getParameter("name");
//                int id = CategoryDAO.insertCategory(name);
//                if (id > 0) {
//                    json.put("id", id);
//                    json.put("key", 1);
//                    json.put("message", "Category inserted successfully");
//                } else {
//                    json.put("key", 0);
//                    json.put("message", "error ,not exites address");
//                }
//            }
//            response.getWriter().write(json.toString());

//           int i= ResidentDAO.updateResidentInfo("cat cat cat ",5);
//           json.put("i", i);
//   ArrayList<Realty> l= CommercialRealtiesDAO.searchForAddress(request.getParameter("address"));
//                if (l.size() > 0) {
//                    JSONArray ja = new Realty().displayAllRealty(l);
//                    json.put("realties", ja);
//                } else {
//                    json.put("key", 0);
//                    json.put("message", "error ,not exites address");
//                }
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
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
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
