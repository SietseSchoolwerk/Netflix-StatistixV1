package Database;

import Domain.Account;
import Domain.Film;
import Domain.Program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class AccountDAO {
    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    // Default constructor for the AccountDAO class
    public AccountDAO() {
        this.connection = databaseConnection.getConn();
    }

    /**
     * Get an array of all the Accounts that are in the database.
     * @return the ArrayList with Account objects
     */
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

    /**
     * Gets all of the account that have one profile.
     * @return an ArrayList with the accounts that match the query
     */
    public ArrayList<Account> getAllAccountsWithOneProfile() {
        String sql = "SELECT Account.Email, Password, Subscriber, Address, City FROM Profile " +
                                    "INNER JOIN Account ON Account.Email = Profile.Email " +
                                    "GROUP BY Account.Email, Password, Subscriber, Address, City " +
                                    "Having COUNT(*) = 1";

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

    /**
     * Executes a query that gets all watched movies for account.
     * @param email
     * @return an ArrayList with Program objects that have been watched
     */
    public ArrayList<Program> getAllWatchedMovies(String email) {
        String sql = "SELECT * FROM Watched " +
                "INNER JOIN Programma " +
                "ON Watched.ProgramId=Programma.ProgramId " +
                "INNER JOIN Profile " +
                "ON Profile.ProfileId = Watched.ProfileId " +
                "INNER JOIN Account " +
                "ON Account.Email = Profile.Email " +
                "WHERE Profile.Email = ? " +
                "AND  NOT Programma.ProgramId " +
                " IN (SELECT Episode.ProgramId FROM Episode) ";

        try {
            ArrayList<Program> result = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet r = statement.executeQuery();

            while(r.next()) {
                result.add(new Film(r.getInt("ProgramId"),r.getString("Title"),r.getString("Genre"),r.getString("Language"), r.getString("playtime"), r.getInt("Age")));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns an Account object for a given email.
     * @param email
     * @return an Account object
     */
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

    /**
     * Gets an account object, and puts all of the attributes in the database.
     * @param account
     * @return true if query executed without errors. False if there were exceptions.
     */
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

    /**
     * Deletes an account from the database for a given email.
     * @param email
     * @return true if query executed without errors. False if there were exceptions.
     */
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

    /**
     * Takes an object and edits the table in the database to the new attributes.
     * @param newAccount
     * @return true if query executed without errors. False if there were exceptions.
     */
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