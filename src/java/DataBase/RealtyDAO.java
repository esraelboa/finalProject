package DataBase;

import static DataBase.PostgreSql.getConnection;
import java.sql.*;
import java.util.ArrayList;
import javaClasses.Marker;
import javaClasses.Realty;

public class RealtyDAO {

    public static Boolean checkRealtyNumber(Realty realty) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "select realtynumber from realty where realtynumber=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, realty.getRealtyNumber());
        ResultSet rs = pstmt.executeQuery();
        return !rs.next();
    }

    public static Boolean checkUserRealtyCount(Realty realty) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "select COUNT(*) as count from realty where ownerid=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, realty.getOwnerid());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt("count") < 5;
    }

    public static int insertRealty(Realty realty) throws Exception {
        Connection c = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        PreparedStatement pstmt4 = null;
        String address;
        int id = 0;
        c = PostgreSql.getConnection();
        Marker marker = realty.getPosition();
        double lng = marker.getLng();
        double lat = marker.getLat();
// gets number of points in avaliable location where system allow it (Now in Tripoli )
        String getAddress = "select String_AGG(no::text,'.') as Address ,count(no) from "
                + "(select no from citycoor where ST_Contains(citycoor.coor, St_setsrid(St_Point(?,?),4326)) "
                + " order by fence_type_no ) as subquery ";
        pstmt1 = c.prepareStatement(getAddress);
        pstmt1.setDouble(1, lng);
        pstmt1.setDouble(2, lat);
        ResultSet rs = pstmt1.executeQuery();
        rs.next();
        //get address from point number
        address = rs.getString("Address");
        int count = rs.getInt("Count");
        rs.close();
           c.setAutoCommit(false);
        //check if loction avaliable 
        if ((address != null && count == 3) && checkRealtyNumber(realty) && checkUserRealtyCount(realty)) {
            //update counter
            String updateCounter = "update citycoor \n"
                    + "set counter=counter+1\n"
                    + "where ST_Contains(citycoor.coor, St_setsrid(St_Point(?,?),4326));";
            pstmt2 = c.prepareStatement(updateCounter);
            pstmt2.setDouble(1, lng);
            pstmt2.setDouble(2, lat);
            pstmt2.executeUpdate();
            //select counter
            String getUpdatedCounter = "select counter from citycoor \n"
                    + "where  ST_Contains(citycoor.coor, St_setsrid(St_Point(?,?),4326)) and fence_type_no=3;";
            pstmt3 = c.prepareStatement(getUpdatedCounter);
            pstmt3.setDouble(1, lng);
            pstmt3.setDouble(2, lat);
            ResultSet rs2 = pstmt3.executeQuery();
            rs2.next();
            int counter = rs2.getInt("counter");
            rs.close();
            //marge both point number and counter 
            realty.setAddress(address + "." + counter);
            //insert realty
            String insertRealty = "insert into realty(realtynumber,position,ownerid,address,description) \n"
                    + "values(?,st_setsrid(St_Point(?,?),4326),?,?,?);";
            pstmt4 = c.prepareStatement(insertRealty, Statement.RETURN_GENERATED_KEYS);
            pstmt4.setInt(1, realty.getRealtyNumber());
            pstmt4.setDouble(2, lng);
            pstmt4.setDouble(3, lat);
            pstmt4.setInt(4, realty.getOwnerid());
            pstmt4.setString(5, realty.getAddress());
            pstmt4.setString(6, realty.getDescription());
            pstmt4.executeUpdate();
            ResultSet rsu = pstmt4.getGeneratedKeys();
            rsu.next();
            id = rsu.getInt(1);
            c.commit();
            rsu.close();
            c.close();
            pstmt1.close();
            pstmt2.close();
            pstmt3.close();
            pstmt4.close();
        } else {
            c.rollback();
            System.out.println("loction is not avaliable ");
        }

        return id;
    }

    public static Realty getRealtyinfo(int realtyid) throws Exception {
        Realty realty = null;
        Connection c = PostgreSql.getConnection();
        String sql = "select realtynumber,St_x(position) as lng, st_y(position) as lat,address,description"
                + " from realty where id=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, realtyid);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            realty = new Realty();
            realty.setId(realtyid);
            realty.setRealtyNumber(rs.getInt("realtynumber"));
            Marker marker = new Marker(rs.getDouble("lng"), rs.getDouble("lat"));
            realty.setPosition(marker);
            realty.setAddress(rs.getString("address"));
            realty.setDescription(rs.getString("description"));
            return realty;
        }
        rs.close();
        pstmt.close();
        c.close();
        return realty;
    }

    public static Realty searchForAddress(String address) throws Exception {
        Realty realty = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection c = PostgreSql.getConnection();
        String sql = "select id, st_X(position) as lng , st_Y(position) as lat , description \n"
                + "from realty where address =?";
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

    public static ArrayList<Realty> getAllRealty(int ownerId) throws Exception {
        ArrayList<Realty> list = new ArrayList<>();
        Connection c = getConnection();
        String sql = "select id,realtynumber,St_x(position) as lng, st_y(position) as lat"
                + ",address,description from realty where ownerid=?";

        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, ownerId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Realty realty = new Realty();
            realty.setId(rs.getInt("id"));
            realty.setRealtyNumber(rs.getInt("realtynumber"));
            Marker marker = new Marker(rs.getDouble("lng"), rs.getDouble("lat"));
            realty.setPosition(marker);
            realty.setAddress(rs.getString("address"));
            realty.setDescription(rs.getString("description"));
            list.add(realty);
        }
        rs.close();
        pstmt.close();
        c.close();

        return list;
    }

    public static boolean updateRealtyinfo(Realty realty) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs;
        Connection c = PostgreSql.getConnection();
        String sql = "UPDATE public.realty SET description=? WHERE id=?";
        boolean rowUpdated = false;

        pstmt = c.prepareStatement(sql);
        pstmt.setString(1, realty.getDescription());
        pstmt.setInt(2, realty.getId());
        rowUpdated = pstmt.executeUpdate() > 0;
        pstmt.close();
        c.close();

        return rowUpdated;
    }

    public static boolean checkSubAddressCounter(int realtyid) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "select count(id) from resident where realtyid=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, realtyid);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt("count") <= 0;
    }

    public static boolean deleteRealty(int realtyid) throws Exception {
        boolean deletedRows=false;
        Connection c = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        c = PostgreSql.getConnection();
        c.setAutoCommit(false);
        String deleteFromCr = "delete from commercialrealties using resident\n"
                + "where resident.id=commercialrealties.residentid and resident.realtyid=?";
        pstmt1 = c.prepareStatement(deleteFromCr);
        pstmt1.setInt(1, realtyid);
        int ef1 = pstmt1.executeUpdate();
        System.out.println(ef1);
        String deleteFromResident = "delete from resident \n"
                + "where realtyid=?";
        pstmt2 = c.prepareStatement(deleteFromResident);
        pstmt2.setInt(1, realtyid);
        int ef2 = pstmt2.executeUpdate();
                System.out.println(ef2);
        String deleteFromRealty = "delete from realty where id=?";
        pstmt3 = c.prepareStatement(deleteFromRealty);
        pstmt3.setInt(1, realtyid);
        int ef3 = pstmt3.executeUpdate();
                System.out.println(ef3);
        if((ef1>0 && ef2>0 &&ef3>0)||(ef2>0 &&ef3>0)||(ef3>0)){
          deletedRows=true;
          c.commit();
        }else{
        c.rollback();
        }
        return deletedRows;
    }
}
