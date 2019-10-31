/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javaClasses.Category;

/**
 *
 * @author esra
 */
public class CategoryDAO {

    public static int insertCategory(String name) throws Exception {
        int id = 0;
        Connection con = PostgreSql.getConnection();
        String sql = "INSERT INTO CATEGORY(NAME) VALUES (?)";
        PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, name);
        if (pstmt.executeUpdate() > 0) {
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            rs.close();
            System.out.println("inserted succsfully : " + id);
        }
        pstmt.close();
        con.close();

        return id;
    }

    public static int updateCategoryName(int catid, String name) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "UPDATE category \n"
                + "set name=?\n"
                + "where catid=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setInt(2, catid);
        int effectedRows = pstmt.executeUpdate();
        return effectedRows;
    }
    public static ArrayList<Category> getAllCatogries() throws Exception {
        ArrayList<Category> list = new ArrayList<>();
        ResultSet rs;
        Connection c = PostgreSql.getConnection();
        String sql = "select catid,name from category";
        PreparedStatement pstmt = c.prepareStatement(sql);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            Category catogray = new Category();
            catogray.setCatId(rs.getInt("catid"));
            catogray.setName(rs.getString("name"));
            list.add(catogray);
        }
        return list;
    }
}
