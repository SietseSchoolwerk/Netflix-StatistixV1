package Database;

import Domain.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/*

    Deze Class bevat alle methods die met de Account table te maken hebben.

    De method: getAllAccounts geeft een ArrayList met Account objecten terug van \
    de records in de database.

    De method: getAccount geeft een Account object terug, met als attributen \
    de records uit de database

    De method: addAccount neemt een Account object en voegd de attributen van dit\
    object toe als records in de database.

    De method deleteAccount verwijderd records met als unique identifier de email

    De method editAccount past een record in de database aan naar de nieuwe attributen\
    van het bijbehorende object.


 */


public class AccountDAO {
    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public AccountDAO() {
        this.connection = databaseConnection.getConn();
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

    public ArrayList<Account> getAllAccountsWithOneProfile() {
        String sql = "SELECT Account.Email, Password, Subscriber, Address, City FROM Profile INNER JOIN Account ON Account.Email = Profile.Email GROUP BY Account.Email, Password, Subscriber, Address, City Having COUNT(*) = 1";

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
            PreparedStatement pdo = this.connection.prepareStatement(
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
        try{
            PreparedStatement pdo = this.connection.prepareStatement(
                    "INSERT INTO Account (Email,Password,Subscriber,Address,City) " +
                            " values(?,?,?,?,?)"
            );
            pdo.setString(1,account.getEmail());
            pdo.setString(2,account.getPassword());
            pdo.setString(3,account.getSubscriber());
            pdo.setString(4,account.getAddress());
            pdo.setString(5,account.getCity());
            pdo.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }


    }

    public boolean deleteAccount(String email){
        String sql = "DELETE Account WHERE Email = ?;";

        try{
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1,email);
            statement.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean editAccount(Account newAccount) {
        String sql = "UPDATE Account SET Password=?,Subscriber=?,Address=?,City=? WHERE Email=?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,newAccount.getPassword());
            statement.setString(2,newAccount.getSubscriber());
            statement.setString(3,newAccount.getAddress());
            statement.setString(4,newAccount.getCity());
            statement.setString(5,newAccount.getEmail());
            statement.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }



}