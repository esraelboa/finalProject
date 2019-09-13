package javaClasses;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author esra
 */
public class Realty {

    private int id;
    private int realtyNumber;
    private Marker position;
    private int ownerid;
    private String address;
    private String description;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRealtyNumber(int realtyNumber) {
        this.realtyNumber = realtyNumber;
    }

    public int getRealtyNumber() {
        return realtyNumber;
    }

    public void setPosition(Marker position) {
        this.position = position;
    }

    public Marker getPosition() {
        return position;
    }

    public int getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(int ownerid) {
        this.ownerid = ownerid;
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
    public JSONObject convetToJson(Realty realty) {
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("realtyid", realty.getId());
            jobj.put("realtynumber", realty.getRealtyNumber());
            jobj.put("lng", realty.getPosition().getLng());
            jobj.put("lat", realty.getPosition().getLng());
            jobj.put("address", realty.getAddress());
            jobj.put("description", realty.getDescription());
        } catch (JSONException ex) {
            Logger.getLogger(Realty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jobj;
    }
}
