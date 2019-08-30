/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.*;
import javaClasses.Marker;

/**
 *
 * @author esra
 */
public class AddressDAO {

    public static void updateCounter(int realtyid) throws Exception {
        Connection c = PostgreSql.getConnection();
        String sql = "update citycoor\n"
                + "set counter=counter+1\n"
                + "where id in (SELECT CITYCOOR.id FROM CITYCOOR,REALTY"
                + " WHERE ST_Contains(citycoor.coor, realty.\"position\")=true and realty.id=?);";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, realtyid);
        pstmt.executeUpdate();
        pstmt.close();
        c.close();
    }

    public static int generatAddress(int realtyid, String description) throws Exception {
        int id = 0;
        updateCounter(realtyid);
        Connection c = PostgreSql.getConnection();
        String sql = "insert into address(address,description) "
                + "SELECT distinct\n"
                + "concat_ws('-',\n"
                + "  (select citycoor.\"no\"  from citycoor where fence_type_no=1 and ST_Contains(citycoor.coor, realty.\"position\")=true) ,\n"
                + "  (select citycoor.\"no\"  from citycoor where fence_type_no=2 and ST_Contains(citycoor.coor, realty.\"position\")=true) ,\n"
                + "  (select citycoor.\"no\" from citycoor where fence_type_no=3 and ST_Contains(citycoor.coor, realty.\"position\")=true),\n"
                + "  (select counter from citycoor where fence_type_no=3 and ST_Contains(citycoor.coor, realty.\"position\")=true)\n"
                + " ),?\n"
                + "FROM \n"
                + "  public.realty,\n"
                + "  public.citycoor\n"
                + "where \n"
                + "ST_Contains(citycoor.coor, realty.\"position\")=true and realty.id=?;";
        PreparedStatement pstmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, description);
        pstmt.setInt(2, realtyid);

        if (pstmt.executeUpdate() > 0) {
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            rs.close();
        }
        pstmt.close();
        c.close();
        return id;
    }

    public static int realtyAddress(int realtyid, int addressid) throws Exception {
        int id = 0;
        Connection c = PostgreSql.getConnection();
        String sql = "insert into realtyaddress values(?,?);";
        PreparedStatement pstmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, realtyid);
        pstmt.setInt(2, addressid);

        if (pstmt.executeUpdate() > 0) {
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            rs.close();
        }
        pstmt.close();
        c.close();
        return id;
    }

       public static Marker getAddressCoordinate(String address) throws Exception {
        Marker marker = new Marker();
        Connection c = PostgreSql.getConnection();
        String sql = "select st_x(position) as lng , st_y(position) as lat, address.description\n"
                + "from realty inner join realtyaddress on realty.id =realtyid\n"
                + "inner join address on address.id=addressid\n"
                + "where addressid= (select id from address where address=?);";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setString(1, address);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            marker.setLng(rs.getDouble("lng"));
            marker.setLat(rs.getDouble("lat"));
        }

        return marker;
    }

    public static String getAddressDescrption(String address) throws Exception {
        String description = null;
        Connection c = PostgreSql.getConnection();
        String sql = "select description from address where address=?";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setString(1, address);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            description = rs.getString("description");
        }

        return description;
    }

}
