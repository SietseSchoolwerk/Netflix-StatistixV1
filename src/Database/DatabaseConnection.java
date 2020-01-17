package Database;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {

    private Connection connection;
    private String databaseName = "Netflix";
    private final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Netflix;integratedSecurity=true;port=1433";

    /**
     * This method makes a connection to the database and returns it.
     * @return a Connection
     */

    public Connection getConn() {
        try {
            return DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}