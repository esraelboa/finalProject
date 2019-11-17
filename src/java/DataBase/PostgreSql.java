package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSql {

    private static final String driverName = "org.postgresql.Driver";
    private static final String hostname = "jdbc:postgresql://localhost:5432/finalProject";
    private static final String username =  "admin";
    private static final String password = "12345";
    
    //basma soomahostname="jdbc:postgresql://localhost:5432/finalProject"
    private static final String sooma = "sooma";
    private static final String somapassword = "basmasalem1995";
    
    //esraabo esrahostname="jdbc:postgresql://localhost:5432/finalProject"
    private static final String esra = "esra";
    private static final String esrapassword = "0945830624";
    //esraelha esrahostname="jdbc:postgresql://localhost:5432/finalproject"
    private static final String esraelha = "admin";
    private static final String esraelhapassword = "12345";
    
    public static Connection getConnection() throws Exception {
        Connection con = null;
        Class.forName(driverName);
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finalProject",esra,esrapassword);
        if (con != null) {
            return con;
        } else {
            throw new Exception();
        }
    }

}
