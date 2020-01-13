package Domain;

public class Episode extends Program {
    private int programId;
    private String followNumber;

    public Episode(int programId, String title, String genre, String language, String lengthOfTime, String followNumber) {
        super(programId, title, genre, language, lengthOfTime);
        this.programId = programId;
        this.followNumber = followNumber;
    }

    public boolean getEpisode(){
        return true;
    }
}
