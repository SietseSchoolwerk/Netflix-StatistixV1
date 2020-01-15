package Database;

import Domain.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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

        StringBuilder setStatementStringBuilder = new StringBuilder();

        String sqlStr = "UPDATE Account SET Password=?,Subscriber=?,Address=?,City=? WHERE Email=?";

        try{
            PreparedStatement pdo = connection.prepareStatement(sqlStr);
            pdo.setString(1,newAccount.getPassword());
            pdo.setString(2,newAccount.getSubscriber());
            pdo.setString(3,newAccount.getAddress());
            pdo.setString(4,newAccount.getCity());
            pdo.setString(5,newAccount.getEmail());
            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }



}