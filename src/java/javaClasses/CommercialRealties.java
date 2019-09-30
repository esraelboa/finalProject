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

/**
 *
 * @author esra1996
 */
public class CommercialRealties {
      
    private String realtyNanme;
    private int licenseNumber;
    private String description;
    private String img_Rea;
    private int categoryId;

    public String getRealtyNanme() {
        return realtyNanme;
    }

    public void setRealtyNanme(String realtyNanme) {
        this.realtyNanme = realtyNanme;
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

    public String getImg_Rea() {
        return img_Rea;
    }

    public void setImg_Rea(String img_Rea) {
        this.img_Rea = img_Rea;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
     public JSONObject convetToJson(CommercialRealties realties) {
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("realtyName", realties.getRealtyNanme());
            jobj.put("licenseNumber", realties.getLicenseNumber());
            jobj.put("description", realties.getDescription());
            jobj.put("img_Rea", realties.getImg_Rea());
            jobj.put("categoryId", realties.getCategoryId());
        } catch (JSONException ex) {
            Logger.getLogger(Realty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jobj;
    }
}
