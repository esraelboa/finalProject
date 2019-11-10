/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import static DataBase.PostgreSql.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javaClasses.Marker;
import javaClasses.Realty;
import javaClasses.Resident;

/**
 *
 * @author esra
 */
public class ResidentDAO {

    public static int checkResidentEmail(String email) throws Exception {
        int id = 0;
        Connection c = PostgreSql.getConnection();
        String sql = "select id from userr where email=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

//    public static Boolean checkResidentIdCount(int residentid) throws Exception {
//        Connection c = PostgreSql.getConnection();
//        String sql = "select count(id )from resident where residentid=?";
//        PreparedStatement pstmt = c.prepareStatement(sql);
//        pstmt.setInt(1, residentid);
//        ResultSet rs = pstmt.executeQuery();
//        rs.next();
//        return rs.getInt("count") == 0;
//    }
    public static Boolean checkResidentAddress(String address) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "select * from resident where address=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setString(1, address);
        ResultSet rs = pstmt.executeQuery();
        return !rs.next();
    }

    public static int insertResident(Resident resident) throws Exception {
        int id = 0;
        Connection c = PostgreSql.getConnection();
        //get RealtyAddress
        String getRealtyAddress = "select address from realty where id=?";
        PreparedStatement pstmt1 = c.prepareStatement(getRealtyAddress);
        pstmt1.setInt(1, resident.getRealtyId());
        ResultSet rs = pstmt1.executeQuery();
        if (rs.next()) {
            String address = rs.getString("address");
            resident.setAddress(address + "," + resident.getAddress());
            if (checkResidentAddress(resident.getAddress())) {
                //insert Resident query
                String insertResident = "insert into resident(ownerid,realtyid,residentid,address,realtytype) values(?,?,?,?,?);";
                PreparedStatement pstmt2 = c.prepareStatement(insertResident, Statement.RETURN_GENERATED_KEYS);
                pstmt2.setInt(1, resident.getOwnerId());
                pstmt2.setInt(2, resident.getRealtyId());
                pstmt2.setInt(3, resident.getResidentId());
                pstmt2.setString(4, resident.getAddress());
                pstmt2.setInt(5, resident.getRealtyType());
                pstmt2.executeUpdate();
                ResultSet rsu = pstmt2.getGeneratedKeys();
                rsu.next();
                id = rsu.getInt(1);
            }
        }
        return id;
    }

    public static Resident displayResidentRealtyInfo(int residentid) throws Exception {
        Resident resident = null;
        Connection c = PostgreSql.getConnection();
        String sql = "select resident.id,resident.address,resident.description\n"
                + "from resident where residentid=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, residentid);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            resident = new Resident();
            resident.setId(rs.getInt("id"));
            resident.setAddress(rs.getString("address"));
            resident.setDescription(rs.getString("description"));
        }
        return resident;
    }

    public static int updateResidentInfo(String description, int id) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "update resident\n"
                + "set description=?\n"
                + "where id=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setString(1, description);
        pstmt.setInt(2, id);
        int effectedRows = pstmt.executeUpdate();
        return effectedRows;

    }
    
    public static  ArrayList<Resident> getAllRealtyResidents(int realtyId) throws Exception{
      ArrayList<Resident> list = new ArrayList<>();
        Connection c = getConnection();
        String sql = "select id,address,description,realtytype from resident where realtyid=?";

        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, realtyId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
           Resident resident=new Resident();
            resident.setId(rs.getInt("id"));
            resident.setAddress(rs.getString("address"));
            resident.setDescription(rs.getString("description"));
            resident.setRealtyType(rs.getInt("realtytype"));
            list.add(resident);
        }
        rs.close();
        pstmt.close();
        c.close();
        return list;
    }
    
    public static Realty searchForAddress(String address) throws Exception {
        Realty realty = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection c = PostgreSql.getConnection();
        String sql = "select resident.id,st_x(realty.position) as lng , st_y(realty.position) as lat , resident.description\n"
                + "from realty inner join resident on realty.id=resident.realtyid\n"
                + "where resident.address like ?";
        pstmt = c.prepareStatement(sql);
        pstmt.setString(1, address);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            Marker marker = new Marker();
            realty = new Realty();
            realty.setId(rs.getInt("id"));
            marker.setLng(rs.getDouble("lng"));
            marker.setLat(rs.getDouble("lat"));
            realty.setPosition(marker);
            realty.setDescription(rs.getString("description"));
        }
        c.close();

        return realty;
    }
}
