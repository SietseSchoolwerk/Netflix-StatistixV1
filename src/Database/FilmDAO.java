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
            while (r.next()) {
                result.add(new Film(r.getInt("ProgramId"), r.getString("Title"), r.getString("Genre"), r.getString("Language"), r.getString("playtime"), r.getInt("Age")));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Film getFilmWithAgeUnderSixteen() {

        try {
            PreparedStatement pdo = this.connection.prepareStatement(
                    "SELECT TOP 1 * FROM Programma WHERE NOT Programma.ProgramId " +
                            " IN (SELECT Episode.ProgramId FROM Episode) AND AGE < 16 " +
                            " ORDER BY Playtime DESC"

            );
            ResultSet rs = pdo.executeQuery();
            Object[] arr = new Object[6];
            while (rs.next()) {
                arr[0] = rs.getInt(1);      // programId: int
                arr[1] = rs.getString(2);   // title: str
                arr[2] = rs.getString(3);   // Genre: str
                arr[3] = rs.getString(4);   // Language: str
                arr[4] = rs.getInt(5);      // Age: int
                arr[5] = rs.getInt(6);      // PlayTime: int
            }
            return new Film(
                    (int) arr[0],
                    (String) arr[1],
                    (String) arr[2],
                    (String) arr[3],
                    Integer.toString((int) arr[5]),
                    (int) arr[4]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public int getFilmFullyWatched(int programId) {
        try {

            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT COUNT(ProfileId) FROM Programma " +
                            "INNER JOIN Watched " +
                            "ON Programma.ProgramId=Watched.ProgramId " +
                            "WHERE WatchedPercentage = 100 AND Programma.ProgramId= ?"
            );
            pdo.setInt(1, programId);
            ResultSet rs = pdo.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);

            } else if (!rs.next()) {
                return 0;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
