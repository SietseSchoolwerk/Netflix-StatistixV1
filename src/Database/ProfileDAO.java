package Database;
import Domain.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProfileDAO {

    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public ProfileDAO() {
        this.connection = databaseConnection.getConn();
    }

    public ArrayList<Profile> getAllProfiles(String email) {
        String sql = "SELECT * FROM Profile WHERE Email=?;";

        try {
            ArrayList<Profile> result = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet r = statement.executeQuery();
            while(r.next()) {
                result.add(new Profile(r.getInt("ProfileId"), r.getString("Name"),r.getInt("Age")));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Profile getProfile(String name){
        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT ProfileId,name,age FROM Profile WHERE Name=?"
            );
            pdo.setString(1, name);
            ResultSet rs = pdo.executeQuery();


            Object[] arr = new Object[2];

            while (rs.next()) {
                arr[0] = rs.getInt(0); //Id
                arr[1] = rs.getString(1); //name
                arr[2] = rs.getInt(2); // age
            }

            return new Profile(Integer.parseInt((String)arr[0]),(String)arr[1],(int)arr[2]);

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public String getEmailWithProfileId(int profileId){
            String sql = "SELECT * FROM Profile WHERE ProfileId=?;";

            try {
                ArrayList<Profile> result = new ArrayList<>();
                PreparedStatement statement = this.connection.prepareStatement(sql);
                statement.setInt(1, profileId);

                ResultSet r = statement.executeQuery();
                while(r.next()) {
                    return r.getString("Email");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }


    public boolean addProfile( Profile profile){

        String ProfileId = profile.getName();
        //String email = profile.getEmail();
        String name = profile.getName();
        int age = profile.getAge();


        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "INSERT INTO Profile (Name,Age) values(?,?)"
            );

            //pdo.setString(1,email);
            pdo.setString(1,name);
            pdo.setInt(2,age);
            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public boolean editProfile(Profile profile){

        String NewName = profile.getName();
        int NewAge = profile.getAge();


        StringBuilder setStatementStringBuilder = new StringBuilder();

        // Beware. This code has the same lame SQLi as in the AccountDAO.
        setStatementStringBuilder.append(String.format("UPDATE Profile SET "));

            setStatementStringBuilder.append(
                    String.format("Name='%s',",NewName)
            );


            setStatementStringBuilder.append(
                    String.format("Age='%s',",NewAge )
            );

        String setStatementString = setStatementStringBuilder.toString();
        String setStatementStringSliced =
                setStatementString.substring(0, setStatementString.length() - 1);

        //setStatementStringStrSliced += " WHERE uniqueId=<uniqueId>";
        setStatementStringSliced += String.format(
                " WHERE ProfileId='%s'",profile.getProfileId());


        try{
            PreparedStatement pdo = connection.prepareStatement(
                    setStatementStringSliced
            );
            System.out.println(setStatementStringBuilder.toString());
            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteProfile(int ProfileId){
        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "DELETE FROM Profile WHERE ProfileId=?"
            );
            pdo.setInt(1, ProfileId);
            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }



    /*
    public static boolean setWatched(int watchedId, int profileId,int programId,int watchedPercentage){

    }

    public static boolean getWatched(){}
    */

}
