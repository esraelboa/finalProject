package DataBase;

import javaClasses.Realty;
import javaClasses.Marker;
import java.sql.*;

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
        return rs.getInt("count") <= 5;
    }

    public static Boolean checkRealtycoor(Marker marker) throws Exception {

        Connection c = PostgreSql.getConnection();
        String sql = "select no from citycoor where fence_type_no=3 and ST_Contains(citycoor.coor,ST_SetSRID(ST_POINT(?,?),4326))=true";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setDouble(1, marker.getLng());
        pstmt.setDouble(2, marker.getLat());
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }

    public static int insertRealty(Realty realty) throws Exception {
       int id = 0;
        if (checkRealtyNumber(realty) && checkRealtycoor(realty.getPosition())) {
            if (checkUserRealtyCount(realty)) {
                Connection c = PostgreSql.getConnection();
                String sql = "INSERT INTO realty(realtynumber,position,ownerid,residentemail) values(?,ST_SetSRID(ST_POINT(?,?),4326),?,?);";
                PreparedStatement pstmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, realty.getRealtyNumber());
                Marker marker = realty.getPosition();
                pstmt.setDouble(2, marker.getLng());//x
                pstmt.setDouble(3, marker.getLat());//y
                pstmt.setInt(4, realty.getOwnerid());
                pstmt.setString(5,realty.getResidentemail());
                if (pstmt.executeUpdate() > 0) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    rs.next();
                    id = rs.getInt(1);
                    rs.close();
                }
                pstmt.close();
                c.close();
            }else{
                System.out.println("count is bigger than 5");
            }
        }
        return id;
    }
}
