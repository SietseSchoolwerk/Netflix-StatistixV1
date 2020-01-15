package Database;

import java.sql.Connection;
import java.sql.DriverManager;



public class DatabaseConnection {

    private Connection connection;
    private String databaseName = "Netflix";
    private final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Netflix;integratedSecurity=true;port=1433";

    public Connection getConn() {
        try {
            return DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}