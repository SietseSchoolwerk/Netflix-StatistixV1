package Database;

import java.sql.Connection;
import Domain.Film;

public class FilmDAO extends ProgramDAO{

    private static Connection connection = DatabaseConnection.getConn();

    public FilmDAO(Connection connection) {
        super(connection);
    }

    public Film getFilm(int ProgramId){
        return (Film)super.getProgram(ProgramId);
    };

}
