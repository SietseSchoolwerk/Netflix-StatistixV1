package Domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProfileTest {

    @Test
    void testCheckNameWithEmptyStringShouldReturnFalse() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );
        String invalidName = "";

        //Act
        boolean result = profile.checkName(invalidName);

        //Assert
        Assertions.assertFalse(result);

    }

    @Test
    void testCheckNameWithNumbersShouldReturnFalse() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );
        String invalidName = "123";

        //Act
        boolean result = profile.checkName(invalidName);

        //Assert
        Assertions.assertFalse(result);

    }
    @Test
    void testCheckNameWithNumbersAndLettersShouldReturnFalse() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );
        String invalidName = "123sdf";

        //Act
        boolean result = profile.checkName(invalidName);

        //Assert
        Assertions.assertFalse(result);

    }

    @Test
    void testCheckNameWithOnlyLettersShouldReturnTrue() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );
        String validName = "NewValidName";

        //Act
        boolean result = profile.checkName(validName);

        //Assert
        Assertions.assertTrue(result);

    }

    @Test
    void testCheckAgeWithNegativeNumberShouldReturnFalse() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );

        int inValidAge = -1;

        //Act
        boolean result = profile.checkAge(Integer.toString(inValidAge));

        //Assert
        Assertions.assertFalse(result);

    }

    @Test
    void testCheckAgeWithZeroShouldReturnFalse() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );

        int inValidAge = 0;

        //Act
        boolean result = profile.checkAge(Integer.toString(inValidAge));

        //Assert
        Assertions.assertFalse(result);

    }

    @Test
    void testCheckAgeWithNumberAboveZeroShouldReturnTrue() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );
        int validAge = 1;

        //Act
        boolean result = profile.checkAge(Integer.toString(validAge));

        //Assert
        Assertions.assertTrue(result);
    }

    @Test
    void testSetBekekenWithNegativeNumberShouldReturnFalse() {

        //Arrange
        Watched watched = new Watched(
                0,
                "Test",
                "Test",
                "Test",
                1 ,
                12,
                0,
                null,
                null
        );
        int watchPercentage = -1;

        //Act
        boolean result1 = watched.checkWatchedPercentage(watchPercentage);

        //Assert
        Assertions.assertFalse(result1);

    }

    @Test
    void testSetBekekenWithNumberZeroShouldReturnFalse() {

        //Arrange
        Watched watched = new Watched(
                0,
                "Test",
                "Test",
                "Test",
                1 ,
                12,
                0,
                null,
                null
        );
        int watchPercentage = 0;

        //Act
        boolean result1 = watched.checkWatchedPercentage(watchPercentage);

        //Assert
        Assertions.assertFalse(result1);

    }

    @Test
    void testSetBekekenWithNumberAboveZeroAndUnderHunderdOneShouldReturnTrue() {

        //Arrange
        Watched watched = new Watched(
                0,
                "Test",
                "Test",
                "Test",
                1 ,
                12,
                0,
                null,
                null
        );
        int watchPercentage = 1;

        //Act
        boolean result1 = watched.checkWatchedPercentage(watchPercentage);

        //Assert
        Assertions.assertTrue(result1);

    }

    @Test
    void testSetBekekenWithNumberAboveHundredShouldReturnFalse() {

        //Arrange
        Watched watched = new Watched(
                0,
                "Test",
                "Test",
                "Test",
                1 ,
                12,
                0,
                null,
                null
        );
        int watchPercentage = 101;

        //Act
        boolean result1 = watched.checkWatchedPercentage(watchPercentage);

        //Assert
        Assertions.assertFalse(result1);

    }

    @Test
    void testSetBekekenWithNumberHundredShouldReturnTrue() {

        //Arrange
        Watched watched = new Watched(
                0,
                "Test",
                "Test",
                "Test",
                1 ,
                12,
                0,
                null,
                null
        );
        int watchPercentage = 100;

        //Act
        boolean result1 = watched.checkWatchedPercentage(watchPercentage);

        //Assert
        Assertions.assertTrue(result1);

    }
}