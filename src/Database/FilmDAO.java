package Database;

import java.sql.Connection;
import Domain.Film;

/*

    Deze class bevat de method getFilm. Zoals in de ERD te lezen valt\
    is een Film simpelweg een Program. Door de FilmDAO te extenden met\
    de Program class kunnen simpelweg alle functionaliteiten van deze\
    super class worden overgenomen zonder code te dupliceren.

*/

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
