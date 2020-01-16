package Domain;

import java.util.ArrayList;
import java.util.List;

public class Serie {
    private String title;
    private String recommend;
    private List<Episode> episodeList;

    public Serie(String title, String recommend) {
        this.title = title;
        this.recommend = recommend;
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
