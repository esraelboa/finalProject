package javaClasses;

public class Marker {

    private double lng;//x
    private double lat;//y
   
    public Marker(){}
    public Marker( double lng,double lat) { 
        this.lng = lng;
        this.lat = lat;
    }
    public void setLng(double lon) {
        this.lng = lon;
    }

    public double getLng() {
        return lng;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

}
