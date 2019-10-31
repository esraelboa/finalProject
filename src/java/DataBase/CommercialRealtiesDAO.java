/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javaClasses.Category;
import javaClasses.CommercialRealties;
import javaClasses.Marker;
import javaClasses.Realty;

/**
 *
 * @author esra1996
 */
public class CommercialRealtiesDAO {

    public static Boolean checklicenseNumber(CommercialRealties commercialrealties) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "select licenseNumber from commercialRealties where licenseNumber=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, commercialrealties.getLicenseNumber());

        ResultSet rs = pstmt.executeQuery();
        return !rs.next();
    }

    public static int insertCommercialRealties(CommercialRealties commercialrealties) throws Exception {
        int id = 0;

        Connection con = PostgreSql.getConnection();

        if (checklicenseNumber(commercialrealties)) {

            String sql = "insert  into commercialrealties(realtyname, licensenumber, description, residentid,categoryid) VALUES (?,?,?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, commercialrealties.getRealtyName());
            pstmt.setInt(2, commercialrealties.getLicenseNumber());
            pstmt.setString(3, commercialrealties.getDescription());
            pstmt.setInt(4, commercialrealties.getResidentId());
            pstmt.setInt(5, commercialrealties.getCategoryId());

            pstmt.executeUpdate();
            ResultSet rsu = pstmt.getGeneratedKeys();
            rsu.next();
            id = rsu.getInt(1);

        } else {
            System.out.println("error");

        }
        return id;
    }

    public static ArrayList<Realty> searchForAddress(String address) throws Exception {
        ArrayList<Realty> list = new ArrayList<>();
        Realty realty = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection c = PostgreSql.getConnection();
        String sql = "select realtyname, commercialrealties.id,St_x(realty.position) as lng,St_y(realty.position) as lat from commercialrealties \n"
                + "inner join resident on commercialrealties.residentid=resident.id\n"
                + "inner join realty on realty.id=resident.realtyid\n"
                + "where commercialrealties.realtyname like ? ";
        pstmt = c.prepareStatement(sql);
        pstmt.setString(1,"%"+address+"%");
        rs = pstmt.executeQuery();
        while(rs.next()) {
            Marker marker = new Marker();
            realty = new Realty();
            realty.setId(rs.getInt("id"));
            marker.setLng(rs.getDouble("lng"));
            marker.setLat(rs.getDouble("lat"));
            realty.setPosition(marker);
            realty.setDescription(rs.getString("realtyname"));
            list.add(realty);
        }
        c.close();

        return list;
    }
//            public static CommercialRealties displayCommercialRealtiesInfo(int CommercialRealtiesid) throws Exception {
//        CommercialRealties CommercialRealties = null;
//        Category category=null;
//        
//        Connection c = PostgreSql.getConnection();
//        String sql = "select commercialrealties.realtyname,category.name,commercialrealties.description from commercialrealties\n" +
//"inner join category on category.catid = commercialRealties.id";
//        PreparedStatement pstmt = c.prepareStatement(sql);
//        pstmt.setInt(1, CommercialRealtiesid);
//        ResultSet rs = pstmt.executeQuery();
//        while (rs.next()) {
//        //    CommercialRealties = new CommercialRealties();
//          CommercialRealties.setRealtyName(rs.getString("realtyName"));
//          category.setName(rs.getString("name"));
//          CommercialRealties.setDescription(rs.getString("description"));
//            
//        }
//        return CommercialRealties;
//    }
}
