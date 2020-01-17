package Domain;

import java.util.ArrayList;

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

    public boolean setEmail(String email) {
        if (email.isEmpty()) {
            return false;
        }
        this.email = email;
        return true;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if (password.isEmpty()) {
            return false;
        }
        this.password = password;
        return true;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public boolean setSubscriber(String subscriber) {
        if (subscriber.isEmpty()) {
            return false;
        }
        this.subscriber = subscriber;
        return true;
    }

    public String getAddress() {
        return address;
    }

    public boolean setAddress(String address) {
        if (address.isEmpty()) {
            return false;
        }
        this.address = address;
        return true;
    }

    public String getCity() {
        return city;
    }

    public boolean setCity(String city) {
        if (city.isEmpty()) {
            return false;
        }
        this.city = city;
        return true;
    }
}
