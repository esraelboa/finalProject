package javaClasses;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class Resident {
    
    private int id;
    private int ownerId;
    private int realtyId;
    private int residentId;
    private String address;
    private String description;
    private int realtytype;

//    public boolean isRealtyType() {
//        return realtyType;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getRealtyId() {
        return realtyId;
    }

    public void setRealtyId(int realtyId) {
        this.realtyId = realtyId;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRealtyType() {
        return realtytype;
    }

    public void setRealtyType(int realtytype) {
        this.realtytype = realtytype;
    }

    public JSONObject convetToJson(Resident resident) {
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("id",resident.getId());
            jobj.put("address", resident.getAddress());
            jobj.put("description", resident.getDescription());
            jobj.put("realtytype",resident.getRealtyType());
        } catch (JSONException ex) {
            Logger.getLogger(Realty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jobj;
    }

  
}
