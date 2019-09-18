/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaClasses.User;

/**
 *
 * @author esra
 */
public class UserDAO {

    public static int insertuser(User user) throws Exception {
        int st = 0;
        int id = 0;
        Connection con = PostgreSql.getConnection();
        String sql = "INSERT INTO userr(firstname, lastname, phonenumber, email, passwordd)VALUES (?, ?, ?,?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, user.getFirstName());
        pstmt.setString(2, user.getLastName());
        pstmt.setString(3, user.getPhoneNumber());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getUserPassword());

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
            list.add(user);
        }

        return list;
    }

    public static User get_email_password(String email, String password) throws Exception {
        User us = null;
        PreparedStatement ps;
        ResultSet rs;

        Connection con = PostgreSql.getConnection();
        ps = con.prepareStatement("SELECT id , firstname ,lastname , phonenumber FROM userr WHERE email=? and passwordd=?;");
        ps.setString(1, email);
        ps.setString(2, password);

        rs = ps.executeQuery();
        if (rs.next()) {
            us = new User();
            us.setId(rs.getInt(1));
            us.setFirstName(rs.getString(2));
            us.setLastName(rs.getString(3));
            us.setPhoneNumber(rs.getString(4));

        }
        con.close();
        return us;
    }
}