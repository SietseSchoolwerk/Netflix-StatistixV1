package Domain;

public class Film extends Program {
    private int programId;

    public Film(int programId, String title, String genre, String language, String lengthOfTime) {
        super(programId, title, genre, language, lengthOfTime);
        this.programId = programId;
    }

    public boolean getFilm() {
        return true;
    }
}
