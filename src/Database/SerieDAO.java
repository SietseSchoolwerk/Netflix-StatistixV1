
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Database.DatabaseConnection;
import Domain.Serie;


public class SerieDAO {

    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public SerieDAO() {
        this.connection = databaseConnection.getConn();
    }

    public Serie getAllSeries() {

        try {

            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT * FROM Serie"
            );
            ResultSet rs = pdo.executeQuery();
            while (rs.next()) {
                String title = rs.getString(1)
                String similar = rs.getString(2)
                return new Serie(title, similar);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}