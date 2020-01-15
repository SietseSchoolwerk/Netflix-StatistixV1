package Database;

import java.sql.Connection;
import java.sql.DriverManager;



public class DatabaseConnection {

    private static Connection connection;
    private static String databseName = "Netflix";
    private static String connectionUrl = String.format("jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=%s;integratedSecurity=true;", databseName);

    public static Connection getConn() {
        try {
            return DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}