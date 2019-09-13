package DataBase;

import java.sql.*;
import javaClasses.Marker;
import javaClasses.Realty;

/**
 *
 * @author esra
 */
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
                + "(select no from citycoor where ST_Contains(citycoor.coor, St_setsrid(St_Point(?,?),4326))  order by fence_type_no ) as subquery ";

        pstmt1 = c.prepareStatement(getAddress);
        pstmt1.setDouble(1, lng);
        pstmt1.setDouble(2, lat);
        ResultSet rs = pstmt1.executeQuery();
        rs.next();
        //get address from point number
        address = rs.getString("Address");
        int count = rs.getInt("Count");
        System.out.println(count);
        rs.close();
        //check if loction avaliable 
        if ((address != null && count == 3) && checkRealtyNumber(realty) && checkUserRealtyCount(realty)) {

            //update counter
            c.setAutoCommit(false);
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
            System.out.println(id);
            System.out.println("address : " + address + "." + counter);
            rsu.close();
            c.close();
            pstmt1.close();
            pstmt2.close();
            pstmt3.close();
            pstmt4.close();
        } else {
            System.out.println("loction is not avaliable ");
        }

        return id;
    }

    public static Realty getRealtyinfo(int realtyid) throws Exception {
        Realty realty = new Realty();
        Connection c = PostgreSql.getConnection();
        String sql = "select realtynumber,St_x(position) as lng, st_y(position) as lat,address,description from realty where id=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, realtyid);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            realty.setId(realtyid);
            realty.setRealtyNumber(rs.getInt("realtynumber"));
            Marker marker = new Marker(rs.getDouble("lng"), rs.getDouble("lat"));
            realty.setPosition(marker);
            realty.setAddress(rs.getString("address"));
            realty.setDescription(rs.getString("description"));
        }
        rs.close();
        pstmt.close();
        c.close();
        return realty;
    }

    public static Realty searchForAddress(String address) throws Exception {
        Realty realty = null;
        Marker marker = new Marker();
        Connection c = PostgreSql.getConnection();
        String sql = "select st_X(position) as lng , st_Y(position) as lat , description \n"
                + "from realty where address =?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setString(1, address);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            marker.setLng(rs.getDouble("lng"));
            marker.setLat(rs.getDouble("lat"));
            realty.setPosition(marker);
            realty.setDescription(rs.getString("description"));
        }
        return realty;
    }
}
