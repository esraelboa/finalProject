package javaClasses;

public class Marker {

    private double lng;//x
    private double lat;//y
   
    public Marker(){}
    public Marker(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
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
