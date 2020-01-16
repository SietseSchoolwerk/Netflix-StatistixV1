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

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setBekeken() {

    }
}
