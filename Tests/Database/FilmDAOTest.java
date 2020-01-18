package Database;

import Domain.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmDAOTest {

    @Test
    void getFilmWithAgeUnderSixteenShouldReturnTheGoodTheBadAndTheUgly() {
        //Arrange
        FilmDAO filmDAO = new FilmDAO();

        //Act
        Film film = filmDAO.getFilmWithAgeUnderSixteen();

        //Assert
        Assertions.assertEquals("The Good, the Bad and the Ugly", film.getTitle());
    }
}