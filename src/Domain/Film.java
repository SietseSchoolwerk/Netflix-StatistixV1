package Domain;

//Film class for the netflix program. This is not used for editing only for displaying in lists.
public class Film extends Program {
    private int programId;

    /**
     * Default constructor for film
     * @param programId
     * @param title
     * @param genre
     * @param language
     * @param lengthOfTime
     * @param age
     */
    public Film(int programId, String title, String genre, String language, String lengthOfTime, int age) {
        super(programId, title, genre, language,age, lengthOfTime);
        this.programId = programId;
    }
}
