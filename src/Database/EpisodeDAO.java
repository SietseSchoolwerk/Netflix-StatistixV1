package Database;
import Domain.Episode;
import Domain.Program;
import Domain.Serie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class EpisodeDAO {
    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    // Default constructor for the EpisodeDAO class
    public EpisodeDAO() {
        this.connection = databaseConnection.getConn();
    }


    /**
     * Get an Episode object for a given ProgramId
     * @param ProgramId
     * @return an Episode object
     */
    public Episode getEpisode(int ProgramId) {
        try {

            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT Title, Vol FROM Episode WHERE ProgramId=?"
            );

            pdo.setInt(1, ProgramId);
            ResultSet rs = pdo.executeQuery();

            while (rs.next()) {
                ProgramDAO programDAO = new ProgramDAO();
                Program program = programDAO.getProgram(ProgramId);
                Serie serie = new SerieDAO().getSerie(ProgramId);
                return new Episode(ProgramId, rs.getString(1), program.getGenre(), program.getLanguage(), program.getLengthOfTime(), rs.getString(2), program.getAge());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Get an ArrayList with all episodes in a series
     * @param serie
     * @return an ArrayList with all Episode objects in a series
     */
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