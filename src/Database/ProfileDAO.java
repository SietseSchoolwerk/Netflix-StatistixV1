package Database;
import Domain.Profile;
import Domain.Program;
import Domain.Watched;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProfileDAO {

    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

   //Default constructor for the ProfileDAO class
    public ProfileDAO() {
        this.connection = databaseConnection.getConn();
    }

    /**
     * Get all profiles for an account (by a given email)
     * @param email
     * @return an ArrayList with Profile objects
     */
    public ArrayList<Profile> getAllProfiles(String email) {
        String sql = "SELECT * FROM Profile WHERE Email=?;";

        try {
            ArrayList<Profile> result = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet r = statement.executeQuery();
            while (r.next()) {
                result.add(new Profile(r.getInt("ProfileId"), r.getString("Name"), r.getInt("Age"), r.getString("Email")));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a profile for a given name
     * @param name
     * @return a Profile object
     */
    public Profile getProfile(String name) {
        try {
            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT ProfileId,name,age,Email FROM Profile WHERE Name=?"
            );
            pdo.setString(1, name);
            ResultSet rs = pdo.executeQuery();


            Object[] arr = new Object[2];

            while (rs.next()) {
                arr[0] = rs.getInt(0); //Id
                arr[1] = rs.getString(1); //name
                arr[2] = rs.getInt(2); // age
            }

            return new Profile(Integer.parseInt((String) arr[0]), (String) arr[1], (int) arr[2], (String) arr[3]);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * get the email address for a given profileId
     * @param profileId
     * @return the string with the email address
     */
    public String getEmailWithProfileId(int profileId) {
        String sql = "SELECT * FROM Profile WHERE ProfileId=?;";

        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, profileId);

            ResultSet r = statement.executeQuery();
            while (r.next()) {
                return r.getString("Email");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Count how many profiles an account has (for a given email)
     * @param Email
     * @return an int with the number of profiles
     */
    public int profileCounter(String Email) {
        String sql = "SELECT count(*) as result FROM Profile WHERE Email=?;";

        try {
            ArrayList<Profile> result = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, Email);

            ResultSet r = statement.executeQuery();
            while (r.next()) {
                return r.getInt("result");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Add a new profile to the database.
     * @param profile
     * @return true if query executed without errors. False if there were exceptions.
     */
    public boolean addProfile(Profile profile) {
        try {
            PreparedStatement pdo = connection.prepareStatement(
                    "INSERT INTO Profile (Name,Age,Email) values(?,?,?)"
            );

            //pdo.setString(1,email);
            pdo.setString(1, profile.getName());
            pdo.setInt(2, profile.getAge());
            pdo.setString(3, profile.getEmail());
            pdo.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *  Edit a profile
     * @param profile
     * @return true if query executed without errors. False if there were exceptions.
     */

    // Change the StringBuilder part to something seen in AccountDAO.editAccount
    public boolean editProfile(Profile profile) {



        String NewName = profile.getName();
        int NewAge = profile.getAge();


        StringBuilder setStatementStringBuilder = new StringBuilder();

        // Beware. This code has the same lame SQLi as in the AccountDAO.
        setStatementStringBuilder.append(String.format("UPDATE Profile SET "));

        setStatementStringBuilder.append(
                String.format("Name='%s',", NewName)
        );


        setStatementStringBuilder.append(
                String.format("Age='%s',", NewAge)
        );

        String setStatementString = setStatementStringBuilder.toString();
        String setStatementStringSliced =
                setStatementString.substring(0, setStatementString.length() - 1);

        setStatementStringSliced += String.format(
                " WHERE ProfileId='%s'", profile.getProfileId());


        try {
            PreparedStatement pdo = connection.prepareStatement(
                    setStatementStringSliced
            );
            System.out.println(setStatementStringBuilder.toString());
            pdo.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a profile for a given Profile object
     * @param profile
     * @return true if query executed without errors. False if there were exceptions.
     */
    public boolean deleteProfile(Profile profile) {
        try {
            PreparedStatement pdo = connection.prepareStatement(
                    "DELETE FROM Profile WHERE ProfileId=?"
            );
            pdo.setInt(1, profile.getProfileId());
            pdo.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Set the watched percentage for a given Program and a Profile
     * @param profile
     * @param program
     * @param watchedPercentage
     * @return true if query executed without errors. False if there were exceptions.
     */
    public boolean setWatched(Profile profile, Program program, int watchedPercentage) {
        try {
            PreparedStatement pdo = connection.prepareStatement(
                    "INSERT INTO Watched (WatchedPercentage,ProfileId,ProgramId) values(?,?,?)"
            );

            pdo.setInt(1, watchedPercentage);
            pdo.setInt(2, profile.getProfileId());
            pdo.setInt(3, program.getProgramId());
            pdo.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Update watchedPercentage for a given WatchedId
     * @param watchedId
     * @param watchedPercentage
     * @return true if query executed without errors. False if there were exceptions.
     */
    public boolean editWatched(int watchedId,int watchedPercentage) {
        try {
            PreparedStatement pdo = this.connection.prepareStatement(
                    "  UPDATE Watched" +
                            "  SET WatchedPercentage=?" +
                            "  WHERE WatchedId=?"
            );
            pdo.setInt(1, watchedPercentage);
            pdo.setInt(2, watchedId);
            pdo.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Delete a Watched record for a given WatchedId
     * @param watchedId
     * @return true if query executed without errors. False if there were exceptions.
     */
        public boolean deleteWatched(int watchedId){
            try{
                PreparedStatement pdo = this.connection.prepareStatement(
                        " DELETE FROM Watched" +
                                " WHERE WatchedId=?"
                );
                pdo.setInt(1,watchedId);
                pdo.execute();
                return true;
            } catch(Exception e){
                e.printStackTrace();
                return false;
            }

}

    /**
     *  Get watched objects for a given profileID
     * @param profileId
     * @return the ArrayList with Watched objects
     */
    public ArrayList<Watched> getWatched(int profileId) {
        String sql = "SELECT WatchedId, Programma.Title, Genre, Programma.Language, Programma.Age, Playtime, WatchedPercentage, Episode.Title, Vol FROM Watched " +
                "INNER JOIN Programma on Programma.ProgramId = Watched.ProgramId " +
                "LEFT JOIN Episode on Programma.ProgramId = Episode.ProgramId " +
                "WHERE ProfileId = ?";

        try {
            ArrayList<Watched> result = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, profileId);

            ResultSet r = statement.executeQuery();
            while (r.next()) {
                result.add(new Watched(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getInt(5), r.getInt(6), r.getInt(7), r.getString(8), r.getString(9)));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
