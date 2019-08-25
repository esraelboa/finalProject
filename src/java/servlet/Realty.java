package servlet;

/**
 *
 * @author esra
 */
public class Realty {

    private int id;
    private int realtyNumber;
    private Marker position;
    private int ownerid;
    private String residentemail;

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

   

    public void setResidentemail(String residentemail) {
        this.residentemail = residentemail;
    }

    public String getResidentemail() {
        return residentemail;
    }

}
