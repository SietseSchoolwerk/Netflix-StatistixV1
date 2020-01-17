package Domain;

public class Profile {
    private int profileId;
    private String name;
    private int age;
    private String email;

    // Alternative constructor
    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //Alternative constructor
    public Profile(int profileId, String name, int age, String email) {
        this(name, age, email);
        this.profileId = profileId;
    }

    // Constructor
    public Profile(String name, int age, String email) {
        this(name, age);
        this.email = email;
    }

    // Simple getter of the profile id of the profile
    public int getProfileId() {
        return profileId;
    }

    // Simple getter of the name
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the current instance of profile
     * @param name
     * @return boolean to indicate if the change was succesfull.
     */
    public boolean setName(String name) {
        // Need to check that the name that the user wants to set is not empty.
        if (name.isEmpty()) {
            return false;
        }
        this.name = name;
        return true;
    }

    /**
     * Returns age of the current instance of profile.
     * @return int age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setting the age of the current instance of the profile.
     * @param age
     * @return a boolean with the status of changing the age. Can be succesfull if true and unsucessful if false.
     */
    public boolean setAge(int age) {
        if (age == 0 || age < 0) {
            return false;
        }
        this.age = age;
        return true;
    }


    /**
     * Returns the email of the current profile.
     * @return String with email of the account the profile is linked to.
     * The email cannot be updated since the profile cannot switch.
     */
    public String getEmail() {
        return email;
    }
}
