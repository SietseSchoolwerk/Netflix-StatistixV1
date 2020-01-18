package Domain;

public class Program {
    private int programId;
    private String title;
    private String genre;
    private String language;
    private String lengthOfTime;
    private int age;

    /**
     * Default constructor for the program
     * @param programId
     * @param title
     * @param genre
     * @param language
     * @param age
     * @param lengthOfTime
     */
    public Program(int programId, String title, String genre, String language,int age, String lengthOfTime) {
        this.programId = programId;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.lengthOfTime = lengthOfTime;
        this.age = age;
    }


    /**
     * Get the programId attribute
     * @return programId attribute
     */
    public int getProgramId() {
        return programId;
    }


    /**
     * Get the title attribute
     * @return title attribute
     */
    public String getTitle() {
        return title;
    }


    /**
     * Get the genre attribute
     * @return genre attribute
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Get the language attribute
     * @return language attribute
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Get the lengthOfTime attribute
     * @return lengthOfTime attribute
     */
    public String getLengthOfTime() {
        return lengthOfTime;
    }

    /**
     * Get the age attribtute
     * @return the age attribute
     */
    public int getAge() {
        return age;
    }
}
