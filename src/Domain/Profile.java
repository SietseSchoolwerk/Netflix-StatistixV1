package Domain;

public class Profile {
    private int profileId;
    private String name;
    private int age;
    private String email;

    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Profile(int profileId, String name, int age, String email) {
        this.profileId = profileId;
        this.name = name;
        this.age = age;
        this.email = email;
    }


    public Profile(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }


    public int getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (name.isEmpty()) {
            return false;
        }
        this.name = name;
        return true;
    }

    public int getAge() {
        return age;
    }

    public boolean setAge(int age) {
        if (age == 0) {
            return false;
        }
        this.age = age;
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setBekeken() {

    }
}
