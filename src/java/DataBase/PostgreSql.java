
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public  class PostgreSql {
    
           static Connection con;

    public static Connection getConnection()throws ClassNotFoundException, SQLException {
        
    Class.forName("org.postgresql.Driver");
    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finalProject", "sooma", "basmasalem1995");
    
    // Statement stmt = con.createStatement(); 
   //  ResultSet rs = stmt.executeQuery("select categories_name from category"); 
     return con;
    }
    
    public void close () throws SQLException {   
        
        con.close();
    }
}
