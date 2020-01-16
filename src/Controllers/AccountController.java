package Controllers;

import Database.AccountDAO;
import Domain.Account;
import GUI.Accounts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AccountController implements EventHandler<ActionEvent> {
    private TextField txtEmailAccount;
    private TextField txtPasswordAccount;
    private TextField txtSubscriberAccount;
    private TextField txtAddressAccount;
    private TextField txtCityAccount;

    private Stage stage;
    private Account account;

    public AccountController(Stage stage, Account account) {
        this.account = account;
        this.stage = stage;
    }

    public void handle(ActionEvent event) {
        Button btn = null;
        if (event.getSource().toString().toLowerCase().contains("button")) {
            btn = (Button) event.getTarget();
            if (btn.getId().equals("btnSubmit")) {
                this.handleEdit();
            } else if (btn.getId().equals("btnDelete") && this.account != null) {
                this.handleDelete(this.account);
            } else if (btn.getId().equals("btnAddAccount") && this.account != null){
                Account account = new Account(txtEmailAccount.getText(), txtPasswordAccount.getText(), txtSubscriberAccount.getText(), txtAddressAccount.getText(), txtCityAccount.getText());
                this.handleAdd(account);
            }
        }
    }

    public void handleEdit() {
        this.account.setPassword(txtPasswordAccount.getText());
        this.account.setSubscriber(txtSubscriberAccount.getText());
        this.account.setAddress(txtAddressAccount.getText());
        this.account.setCity(txtCityAccount.getText());

        Boolean result = new AccountDAO().editAccount(this.account);
        this.stage.setScene(new Accounts().AccountList(this.stage));
    }

    public void handleDelete(Account account) {
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.deleteAccount(account.getEmail());
        this.stage.setScene(new Accounts().AccountList(this.stage));
    }

    public void handleAdd(Account account) {
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.addAccount(account);
        this.stage.setScene(new Accounts().AccountList(this.stage));
    }

    public TextField getTxtEmailAccount() {
        return txtEmailAccount;
    }

    public void setTxtEmailAccount(TextField txtEmailAccount) {
        this.txtEmailAccount = txtEmailAccount;
    }

    public TextField getTxtPasswordAccount() {
        return txtPasswordAccount;
    }

    public void setTxtPasswordAccount(TextField txtPasswordAccount) {
        this.txtPasswordAccount = txtPasswordAccount;
    }

    public TextField getTxtSubscriberAccount() {
        return txtSubscriberAccount;
    }

    public void setTxtSubscriberAccount(TextField txtSubscriberAccount) {
        this.txtSubscriberAccount = txtSubscriberAccount;
    }

    public TextField getTxtAddressAccount() {
        return txtAddressAccount;
    }

    public void setTxtAddressAccount(TextField txtAddressAccount) {
        this.txtAddressAccount = txtAddressAccount;
    }

    public TextField getTxtCityAccount() {
        return txtCityAccount;
    }

    public void setTxtCityAccount(TextField txtCityAccount) {
        this.txtCityAccount = txtCityAccount;
    }
}
