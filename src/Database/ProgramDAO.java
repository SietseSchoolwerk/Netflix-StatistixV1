package Database;

import Domain.*;
import Domain.Program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/*
 * Deze class selecteerd data uit de Programs table.
 * Vervolgens word met deze data een Program object gemaakt\
 * en gereturned.
 */
public class ProgramDAO {

    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public ProgramDAO() { this.connection = databaseConnection.getConn(); }

    public Program getProgram(int ProgramId){
        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT Title,Genre,Language,Age,Playtime " +
                            "FROM Profile WHERE ProfileId=?"
            );
            pdo.setInt(1, ProgramId);
            ResultSet rs = pdo.executeQuery();

            Object[] arr = new Object[4];

            while (rs.next()) {         // Th is result data is untested
                arr[0] = rs.getString(1); // title
                arr[1] = rs.getString(2); // genre
                arr[2] = rs.getString(3); // language
                arr[3] = rs.getInt(4);    // playTime
            }
            return new Program(
                    ProgramId,
                    (String)arr[0],
                    (String)arr[1],
                    (String)arr[2],
                    (String)arr[3]
            );

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Program> getAllPrograms(){
        try{
            ArrayList<Program> programmaList = new ArrayList<>();
            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT ProgramId " +
                            "FROM Programma"
            );
            ResultSet rs = pdo.executeQuery();

            Object[] arr = new Object[4];

            while (rs.next()) {
                int programmaId = rs.getInt(1);    //Program id

                if (isSerie(programmaId)) {
                    EpisodeDAO episodeDAO = new EpisodeDAO();
                    Episode episode = episodeDAO.getEpisode(programmaId);

                    programmaList.add(episode);
                } else {
                    FilmDAO filmDAO = new FilmDAO();
                    Film film = filmDAO.getFilm(programmaId);

                    programmaList.add(film);
                }

            }
            return programmaList;

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public boolean isSerie(int programId) {
        String sql = "SELECT count(*) as result FROM Episode WHERE ProgramId=?;";

        try {
            ArrayList<Profile> result = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, programId);

            ResultSet r = statement.executeQuery();
            while(r.next()) {
                if (r.getInt("result") == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
