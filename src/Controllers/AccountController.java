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


public class AccountController implements EventHandler<ActionEvent> {
    private TextField txtEmailAccount;
    private TextField txtPasswordAccount;
    private TextField txtSubscriberAccount;
    private TextField txtAddressAccount;
    private TextField txtCityAccount;

    private Alert alert;

    private Stage stage;
    private Account account;

    public AccountController(Stage stage, Account account) {
        this(stage);
        this.account = account;
    }

    public AccountController(Stage stage) {
        this.stage = stage;
        this.alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Error occured while creating account");
    }

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

    public void handleDelete(Account account) {
        AccountDAO accountDAO = new AccountDAO();

        if (!accountDAO.deleteAccount(account.getEmail())) {
            handleError("Database error");
            return;
        }

        this.stage.setScene(new Accounts().AccountList(this.stage));
    }

    public void handleAdd(Account account) {
        AccountDAO accountDAO = new AccountDAO();

        if (!accountDAO.addAccount(account)) {
            handleError("Database error");
            return;
        }
        this.stage.setScene(new Accounts().AccountList(this.stage));
    }

    public void setTxtEmailAccount(TextField txtEmailAccount) {
        this.txtEmailAccount = txtEmailAccount;
    }

    public void setTxtPasswordAccount(TextField txtPasswordAccount) {
        this.txtPasswordAccount = txtPasswordAccount;
    }

    public void setTxtSubscriberAccount(TextField txtSubscriberAccount) {
        this.txtSubscriberAccount = txtSubscriberAccount;
    }

    public void setTxtAddressAccount(TextField txtAddressAccount) {
        this.txtAddressAccount = txtAddressAccount;
    }


    public void setTxtCityAccount(TextField txtCityAccount) {
        this.txtCityAccount = txtCityAccount;
    }
}
