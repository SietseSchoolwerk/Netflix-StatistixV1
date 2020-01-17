package Controllers;

import Database.AccountDAO;
import Domain.Account;
import GUI.Accounts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AccountController implements EventHandler<ActionEvent> {
    private TextField txtEmailAccount;
    private TextField txtPasswordAccount;
    private TextField txtSubscriberAccount;
    private TextField txtAddressAccount;
    private TextField txtCityAccount;

    private Alert alert;

    private Stage stage;
    private Account account;

    // Default constructor for the AccountController
    public AccountController(Stage stage, Account account) {
        this(stage);
        this.account = account;
    }

    /**
     *
     * @param stage
     */
    public AccountController(Stage stage) {
        this.stage = stage;
        this.alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Error occured with account");
    }

    /**
     * Handle ActionEvents
     * @param event
     */
    public void handle(ActionEvent event) {
        Button btn = null;
        if (event.getSource().toString().toLowerCase().contains("button")) {
            btn = (Button) event.getTarget();
            if (btn.getId().equals("btnSubmit")) {
                this.handleEdit();
            } else if (btn.getId().equals("btnDelete") && this.account != null) {
                this.handleDelete(this.account);
            } else if (btn.getId().equals("btnAddAccount")){
                Account account = new Account(txtEmailAccount.getText(), txtPasswordAccount.getText(), txtSubscriberAccount.getText(), txtAddressAccount.getText(), txtCityAccount.getText());
                this.handleAdd(account);
            }
        }
    }

    /**
     * Handle errors
     * @param txt
     */
    public void handleError(String txt) {
        alert.setContentText(txt);
        alert.showAndWait();
        return;
    }


    public void handleEdit() {
        if (!this.account.setPassword(txtPasswordAccount.getText())) {
            handleError("Password is an incorrect format");
            return;
        }

        if (!this.account.setSubscriber(txtSubscriberAccount.getText())) {
            handleError("Subscriber is an incorrect format");
            return;
        }

        if (!this.account.setAddress(txtAddressAccount.getText())) {
            handleError("Address is an incorrect format");
            return;
        }

        if (!this.account.setCity(txtCityAccount.getText())) {
            handleError("City is an incorrect format");
            return;
        }

        Boolean result = new AccountDAO().editAccount(this.account);

        if (!result) {
            handleError("Database error");
            return;
        }

        this.stage.setScene(new Accounts().AccountList(this.stage));
    }

    /**
     *
     * @param account
     */
    public void handleDelete(Account account) {
        AccountDAO accountDAO = new AccountDAO();

        if (!accountDAO.deleteAccount(account.getEmail())) {
            handleError("Database error");
            return;
        }

        this.stage.setScene(new Accounts().AccountList(this.stage));
    }

    /**
     *
     * @param account
     */
    public void handleAdd(Account account) {
        AccountDAO accountDAO = new AccountDAO();

        if (accountDAO.getAccount(account.getEmail()).getEmail() != null){
            handleError("This is email is already in use");
            return;
        }

        if(!account.setEmail(account.getEmail())){
            handleError("This is not a valid email");
            return;
        }


        if (!account.setPassword(account.getPassword())) {
            handleError("Password is an incorrect format");
            return;
        }

        if (!account.setPassword(account.getPassword())) {
            handleError("Password is an incorrect format");
            return;
        }

        if (!account.setSubscriber(account.getSubscriber())) {
            handleError("Subscriber is an incorrect format");
            return;
        }

        if (!account.setAddress(account.getAddress())) {
            handleError("Address is an incorrect format");
            return;
        }

        if (!account.setCity(txtCityAccount.getText())) {
            handleError("City is an incorrect format");
            return;
        }

        if (!accountDAO.addAccount(account)) {
            handleError("Database error");
            return;
        }

        this.stage.setScene(new Accounts().AccountList(this.stage));
    }

    /**
     *
     * @param txtEmailAccount
     */
    public void setTxtEmailAccount(TextField txtEmailAccount) {
        this.txtEmailAccount = txtEmailAccount;
    }

    /**
     *
     * @param txtPasswordAccount
     */
    public void setTxtPasswordAccount(TextField txtPasswordAccount) {
        this.txtPasswordAccount = txtPasswordAccount;
    }

    /**
     *
     * @param txtSubscriberAccount
     */
    public void setTxtSubscriberAccount(TextField txtSubscriberAccount) {
        this.txtSubscriberAccount = txtSubscriberAccount;
    }

    /**
     *
     * @param txtAddressAccount
     */
    public void setTxtAddressAccount(TextField txtAddressAccount) {
        this.txtAddressAccount = txtAddressAccount;
    }

    /**
     *
     * @param txtCityAccount
     */
    public void setTxtCityAccount(TextField txtCityAccount) {
        this.txtCityAccount = txtCityAccount;
    }
}
