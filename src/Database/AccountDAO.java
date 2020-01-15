package Database;

import Domain.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountDAO {
    private Connection connection;

    public AccountDAO(Connection connection) {
        this.connection = DatabaseConnection.getConn();
    }

    public ArrayList<Account> getAllAccounts() {
        String sql = "SELECT * FROM Account;";

        try {
            ArrayList<Account> result = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet r = statement.executeQuery();
            while(r.next()) {
                result.add(new Account(r.getString("Email"),r.getString("Password"),r.getString("Subscriber"),r.getString("Address"), r.getString("City")));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccount(String email){

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

    public boolean addAccount(Account account){

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

    public boolean deleteAccount(String email){


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

    public boolean editAccount(Account newAccount,String accountEmailToEdit) {


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