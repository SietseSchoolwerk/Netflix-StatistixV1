package Domain;

public class Watched {
    private int watchedId;
    private String programma;
    private String genre;
    private String language;
    private int Age;
    private int playtime;
    private float watchedPercentage;
    private String episode;
    private String followNumber;

    public Watched(int watchedId, String programma, String genre, String language, int age, int playtime, float watchedPercentage, String episode, String followNumber) {
        this.watchedId = watchedId;
        this.programma = programma;
        this.genre = genre;
        this.language = language;
        this.Age = age;
        this.playtime = playtime;
        this.watchedPercentage = watchedPercentage;
        this.episode = episode;
        this.followNumber = followNumber;
    }

    public int getWatchedId() {
        return watchedId;
    }

    public void setWatchedId(int watchedId) {
        this.watchedId = watchedId;
    }

    public String getProgramma() {
        return programma;
    }

    public void setProgramma(String programma) {
        this.programma = programma;
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

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public float getWatchedPercentage() {
        return watchedPercentage;
    }

    public void setWatchedPercentage(int watchedPercentage) {
        this.watchedPercentage = watchedPercentage;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(String followNumber) {
        this.followNumber = followNumber;
    }
}
