package Domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    void testSetValidEmailShouldReturnTrue() {

        //Arrange
        Account account = new Account(
                "email1@avans.nl",
                "Password123",
                "Subscriber123",
                "Kapelaan Molstraat 27",
                "Breda"
                );
        String validEmail = "email2@avans.nl";

        //Act
        boolean result = account.setEmail(validEmail);

        //Assert
        Assertions.assertTrue(result);

    }

    @Test
    void testSetInvalidEmailShouldReturnFalse() {

            //Arrange
            Account account = new Account(
                    "email1@avans.nl",
                    "Password123",
                    "Subscriber123",
                    "Kapelaan Molstraat 27",
                    "Breda"
            );
            String invalidEmail = "wrongemail";

            //Act
            boolean result = account.setEmail(invalidEmail);

            //Assert
            Assertions.assertFalse(result);

    }

    @Test
    void testSetValidPasswordShouldReturnTrue() {

        //Arrange
        Account account = new Account(
                "email1@avans.nl",
                "Password123",
                "Subscriber123",
                "Kapelaan Molstraat 27",
                "Breda"
        );
        String validPassword = "ValidPassword123";

        //Act
        boolean result = account.setPassword(validPassword);

        //Assert
        Assertions.assertTrue(result);


    }

    @Test
    void testSetInvalidPasswordShouldReturnFalse() {

        //Arrange
        Account account = new Account(
                "email1@avans.nl",
                "Password123",
                "Subscriber123",
                "Kapelaan Molstraat 27",
                "Breda"
        );
        String invalidPassword = "";

        //Act
        boolean result = account.setPassword(invalidPassword);

        //Assert
        Assertions.assertFalse(result);

    }

    @Test
    void testSetValidSubscriberShouldReturnTrue() {

        //Arrange
        Account account = new Account(
                "email1@avans.nl",
                "Password123",
                "Subscriber123",
                "Kapelaan Molstraat 27",
                "Breda"
        );
        String validSubscriber = "Subscriber123";

        //Act
        boolean result = account.setSubscriber(validSubscriber);

        //Assert
        Assertions.assertTrue(result);

    }

    @Test
    void testSetInvalidSubscriberShouldReturnFalse() {

        //Arrange
        Account account = new Account(
                "email1@avans.nl",
                "Password123",
                "Subscriber123",
                "Kapelaan Molstraat 27",
                "Breda"
        );
        String invalidSubscriber = "";

        //Act
        boolean result = account.setSubscriber(invalidSubscriber);

        //Assert
        Assertions.assertFalse(result);

    }

    @Test
    void testSetValidAdressShouldReturnTrue() {

        //Arrange
        Account account = new Account(
                "email1@avans.nl",
                "Password123",
                "Subscriber123",
                "Kapelaan Molstraat 27",
                "Breda"
        );
        String validAddress = "Hoofdstraat 38";

        //Act
        boolean result = account.setAddress(validAddress);

        //Assert
        Assertions.assertTrue(result);

    }

    @Test
    void testSetInvalidAdressShouldReturnFalse() {

        //Arrange
        Account account = new Account(
                "email1@avans.nl",
                "Password123",
                "Subscriber123",
                "Kapelaan Molstraat 27",
                "Breda"
        );
        String invalidAddress = "";

        //Act
        boolean result = account.setAddress(invalidAddress);

        //Assert
        Assertions.assertFalse(result);

    }

    @Test
    void testSetValidCityShouldReturnTrue() {

        //Arrange
        Account account = new Account(
                "email1@avans.nl",
                "Password123",
                "Subscriber123",
                "Kapelaan Molstraat 27",
                "Breda"
        );
        String validAddress = "London";

        //Act
        boolean result = account.setCity(validAddress);

        //Assert
        Assertions.assertTrue(result);

    }

    @Test
    void testSetInvalidCityShouldReturnFalse() {


        //Arrange
        Account account = new Account(
                "email1@avans.nl",
                "Password123",
                "Subscriber123",
                "Kapelaan Molstraat 27",
                "Breda"
        );
        String invalidCity = "";

        //Act
        boolean result = account.setSubscriber(invalidCity);

        //Assert
        Assertions.assertFalse(result);

    }

}