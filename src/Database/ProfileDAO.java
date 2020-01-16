package Database;
import Domain.Profile;
import Domain.Program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

    /*

    Deze Class bevat alle methods die met de Profile table te maken hebben.

    De method: addProfile neemt een Profile object en voegd de attributen van dit\
    object toe als records in de database.

    De method deleteProfile verwijderd records

    De method editProfile past een record in de database aan naar de nieuwe attributen\
    van het bijbehorende object.
     */

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
                result.add(new Profile(r.getInt("ProfileId"), r.getString("Name"),r.getInt("Age"),r.getString("Email")));
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

            return new Profile(Integer.parseInt((String)arr[0]),(String)arr[1],(int)arr[2], (String)arr[3]);

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public String getEmailWithProfileId(int profileId){
            String sql = "SELECT * FROM Profile WHERE ProfileId=?;";

            try {
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


    public int profileCounter(String Email) {
        String sql = "SELECT count(*) as result FROM Profile WHERE Email=?;";

        try {
            ArrayList<Profile> result = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, Email);

            ResultSet r = statement.executeQuery();
            while(r.next()) {
                return r.getInt("result");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public boolean addProfile( Profile profile){
        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "INSERT INTO Profile (Name,Age,Email) values(?,?,?)"
            );

            //pdo.setString(1,email);
            pdo.setString(1,profile.getName());
            pdo.setInt(2,profile.getAge());
            pdo.setString(3,profile.getEmail());
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


    public boolean deleteProfile(Profile profile){
        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "DELETE FROM Profile WHERE ProfileId=?"
            );
            pdo.setInt(1, profile.getProfileId());
            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }




    public boolean setWatched(Profile profile , Program program , int watchedPercentage){
        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "INSERT INTO Watched (WatchedPercentage,ProfileId,ProgramId) values(?,?,?)"
            );

            pdo.setInt(1,watchedPercentage);
            pdo.setInt(2,profile.getProfileId());
            pdo.setInt(3,program.getProgramId());
            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public boolean editWatched(int watchedId,int watchedPercentage) {
        try {
            PreparedStatement pdo = this.connection.prepareStatement(
                    "  UPDATE Watched" +
                            "  SET WatchedPercentage=?" +
                            "  WatchedId WatchedId=?"
            );
            pdo.setInt(1, watchedId);
            pdo.setInt(2, watchedPercentage);
            pdo.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



        public boolean deleteWatched(int watchedId){
            try{
                PreparedStatement pdo = this.connection.prepareStatement(
                        " DELETE FROM Watched" +
                                " WatchedId WatchedId=?"
                );
                pdo.setInt(1,watchedId);
                pdo.execute();
                return true;
            } catch(Exception e){
                e.printStackTrace();
                return false;
            }

}


/*
    public static boolean getWatched(){}
    */

}
