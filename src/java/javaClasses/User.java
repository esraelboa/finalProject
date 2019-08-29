
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
    
     int id;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String password;

    PostgreSql c = new PostgreSql();
    Statement stmt;
    ResultSet rs;
    Connection con;  

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

    public PostgreSql getC() {
        return c;
    }

    public void setC(PostgreSql c) {
        this.c = c;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
      public List<User> getUsers() {        
        List<User> list = new ArrayList<User>();
        try {

            con = c.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM userr");

            while (rs.next()) {
                
                User user = new User();
                
                user.setId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setEmail(rs.getString(1));
                user.setUserPassword(rs.getString(2));
                list.add(user);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
      
      
       public User get_email_password(String email , String password){
        User us = null;
        PreparedStatement ps;
        try {
            con = c.getConnection();
            ps = con.prepareStatement("SELECT id , firstname ,lastname , phonenumber FROM public.userr WHERE email=? and passwordd=?;");
            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                us = new User();

                us.setId(rs.getInt(1));
                us.setFirstName(rs.getString(2));
                us.setLastName(rs.getString(3));
                us.setPhoneNumber(rs.getString(4));
//                us.setEmail(rs.getString(5));
//                us.setUserPassword(rs.getString(6));                
            }

            con.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }
       
       
    public int insertuser(User b) throws ClassNotFoundException, SQLException {
        int st = 0;
        int id = 0;
        con = c.getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO public.userr(firstname, lastname, phonenumber, email, passwordd)VALUES (?, ?, ?,?, ?) RETURNING id;");

        ps.setString(1, b.firstName);
        ps.setString(2, b.lastName);
        ps.setString(3, b.phoneNumber);
        ps.setString(4, b.email);
        ps.setString(5, b.password);


        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
        con.close();
        return id;
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
