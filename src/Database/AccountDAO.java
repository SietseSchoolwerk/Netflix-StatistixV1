package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Domain.Account;


public class AccountDAO {

    private static Connection connection = DatabaseConnection.getConn();

    public void AccountDAO(Connection connection) { this.connection = connection; }

    public static Account getAccount(String email){

        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT Email,Password,Subscriber,Address,City " +
                            "FROM Account " +
                            "WHERE Email=?"
            );
            pdo.setString(1,email);
            ResultSet rs = pdo.executeQuery();

            //Account account = new Account();

            String[] arr = new String[5];
            while (rs.next()) {

                arr[0] = rs.getString(1); // email
                arr[1] = rs.getString(2); // passw
                arr[2] = rs.getString(3); // subscriber
                arr[3] = rs.getString(4); // address
                arr[4] = rs.getString(5); // city
            }

            return new Account(arr[0],arr[1],arr[2],arr[3],arr[4]);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean addAccount(Account account){

        String Email = account.getEmail();
        String Password = account.getPassword();
        String Subscriber = account.getSubscriber();
        String Address = account.getAddress();
        String City = account.getCity();

        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "INSERT INTO Account (Email,Password,Subscriber,Address,City) " +
                            " values(?,?,?,?,?)"
            );
            pdo.setString(1,Email);
            pdo.setString(2,Password);
            pdo.setString(3,Subscriber);
            pdo.setString(4,Address);
            pdo.setString(5,City);
            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }


    }

    public static boolean deleteAccount(String email){


        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "DELETE FROM Account WHERE Email=?"
            );
            pdo.setString(1,email);
            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public static boolean editAccount(Account newAccount,String accountEmailToEdit) {

        /*
            This class is vulnerable to SQLi.
            Should either come up with a way to still generate the query dynamically
            or sanitize the arguments.
         */

        StringBuilder setStatementStringBuilder = new StringBuilder();

        setStatementStringBuilder.append("UPDATE Account SET ");
        Account existingAccount = getAccount(accountEmailToEdit);
        //String ExistingEmail = existingAccount.getEmail();
        String ExistingPassword = existingAccount.getPassword();
        String ExistingSubscriber = existingAccount.getSubscriber();
        String ExistingAddress = existingAccount.getAddress();
        String ExistingCity = existingAccount.getCity();

        String NewEmail = newAccount.getEmail();
        String NewPassword = newAccount.getPassword();
        String NewSubscriber = newAccount.getSubscriber();
        String NewAddress = newAccount.getAddress();
        String NewCity = newAccount.getCity();



//         if (!ExistingEmail.equals(NewEmail)) {
//             setStatementStringBuilder.append(
//                    String.format("Email='%s',", NewEmail)
//            );
//        }
        if (!ExistingPassword.equals(NewPassword)) {
            setStatementStringBuilder.append(
                    String.format("Password='%s',", NewPassword)
            );
        }
        if (!ExistingSubscriber.equals(NewSubscriber)) {
            setStatementStringBuilder.append(
                    String.format("Subscriber='%s',", NewSubscriber)
            );
        }
        if (!ExistingAddress.equals(NewAddress)) {
            setStatementStringBuilder.append(
                    String.format("Address='%s',", NewAddress)
            );
        }
        if (!ExistingCity.equals(NewCity)) {
            setStatementStringBuilder.append(
                    String.format("City='%s',", NewCity)
            );
        }
        String setStatementString = setStatementStringBuilder.toString();
        String setStatementStringSliced =
                setStatementString.substring(0, setStatementString.length() - 1);

        //setStatementStringStrSliced += " WHERE uniqueId=<uniqueId>";
        setStatementStringSliced += String.format(" WHERE Email='%s'",accountEmailToEdit);

        try{
            System.out.println(setStatementStringSliced);
            PreparedStatement pdo = connection.prepareStatement(setStatementStringSliced);
            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }



}