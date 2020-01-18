package Domain;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    private String email;
    private String password;
    private String subscriber;
    private String address;
    private String city;
    private ArrayList<Profile> profilesList;


    /**
     * Default constructor for Account.
     * @param email
     * @param password
     * @param subscriber
     * @param address
     * @param city
     */
    public Account(String email, String password, String subscriber, String address, String city) {
        this.email = email;
        this.password = password;
        this.subscriber = subscriber;
        this.address = address;
        this.city = city;
    }

    /**
     * Get the email attribute
     * @return the email string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setting the email of the account of this instance
     * @param email
     * @return boolean to indicate if it was succesfull
     */
    public boolean setEmail(String email) {
        // Needing to check if the string is not empty.
        Pattern p = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$");
        Matcher m = p.matcher(email);

        if (email.isEmpty()) {
            return false;
        }

        if (!m.find()){
            return false;
        }
        this.email = email;
        return true; // Return Succesfull
    }

    /**
     * Returns the password.
     * @return String Password of this instance of account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setting the password of the current instance of account
     * @param password
     * @return boolean indicates if its been succesfull or not.
     */
    public boolean setPassword(String password) {
        // Needing to check if the string is not empty.
        if (password.isEmpty()) {
            return false;
        }
        this.password = password;
        return true; //Password has been succesfully changed
    }

    /**
     * Returns the subscriber of this current instance of account.
     * @return String subscriber
     */
    public String getSubscriber() {
        return subscriber;
    }

    /**
     * Sets subscriber of the current instance of account.
     * @param subscriber
     * @return boolean to indicate if it has been succesfully changed.
     */
    public boolean setSubscriber(String subscriber) {
        // Needing to check if the string is not empty.
        if (subscriber.isEmpty()) {
            return false;
        }
        this.subscriber = subscriber;
        return true; // Subscriber succesfully changed
    }

    /**
     * Returns address of the current instance of account.
     * @return String Address
     */
    public String getAddress() {
        return address;
    }


    /**
     * Set address of the current instance of the user
     * @param address
     * @return boolean, to indicate if it has been succesfull.
     */
    public boolean setAddress(String address) {
        // Needing to check if the string is not empty.
        if (address.isEmpty()) {
            return false;
        }
        this.address = address;
        return true; // The data has been changed.
    }

    /**
     * Get the city of the current instance of the class
     * @return string city
     */
    public String getCity() {
        return city;
    }

    public boolean setCity(String city) {
        // Needing to check if the string is not empty.
        if (city.isEmpty()) {
            return false;
        }
        this.city = city;
        return true; //Data has been changed.
    }
}
