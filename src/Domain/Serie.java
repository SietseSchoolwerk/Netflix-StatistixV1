package Domain;

public class Serie {
    private String title;
    private String recommend;

    /**
     * Default constructor of the seri
     * @param title
     * @param recommend
     */
    public Serie(String title, String recommend) {
        this.title = title;
        this.recommend = recommend;
    }

    /**
     * Get the title of the current instance of Serie
     * @return Return the title of the current instance.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the recommended of the serie.
     * @return the recommended.
     */
    public String getRecommend() {
        return recommend;
    }
}
