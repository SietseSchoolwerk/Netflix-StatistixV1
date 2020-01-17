package Database;
import Domain.Episode;
import Domain.Program;
import Domain.Serie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/*

    Deze class selecteerd data uit de Episode table.
    Vervolgens word met deze data een Episode object uit de Domain package gemaakt\
    en gereturned.

 */


public class EpisodeDAO {
    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public EpisodeDAO() {
        this.connection = databaseConnection.getConn();
    }


    public Episode getEpisode(int programId) {
        try {

            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT * FROM Programma " +
                            "INNER JOIN Episode on Episode.ProgramId = Programma.ProgramId\n" +
                            "WHERE Programma.ProgramId=?"
            );

            pdo.setInt(1, programId);
            ResultSet rs = pdo.executeQuery();

            while (rs.next()) {
                return new Episode(programId, rs.getString(2), rs.getString("Genre"), rs.getString("Language"), rs.getString("Playtime"), rs.getString("Vol"), rs.getInt("Age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public ArrayList<Program> getAllEpisodes(String serie) {
        try {

            ArrayList<Program> result = new ArrayList<>();
            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT * FROM Programma " +
                            "INNER JOIN Episode on Episode.ProgramId = Programma.ProgramId " +
                            "WHERE Episode.Title = ?;"
            );

            pdo.setString(1, serie);
            ResultSet rs = pdo.executeQuery();

            while (rs.next()) {
                result.add(new Episode(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(8), rs.getInt(5)));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}