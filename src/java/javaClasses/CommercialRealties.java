/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaClasses;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
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
    private Resident residentId;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Resident getResident() {
        return residentId;
    }

    public void setResident(Resident residentId) {
        this.residentId = residentId;
    }

     public JSONObject convetToJson(CommercialRealties realties) {
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("id",realties.getId());
            jobj.put("realtyName", realties.getRealtyName());
            jobj.put("licenseNumber", realties.getLicenseNumber());
            jobj.put("description", realties.getDescription());
            jobj.put("resident",realties.getResident());
            jobj.put("category", realties.getCategory().getName());
        } catch (JSONException ex) {
            Logger.getLogger(Realty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jobj;
    }
     public JSONArray displayAllCommercialRealties(ArrayList<CommercialRealties> list) {
        JSONArray jarr = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            jarr.put(convetToJson(list.get(i)));
        }
        return jarr;
    }
  
}
