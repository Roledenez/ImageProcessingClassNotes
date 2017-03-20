package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Roledene on 3/20/2017.
 */
public class DBUtil {
    private static final String USERNAME ="root";
    private static final String PASSWORD ="";
    private static final String CONN ="jdbc:mysql://localhost:3306/Meth-Medi";

    public static Connection getConnection(DBType dbType) throws SQLException {
        switch(dbType){
            case MYSQL:
//                return DriverManager.getConnection(CONN+"?user="+USERNAME+"&password="+PASSWORD);
                return DriverManager.getConnection(CONN,USERNAME,PASSWORD);
            default:
                return null;
        }

    }

    public static void errorProcess(SQLException e){
        System.out.println("Eroor message : "+e.getMessage());
        System.out.println("Eroor code : "+e.getErrorCode());
        System.out.println("SQL state : "+e.getSQLState());
    }
}
