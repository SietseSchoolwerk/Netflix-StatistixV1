package Domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProfileTest {

    @Test
    void testSetInvalidNameShouldReturnFalse() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );
        String invalidName = "";

        //Act
        boolean result = profile.setName(invalidName);

        //Assert
        Assertions.assertFalse(result);

    }

    @Test
    void testSetValidNameShouldReturnTrue() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );
        String validName = "NewValidName";

        //Act
        boolean result = profile.setName(validName);

        //Assert
        Assertions.assertTrue(result);

    }

    @Test
    void testSetInvalidAgeShouldReturnFalse() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );

        int inValidAge = -200;

        //Act
        boolean result = profile.setAge(inValidAge);

        //Assert
        Assertions.assertFalse(result);


    }

    @Test
    void testSetValidAgeShouldReturnTrue() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );
        int validAge = 47;

        //Act
        boolean result = profile.setAge(validAge);

        //Assert
        Assertions.assertTrue(result);
    }

    @Test
    void testSetBekekenWithInvalidDataShouldReturnFalse() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );
        int inValidAge = -90;
        String inValidName = "";

        //Act
        boolean result1 = profile.setAge(inValidAge);
        boolean result2 = profile.setName(inValidName);

        //Assert
        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);

    }

    @Test
    void testSetBekekenWithValidDataShouldReturnTrue() {

        //Arrange
        Profile profile = new Profile(
                "ValidName",
                28
        );
        int validAge = 47;
        String validString = "NewValidName";

        //Act
        boolean result1 = profile.setAge(validAge);
        boolean result2 = profile.setName(validString);

        //Assert
        Assertions.assertTrue(result1);
        Assertions.assertTrue(result2);

    }
}