package Domain;

public class Program {
    private int programId;
    private String title;
    private String genre;
    private String language;
    private String lengthOfTime;
    private int age;

    public Program(int programId, String title, String genre, String language,int age, String lengthOfTime) {
        this.programId = programId;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.lengthOfTime = lengthOfTime;
        this.age = age;
    }

    public int getProgramId() {
        return programId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLengthOfTime() {
        return lengthOfTime;
    }

    public void setLengthOfTime(String lengthOfTime) {
        this.lengthOfTime = lengthOfTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
