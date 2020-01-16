package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domain.Episode;
import Domain.Film;
import Domain.Program;
import Domain.Serie;

/*

    Deze class bevat de method getFilm. Zoals in de ERD te lezen valt\
    is een Film simpelweg een Program. Door de FilmDAO te extenden met\
    de Program class kunnen simpelweg alle functionaliteiten van deze\
    super class worden overgenomen zonder code te dupliceren.

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

}
