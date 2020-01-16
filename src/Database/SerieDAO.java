
package Database;

import Domain.Serie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


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

    // return episode

    public ArrayList<Integer> getAvgWatchedPercentageFromSeriePerEpisode(int programId) {

        try {

            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT AVG(WatchedPercentage) FROM Programma " +
                            "INNER JOIN Watched " +
                            "ON Watched.ProgramId= Programma.ProgramId " +
                            "WHERE Programma.ProgramId=? " +
                            "GROUP BY Programma.Title "
            );
            pdo.setInt(1, programId);
            ResultSet rs = pdo.executeQuery();
            ArrayList<Integer> AvgWatchedPercentageFromSeriePerEpisode =
                    new ArrayList<Integer>();
            while (rs.next()) {
                AvgWatchedPercentageFromSeriePerEpisode.add(
                        rs.getInt(1)
                );
            }
            return AvgWatchedPercentageFromSeriePerEpisode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;


        }

    }
}