/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaClasses;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**s
 *
 * @author esra1996
 */
public class CommercialRealties {
    private int id; 
    private String realtyName;
    private int licenseNumber;
    private String description;
    private int residentId;
    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealtyName() {
        return realtyName;
    }

    public void setRealtyName(String realtyName) {
        this.realtyName = realtyName;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

   


   
     public JSONObject convetToJson(CommercialRealties realties) {
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("realtyName", realties.getRealtyName());
            jobj.put("licenseNumber", realties.getLicenseNumber());
            jobj.put("description", realties.getDescription());
            jobj.put("residentId",realties.getResidentId());
            jobj.put("categoryId", realties.getCategoryId());
        } catch (JSONException ex) {
            Logger.getLogger(Realty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jobj;
    }
}
