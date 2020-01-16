
package Database;

import Domain.Serie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SerieDAO {

    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public SerieDAO() {
        this.connection = databaseConnection.getConn();
    }

    public Serie getSerie(int programId) {

        try {

            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT * FROM Serie " +
                            "INNER JOIN Episode on Episode.Title = Serie.Title " +
                            "WHERE Episode.ProgramId = ?;"
            );
            pdo.setInt(1,programId);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()) {
                String title = rs.getString(1);
                String similar = rs.getString(2);
                return new Serie(title, similar);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}