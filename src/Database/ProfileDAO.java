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
     * Get a profile for a given name and email
     * @param name
     * @param email
     * @return a Profile object
     */
    public Profile getProfile(String name, String email) {
        try {
            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT * FROM Profile WHERE Email=? AND Name = ?"
            );
            pdo.setString(1, email);
            pdo.setString(2, name);
            ResultSet rs = pdo.executeQuery();


            Object[] arr = new Object[4];

            while (rs.next()) {
                arr[0] = rs.getInt(1); //Id
                arr[1] = rs.getString(2); // email
                arr[2] = rs.getString(3); //name
                arr[3] = rs.getInt(4); // age
            }

            if (arr[1] == null){
                arr[0] = 0;
                arr[3] = 0;
            }
            return new Profile((int)arr[0], (String)arr[2], (int)arr[3], (String)arr[1]);

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

        try {
            PreparedStatement pdo = this.connection.prepareStatement(
                    "  UPDATE Profile" +
                            "  SET Name=?" +
                            "  ,Age= ?"
            );
            pdo.setString(1, NewName);
            pdo.setInt(2, NewAge);
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
