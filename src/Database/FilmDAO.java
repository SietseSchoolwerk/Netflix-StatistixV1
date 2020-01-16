package Database;

import Domain.Film;
import Domain.Program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/*

    Deze class bevat de method getFilm.

*/

public class FilmDAO extends ProgramDAO {

    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public FilmDAO() {
        this.connection = databaseConnection.getConn();
    }

    public Film getFilm(int programId) {
        ProgramDAO dao = new ProgramDAO();
        Program prog = dao.getProgram(programId);
        return new Film(programId, prog.getTitle(), prog.getGenre(), prog.getLanguage(), prog.getLengthOfTime(), prog.getAge());
    }

    public ArrayList<Program> getAllMovies() {
        String sql = "SELECT * FROM Programma WHERE NOT Programma.ProgramId " +
                                " IN (SELECT Episode.ProgramId FROM Episode);";

        try {
            ArrayList<Program> result = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet r = statement.executeQuery();
            while(r.next()) {
                result.add(new Film(r.getInt("ProgramId"),r.getString("Title"),r.getString("Genre"),r.getString("Language"), r.getString("playtime"), r.getInt("Age")));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
