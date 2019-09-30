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

    public static Boolean checkResidentIdCount(int residentid) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "select count(id )from resident where residentid=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, residentid);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt("count") == 0;
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
            //insert Resident query
            String insertResident = "insert into resident(ownerid,realtyid,residentid,address) values(?,?,?,?);";
            PreparedStatement pstmt2 = c.prepareStatement(insertResident, Statement.RETURN_GENERATED_KEYS);
            pstmt2.setInt(1, resident.getOwnerId());
            pstmt2.setInt(2, resident.getRealtyId());
            pstmt2.setInt(3, resident.getResidentId());
            pstmt2.setString(4, resident.getAddress());
            pstmt2.executeUpdate();
            ResultSet rsu = pstmt2.getGeneratedKeys();
            rsu.next();
            id = rsu.getInt(1);
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

    public static int updateResidentInfo(String description,int id) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "update resident\n"
                    + "set description=?\n"
                    + "where id=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setString(1, description);
        pstmt.setInt(2, id);
       int effectedRows= pstmt.executeUpdate();
       return effectedRows;

    }
}