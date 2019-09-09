/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaClasses;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author esra
 */
public class City {

    private String name;
    private String coordinate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public JSONObject convertToJson(City city) {
        JSONObject jobj = new JSONObject();
        try {

            jobj.put("name", city.getName());
            jobj.put("coordinate", city.getCoordinate());

        } catch (JSONException ex) {

            System.out.println(ex.getMessage());
        }

        return jobj;
    }

    public JSONArray convertCitiestoJsonArry(ArrayList<City> cities) {
        JSONArray jarr = new JSONArray();
        for (int i = 0; i < cities.size(); i++) {
            jarr.put(convertToJson(cities.get(i)));
        }
        return jarr;
    }

}
