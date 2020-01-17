package Domain;

public class Program {
    // Read only properties since this application includes no edit of the class
    private int programId;
    private String title;
    private String genre;
    private String language;
    private String lengthOfTime;
    private int age;

    //Default constructor for the program class.
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

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getLengthOfTime() {
        return lengthOfTime;
    }

    public int getAge() {
        return age;
    }
}
