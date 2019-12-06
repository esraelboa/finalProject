/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import static DataBase.ResidentDAO.checkResidentAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javaClasses.Category;
import javaClasses.CommercialRealties;
import javaClasses.Marker;
import javaClasses.Realty;
import javaClasses.Resident;

/**
 *
 * @author esra1996
 */
public class CommercialRealtiesDAO {

    public static Boolean checklicenseNumber(int licenseNumber) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "select licenseNumber from commercialRealties where licenseNumber=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, licenseNumber);

        ResultSet rs = pstmt.executeQuery();
        return !rs.next();
    }

//    public static int insertCommercialRealties(CommercialRealties commercialrealties) throws Exception {
//        int id = 0;
//
//        Connection con = PostgreSql.getConnection();
//
//        if (checklicenseNumber(commercialrealties.getLicenseNumber())) {
//
//            String sql = "insert  into commercialrealties(realtyname, licensenumber, description, residentid,categoryid) VALUES (?,?,?,?,?);";
//            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            pstmt.setString(1, commercialrealties.getRealtyName());
//            pstmt.setInt(2, commercialrealties.getLicenseNumber());
//            pstmt.setString(3, commercialrealties.getDescription());
//            pstmt.setInt(4, commercialrealties.getResident().getId());
//            pstmt.setInt(5, commercialrealties.getCategory().getCatId());
//
//            pstmt.executeUpdate();
//            ResultSet rsu = pstmt.getGeneratedKeys();
//            rsu.next();
//            id = rsu.getInt(1);
//
//        } else {
//            System.out.println("error");
//
//        }
//        return id;
//    }

    public static int insertCommercialRealties(CommercialRealties commercialrealties) throws Exception {
        Resident resident = commercialrealties.getResident();
        int residentid = 0, CRid = 0;
        Connection c = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        c = PostgreSql.getConnection();
        //genurate Sub-address 
        String getRealtyAddress = "select address from realty where id=?";
        pstmt1 = c.prepareStatement(getRealtyAddress);
        pstmt1.setInt(1, resident.getRealtyId());
        ResultSet rs = pstmt1.executeQuery();
        if (rs.next()) {
            c.setAutoCommit(false);
            String address = rs.getString("address");
            resident.setAddress(address + "," + resident.getAddress());
            //check if there's anther same sub-address
            if (checkResidentAddress(resident.getAddress())) {
                //insert Resident query
                String insertResident = "insert into resident(ownerid,realtyid,residentid,address,realtytype) values(?,?,?,?,?);";
                pstmt2 = c.prepareStatement(insertResident, Statement.RETURN_GENERATED_KEYS);
                pstmt2.setInt(1, resident.getOwnerId());
                pstmt2.setInt(2, resident.getRealtyId());
                pstmt2.setInt(3, resident.getResidentId());
                pstmt2.setString(4, resident.getAddress());
                pstmt2.setInt(5, resident.getRealtyType());
                pstmt2.executeUpdate();
                ResultSet rsu1 = pstmt2.getGeneratedKeys();
                rsu1.next();
                residentid = rsu1.getInt(1);
                //insert CR query      
                String sql = "insert  into commercialrealties(realtyname, licensenumber, description, residentid,categoryid) VALUES (?,?,?,?,?);";
                PreparedStatement pstmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, commercialrealties.getRealtyName());
                pstmt.setInt(2, commercialrealties.getLicenseNumber());
                pstmt.setString(3, commercialrealties.getDescription());
                pstmt.setInt(4, residentid);
                pstmt.setInt(5, commercialrealties.getCategory().getCatId());
                pstmt.executeUpdate();
                ResultSet rsu = pstmt.getGeneratedKeys();
                rsu.next();
                CRid = rsu.getInt(1);
                c.commit();

            } else {
                System.out.println("error in address");
                c.rollback();
            }
        }
        return CRid;
    }

    public static ArrayList<Realty> searchForAddress(String address) throws Exception {
        ArrayList<Realty> list = new ArrayList<>();
        Realty realty = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection c = PostgreSql.getConnection();
        String sql = "select  commercialrealties.id,realtyname,St_x(realty.position) as lng,St_y(realty.position) as lat from commercialrealties \n"
                + "inner join resident on commercialrealties.residentid=resident.id\n"
                + "inner join realty on realty.id=resident.realtyid\n"
                + "where commercialrealties.realtyname like ? ";
        pstmt = c.prepareStatement(sql);
        pstmt.setString(1, "%" + address + "%");
        rs = pstmt.executeQuery();
        while (rs.next()) {
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

    public static CommercialRealties displayCommercialRealtyInfo(int id) throws Exception {
        CommercialRealties commercialRealties = null;

        Connection c = PostgreSql.getConnection();
        String sql = "select commercialrealties.id,commercialrealties.realtyname,licensenumber,category.name,commercialrealties.description from commercialrealties\n"
                + "inner join category on category.catid = commercialRealties.categoryid where commercialRealties.id=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            commercialRealties = new CommercialRealties();
            commercialRealties.setId(rs.getInt("id"));
            commercialRealties.setRealtyName(rs.getString("realtyname"));
            commercialRealties.setLicenseNumber(rs.getInt("licensenumber"));
            Category category = new Category();
            category.setName(rs.getString("name"));
            commercialRealties.setCategory(category);
            commercialRealties.setDescription(rs.getString("description"));
        }
        return commercialRealties;
    }

    public static Marker getCommercialRealtyPosition(int id) throws Exception {
        Connection c = PostgreSql.getConnection();
        Marker marker = null;
        String sql = "select  st_x(realty.position) as lng,St_y(realty.position) as lat from commercialrealties \n"
                + "inner join resident on commercialrealties.residentid=resident.id\n"
                + "inner join realty on realty.id=resident.realtyid\n"
                + "where commercialrealties.id=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            marker = new Marker();
            marker.setLng(rs.getDouble("lng"));
            marker.setLat(rs.getDouble("lat"));
        }
        return marker;
    }

    public static ArrayList<CommercialRealties> getAllCommercialRealties(int residentId) throws Exception {
        ArrayList<CommercialRealties> list = new ArrayList<>();
        Connection c = PostgreSql.getConnection();
        String sql = "select  commercialrealties.id,realtyname,licensenumber,commercialrealties.description from commercialrealties\n"
                + "inner join resident on resident.id=commercialrealties.residentid \n"
                + "where resident.residentid=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, residentId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            CommercialRealties commercialRealties = new CommercialRealties();
            commercialRealties.setId(rs.getInt("id"));
            commercialRealties.setRealtyName(rs.getString("realtyname"));
            commercialRealties.setLicenseNumber(rs.getInt("licensenumber"));
            commercialRealties.setDescription(rs.getString("description"));
            list.add(commercialRealties);
        }
        return list;
    }

    public static ArrayList<Realty> getAllCommercialRealtiesByCategory(int catId) throws Exception {
        ArrayList<Realty> list = new ArrayList<>();
        Realty realty = null;
        Connection c = PostgreSql.getConnection();
        String sql = "select  commercialrealties.id,realtyname,St_x(realty.position) as lng,St_y(realty.position) as lat from commercialrealties \n"
                + "                inner join resident on commercialrealties.residentid=resident.id\n"
                + "                inner join realty on realty.id=resident.realtyid\n"
                + "                inner join category on category.catid = commercialRealties.categoryid where category.catid=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, catId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Marker marker = new Marker();
            realty = new Realty();
            realty.setId(rs.getInt("id"));
            marker.setLng(rs.getDouble("lng"));
            marker.setLat(rs.getDouble("lat"));
            realty.setPosition(marker);
            realty.setDescription(rs.getString("realtyname"));
            list.add(realty);
        }
        return list;
    }

    public static int updateCommercialRealtyInfo(CommercialRealties realty) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "Update commercialrealties\n"
                + "set realtyname=?,\n"
                + "    description=?,\n"
                + "    categoryid=?\n"
                + "where id=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setString(1, realty.getRealtyName());
        pstmt.setString(2, realty.getDescription());
        pstmt.setInt(3, realty.getCategory().getCatId());
        pstmt.setInt(4, realty.getId());
        int effectedRows = pstmt.executeUpdate();
        return effectedRows;
    }
}
