package Database;
import Domain.Episode;
import Domain.Program;
import Domain.Serie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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
}