package Controllers;

import Database.ProfileDAO;
import Domain.Profile;
import GUI.Profiles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ProfileController implements EventHandler<ActionEvent> {
    private TextField txtNameProfile;
    private TextField txtAgeProfile;

    private Stage stage;
    private Profile profile;

    public ProfileController(Stage stage, Profile profile) {
        this.stage = stage;
        this.profile = profile;
    }

    public void handle(ActionEvent event) {
        Button btn = null;
        if (event.getSource().toString().toLowerCase().contains("button")) {
            btn = (Button) event.getTarget();
            if (btn.getId().equals("btnSubmit")) {
                this.handleEdit();
            } else if (btn.getId().equals("btnDelete")) {
                this.handleDelete();
            }
        }
    }

    public void handleEdit() {
        this.profile.setAge(Integer.parseInt(txtAgeProfile.getText()));
        this.profile.setName(txtNameProfile.getText());

        Boolean result = new ProfileDAO().editProfile(this.profile);
        String attachedEmail = new ProfileDAO().getEmailWithProfileId(this.profile.getProfileId());
        this.stage.setScene(new Profiles().profileList(this.stage, attachedEmail));
    }

    public void handleDelete() {
        ProfileDAO dao = new ProfileDAO();
        String attachedEmail = dao.getEmailWithProfileId(this.profile.getProfileId());

        int count = dao.profileCounter(attachedEmail);
        if (count == 1) {

        } else {
            dao.deleteProfile(this.profile);
        }
        this.stage.setScene(new Profiles().profileList(this.stage, attachedEmail));
    }

    public TextField getTxtNameProfile() {
        return txtNameProfile;
    }

    public void setTxtNameProfile(TextField txtNameProfile) {
        this.txtNameProfile = txtNameProfile;
    }

    public TextField getTxtAgeProfile() {
        return txtAgeProfile;
    }

    public void setTxtAgeProfile(TextField txtAgeProfile) {
        this.txtAgeProfile = txtAgeProfile;
    }
}