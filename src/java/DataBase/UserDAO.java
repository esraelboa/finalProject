/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javaClasses.User;

/**
 *
 * @author esra
 */
public class UserDAO {

    public static int insertUser(User user) throws Exception {
        int st = 0;
        int id = 0;
        Connection con = PostgreSql.getConnection();
        String sql = "INSERT INTO userr(firstname, lastname, phonenumber, email, passwordd,isadmin)VALUES (?, ?, ?,?, ?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, user.getFirstName());
        pstmt.setString(2, user.getLastName());
        pstmt.setString(3, user.getPhoneNumber());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getUserPassword());
        pstmt.setBoolean(6, false);

        st = pstmt.executeUpdate();
        if (st > 0) {
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            rs.close();
            System.out.println("User inserted succsfully : " + id);
        }
        pstmt.close();
        con.close();

        return id;
    }

    public static List<User> getUsers() throws Exception {
        List<User> list = new ArrayList<User>();
        Connection con = PostgreSql.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM userr");

        while (rs.next()) {

            User user = new User();

            user.setId(rs.getInt(1));
            user.setFirstName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setPhoneNumber(rs.getString(4));
            user.setEmail(rs.getString(1));
            user.setUserPassword(rs.getString(2));
            user.setIsAdmin(rs.getBoolean(5));
            list.add(user);
        }

        return list;
    }

    public static User getUserInfo(int id) throws Exception {
        User user = null;
        Connection c = PostgreSql.getConnection();
        String sql = "select id,firstname,lastname,passwordd from userr where id=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setUserPassword(rs.getString("passwordd"));
        }
        return user;
    }

    public static User login(String email, String password) throws Exception {
        User us = null;
        PreparedStatement ps;
        ResultSet rs;

        Connection con = PostgreSql.getConnection();
        ps = con.prepareStatement("SELECT id , firstname ,lastname , phonenumber,isadmin FROM userr WHERE email=? and passwordd=?;");
        ps.setString(1, email);
        ps.setString(2, password);

        rs = ps.executeQuery();
        if (rs.next()) {
            us = new User();
            us.setId(rs.getInt(1));
            us.setFirstName(rs.getString(2));
            us.setLastName(rs.getString(3));
            us.setPhoneNumber(rs.getString(4));
            us.setIsAdmin(rs.getBoolean(5));
        }
        con.close();
        return us;
    }

    public static int UpdateUser(int id, String firstName, String lastName) throws Exception {
        User user = new User();
        Connection c = PostgreSql.getConnection();

        String sql = "UPDATE userr SET  firstname=?, lastname=? WHERE id=?;";
        PreparedStatement pstmt = c.prepareStatement(sql);

        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setInt(3, id);
        int effectedRows = pstmt.executeUpdate();
        return effectedRows;
    }

    public static int changeUserPassword(String currentPassword,String password, int id) throws Exception {
        int effectedRows = 0;
        Connection c = PostgreSql.getConnection();
        if (checkUserCurrentPassword(currentPassword, id)) {
            String sql = "UPDATE userr SET  passwordd=? WHERE id=?;";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setInt(2, id);
            effectedRows = pstmt.executeUpdate();
        }
        return effectedRows;
    }

    public static boolean checkUserCurrentPassword(String password, int id) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "select passwordd from userr where passwordd like ? and id =?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setString(1, password);
        pstmt.setInt(2, id);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }

    public static int deleteUser(int id) {
        int status = 0;
        try {
            Connection c = PostgreSql.getConnection();
            String sql = "DELETE FROM userr WHERE id=44;";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, id);
            status = pstmt.executeUpdate();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
