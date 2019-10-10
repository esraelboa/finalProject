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
import javaClasses.Category;
import javaClasses.CommercialRealties;

/**
 *
 * @author esra1996
 */
public class CommercialRealtiesDAO {
    public static Boolean checklicenseNumber(CommercialRealties  commercialrealties) throws Exception {
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
     
   if(checklicenseNumber(commercialrealties)){
            
        String sql = "insert  into commercialrealties(realtyname, licensenumber, description, residentid) VALUES (?,?,?,?);";
        PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setString(1,commercialrealties.getRealtyName());
         pstmt.setInt(2,commercialrealties.getLicenseNumber());
         pstmt.setString(3,commercialrealties.getDescription());
         pstmt.setInt(4, commercialrealties.getResidentId());
         pstmt.executeUpdate();
         ResultSet rsu = pstmt.getGeneratedKeys();
         rsu.next();
         id=rsu.getInt(1);
     
       
    }else{
         System.out.println("error");

   }
    return id;
        }
        
            public static CommercialRealties displayCommercialRealtiesInfo(int CommercialRealtiesid) throws Exception {
        CommercialRealties CommercialRealties = null;
        Category category=null;
        
        Connection c = PostgreSql.getConnection();
        String sql = "select commercialrealties.realtyname,category.name,commercialrealties.description from commercialrealties\n" +
"inner join category on category.catid = commercialRealties.id";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, CommercialRealtiesid);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
        //    CommercialRealties = new CommercialRealties();
          CommercialRealties.setRealtyName(rs.getString("realtyName"));
          category.setName(rs.getString("name"));
          CommercialRealties.setDescription(rs.getString("description"));
            
        }
        return CommercialRealties;
    }
}
