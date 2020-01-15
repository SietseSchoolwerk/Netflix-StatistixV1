package Database;
import Domain.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfileDAO {

    private static Connection connection = DatabaseConnection.getConn();


    public void ProfileDAO(Connection connection) { this.connection = connection; }
    public void ProfileDAO(){}

    public static Profile getProfile(String name){
        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT name,age FROM Profile WHERE Name=?"
            );
            pdo.setString(1, name);
            ResultSet rs = pdo.executeQuery();


            Object[] arr = new Object[2];

            while (rs.next()) {
                arr[0] = rs.getString(1); //name
                arr[1] = rs.getInt(2); // age
            }

            return new Profile((String)arr[0],(int)arr[1]);

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static boolean addProfile( Profile profile){

        String ProfileId = profile.getName();
        //String email = profile.getEmail();
        String name = profile.getName();
        int age = profile.getAge();


        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "INSERT INTO Profile (Name,Age) values(?,?)"
            );

            //pdo.setString(1,email);
            pdo.setString(2,name);
            pdo.setInt(3,age);
            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static boolean editProfile(Profile profile){

        String NewName = profile.getName();
        int NewAge = profile.getAge();

        Profile oldProfile =  getProfile(profile.getName());
        String OldName = oldProfile.getName();
        int OldAge = oldProfile.getAge();


        StringBuilder setStatementStringBuilder = new StringBuilder();

        // Beware. This code has the same lame SQLi as in the AccountDAO.

        if(!OldName.equals(NewName)){
            setStatementStringBuilder.append(
                    String.format("Name='%s',",NewName)
            );
        }
        if(!(OldAge == NewAge)){
            setStatementStringBuilder.append(
                    String.format("Age='%s',",NewAge )
            );
        }

        String setStatementString = setStatementStringBuilder.toString();
        String setStatementStringSliced =
                setStatementString.substring(0, setStatementString.length() - 1);

        //setStatementStringStrSliced += " WHERE uniqueId=<uniqueId>";
        setStatementStringSliced += String.format(
                " WHERE Name='%s'",profile.getName());


        try{
            PreparedStatement pdo = connection.prepareStatement(
                    setStatementStringSliced
            );

            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static boolean deleteProfile(int ProfileId){


        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "DELETE FROM table_name WHERE ProfileId=?"
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
    Todo
    public static boolean setBekeken(){}

    public static boolean getBekeken(){}
    */

}
