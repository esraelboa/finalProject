package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSql {

    private static final String driverName = "org.postgresql.Driver";
    private static final String hostname = "jdbc:postgresql://localhost:5432/finalproject";
    private static final String username = "admin";
    private static final String password = "12345";
    //basma 
    private static final String sooma = "sooma";
    private static final String somapassword = "basmasalem1995";
    //esra
    private static final String esra = "esra";
    private static final String esrapassword = "0945830624";

    public static Connection getConnection() throws Exception {
        Connection con = null;
        Class.forName(driverName);
        con = DriverManager.getConnection(hostname, username, password);
        if (con != null) {
            return con;
        } else {
            throw new Exception();
        }
    }

}
