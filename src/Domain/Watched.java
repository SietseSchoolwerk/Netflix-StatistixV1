package Domain;

public class Watched {
    private int watchedId;
    private String program;
    private String genre;
    private String language;
    private int age;
    private int playtime;
    private float watchedPercentage;
    private String episode;
    private String followNumber;

    /**
     * Default constructor for watched.
     * @param watchedId
     * @param program
     * @param genre
     * @param language
     * @param age
     * @param playtime
     * @param watchedPercentage
     * @param episode
     * @param followNumber
     */
    public Watched(int watchedId, String program, String genre, String language, int age, int playtime, float watchedPercentage, String episode, String followNumber) {
        this.watchedId = watchedId;
        this.program = program;
        this.genre = genre;
        this.language = language;
        this.age = age;
        this.playtime = playtime;
        this.watchedPercentage = watchedPercentage;
        this.episode = episode;
        this.followNumber = followNumber;
    }

    /**
     * Returns watchedId of the current instance of account.
     * @return int watchedId
     */
    public int getWatchedId() {
        return watchedId;
    }

    /**
     * Returns program of the current instance of account.
     * @return String program
     */
    public String getProgram() {
        return program;
    }

    /**
     * Returns genre of the current instance of account.
     * @return String genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Returns language of the current instance of account.
     * @return String language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Returns age of the current instance of account.
     * @return int age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns playtime of the current instance of account.
     * @return int playtime
     */
    public int getPlaytime() {
        return playtime;
    }

    /**
     * Returns watchedPercentage of the current instance of account.
     * @return float watchedPercentage
     */
    public float getWatchedPercentage() {
        return watchedPercentage;
    }

    /**
     * Checks watchedPercentage of the current instance of account.
     * @return true or false
     */
    public boolean checkWatchedPercentage(int watchedPercentage) {
        if (watchedPercentage <= 100 && watchedPercentage > 0){
            return true;
        }
        return false;
    }

    /**
     * Returns episode of the current instance of account.
     * @return String episode
     */
    public String getEpisode() {
        return episode;
    }

    /**
     * Returns followNumber of the current instance of account.
     * @return String followNumber
     */
     public String getFollowNumber() {
        return followNumber;
    }

}
