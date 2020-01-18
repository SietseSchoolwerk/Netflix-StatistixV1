package Database;

import Domain.Serie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class SerieDAO {

    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    /**
     * Default constructor for the SerieDAO
     */
    public SerieDAO() {
        this.connection = databaseConnection.getConn();
    }

    /**
     * Get a series for a given programID
     * @param programId
     * @return a Serie object
     */
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


    /**
     * Get an ArrayList of all series
     * @return an ArrayList with Serie object of all the series in the database
     */
    public ArrayList<Serie> getAllSeries(){
        String sql = "SELECT * FROM Serie;";

        try {
            ArrayList<Serie> result = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.add(new Serie(rs.getString(1), rs.getString(2)));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the average watched percentage of a give series per episode
     * @param programId
     * @return an integer of the query result
     */
    public int getAvgWatchedPercentageFromSeriePerEpisode(int programId) {

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

            if(rs.next()) {
                return rs.getInt(1);

            }else if (!rs.next()){
                return 0;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get the average percentage of a series per episode for a given account (by email)
     * @param programId
     * @param email
     * @return an int of the query result
     */
    public int getAvgWatchedPercentageFromSeriePerEpisodeWithAccount(int programId, String email) {

        try {

            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT AVG(WatchedPercentage) FROM Programma " +
                            "INNER JOIN Watched " +
                            "ON Watched.ProgramId= Programma.ProgramId " +
                            "INNER JOIN Profile " +
                            "ON Watched.ProfileId = Profile.ProfileId " +
                            "WHERE Programma.ProgramId= ? " +
                            "AND Profile.Email = ? " +
                            "GROUP BY Programma.Title "
            );
            pdo.setInt(1, programId);
            pdo.setString(2, email);
            ResultSet rs = pdo.executeQuery();

            if(rs.next()) {
                return rs.getInt(1);

            }else if (!rs.next()){
                return 0;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}