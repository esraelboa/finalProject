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
import javaClasses.CommercialRealties;

/**
 *
 * @author esra1996
 */
public class CommercialRealtiesDAO {
//    public static Boolean checklicenseNumber(CommercialRealties realties) throws Exception {
//        Connection c = PostgreSql.getConnection();
//        String sql = "select licenseNumber from commercialRealties where licenseNumber=?";
//        PreparedStatement pstmt = c.prepareStatement(sql);
//        pstmt.setInt(1, realties.getLicenseNumber());
//     
//        ResultSet rs = pstmt.executeQuery();
//        return !rs.next();
//    }
    
        public static int insertCommercialRealties(CommercialRealties realties) throws Exception {
        int id = 0;
        Connection con = PostgreSql.getConnection();
   
            
        String sql = "INSERT INTO public.commercialrealties(\n" +
"            id, realtyname, licensenumber, description, residentid)\n" +
"    VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setString(1,realties.getRealtyName());
         pstmt.setInt(2,realties.getLicenseNumber());
         pstmt.setString(3,realties.getDescription());
         pstmt.setInt(4, realties.getResidentId());
         pstmt.executeUpdate();
         ResultSet rsu = pstmt.getGeneratedKeys();

         rsu.next();
        id=rsu.getInt(1);
        
    
        
        return id;
    }
}
