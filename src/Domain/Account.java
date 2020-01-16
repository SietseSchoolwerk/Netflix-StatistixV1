package Domain;

import java.util.ArrayList;


/*



 */

public class Account {
    private String email;
    private String password;
    private String subscriber;
    private String address;
    private String city;
    private ArrayList<Profile> profilesList;

    public Account(String email, String password, String subscriber, String address, String city) {
        this.email = email;
        this.password = password;
        this.subscriber = subscriber;
        this.address = address;
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
