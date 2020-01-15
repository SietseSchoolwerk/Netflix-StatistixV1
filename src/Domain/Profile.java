package Domain;

public class Profile {
    private int profileId;
    private String name;
    private int age;

    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Profile(int profileId, String name, int age) {
        this.profileId = profileId;
        this.name = name;
        this.age = age;
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

    public void setBekeken() {

    }
}
