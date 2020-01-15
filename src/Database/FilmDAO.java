package Database;

import java.sql.Connection;
import Domain.Film;

public class FilmDAO extends ProgramDAO{

    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public FilmDAO() {
        this.connection = databaseConnection.getConn();
    }

    public Film getFilm(int ProgramId){
        return (Film)super.getProgram(ProgramId);
    };

}
