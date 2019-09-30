package javaClasses;

import DataBase.PostgreSql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {

  private  int id;
  private  String firstName;
  private  String lastName;
  private  String phoneNumber;
  private  String email;
  private  String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String userPassword) {
        this.password = userPassword;
    }

    public JSONObject jsonobject(User ojb) {
        JSONObject json = new JSONObject();
        try {
            json.put("user id", ojb.id);
            json.put("first name", ojb.firstName);
            json.put("last name", ojb.lastName);
            json.put("phone number", ojb.phoneNumber);
            json.put("email", ojb.email);
            json.put("password", ojb.password);
        } catch (JSONException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONArray array = new JSONArray();
        array.put(json);
        return json;
    }

    public JSONArray arrayjson(List<User> list) {

        JSONArray array = new JSONArray();
        JSONObject json = null;
        for (User us : list) {
            json = jsonobject(us);
            array.put(json);
        }
        return array;
    }

}
