package Domain;

import java.util.ArrayList;
import java.util.List;

public class Serie {
    private String title;
    private String recommend;

    //Default constructor of the serie class
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
