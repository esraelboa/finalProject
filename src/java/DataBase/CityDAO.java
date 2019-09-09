/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.*;
import java.util.ArrayList;
import javaClasses.City;

/**
 *
 * @author esra
 */
public class CityDAO {

    public static ArrayList<City> getAllCities(int fenceTypeNo) throws Exception {
        ArrayList<City> cities = new ArrayList<>();
        Connection c = PostgreSql.getConnection();
        String sql = "select a_name,coor from citycoor where fence_type_no=?;";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1,fenceTypeNo);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            City city = new City();
            city.setName(rs.getString("a_name"));
            city.setCoordinate(rs.getString("coor"));
            cities.add(city);
        }

        rs.close();
        pstmt.close();
        c.close();
        return cities;
    }

   

}
