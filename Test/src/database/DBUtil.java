package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Roledene on 3/20/2017.
 */
public class DBUtil {
    private static final String USERNAME ="storytel_sachini";
    private static final String PASSWORD ="Hello@123";
    private static final String DATABASE ="storytel_imageprocessing";
    private static final String CONN ="jdbc:mysql://199.168.184.42:3306/"+DATABASE;

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
