package Domain;

public class Film extends Program {
    private int programId;

    public Film(int programId, String title, String genre, String language, String lengthOfTime, int age) {
        super(programId, title, genre, language,age, lengthOfTime);
        this.programId = programId;
    }
}
