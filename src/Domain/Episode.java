package Domain;

public class Episode extends Program {
    private int programId;
    private String followNumber;

    public Episode(int programId, String title, String genre, String language, String lengthOfTime, String followNumber, int age) {
        super(programId, title, genre, language,age, lengthOfTime);
        this.programId = programId;
        this.followNumber = followNumber;
    }

    public String getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(String followNumber) {
        this.followNumber = followNumber;
    }
}
