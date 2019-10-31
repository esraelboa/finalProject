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
 * @author esra1996
 */
public class Category {

    private int catId;
    private String name;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject convertToJson(Category category) {
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("catid", category.getCatId());
            jobj.put("name", category.getName());
        } catch (JSONException ex) {

            System.out.println(ex.getMessage());
        }

        return jobj;
    }

    public JSONArray convertCitiestoJsonArry(ArrayList<Category> category) {
        JSONArray jarr = new JSONArray();
        for (int i = 0; i < category.size(); i++) {
            jarr.put(convertToJson(category.get(i)));
        }
        return jarr;
    }

}
