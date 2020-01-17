package Controllers;

import Database.ProfileDAO;
import Database.ProgramDAO;
import Domain.Profile;
import Domain.Watched;
import GUI.Profiles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ProfileController implements EventHandler<ActionEvent> {
    private TextField txtNameProfile;
    private TextField txtAgeProfile;
    private TextField txtPercentage;
    private String email;

    private Alert alert;

    private Stage stage;
    private Profile profile;
    private Watched watch;

    private int programId;
    public ProfileController(Stage stage, Profile profile) {
        this(stage);
        this.profile = profile;
    }

    public ProfileController(Stage stage) {
        this.stage = stage;
        this.stage = stage;
        this.alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Error occured with profile");
    }

    public void handle(ActionEvent event) {
        Button btn = null;
        if (event.getSource().toString().toLowerCase().contains("button")) {
            btn = (Button) event.getTarget();
            if (btn.getId().equals("btnSubmit")) {
                this.handleEdit();
            } else if (btn.getId().equals("btnDelete")) {
                this.handleDelete();
            } else if (btn.getId().equals("btnAdd")) {
                this.handleAdd();
            } else if (btn.getId().equals("btnWatch")) {
                this.handleWatch();
            } else if (btn.getId().equals("btnUpdate")) {
                this.handleUpdateWatched();
            } else if (btn.getId().equals("btnDeleteWatched")) {
                this.handleDeleteWatched();
            }
        }
    }


    public void handleError(String txt) {
        alert.setContentText(txt);
        alert.showAndWait();
        return;
    }

    public void handleDeleteWatched() {
        ProfileDAO dao = new ProfileDAO();
        if (!dao.deleteWatched(watch.getWatchedId())) {
            handleError("Database Error");
            return;
        }
        this.stage.setScene(new Profiles().profileList(this.stage, this.email));
    }

    public void handleUpdateWatched() {
        ProfileDAO dao = new ProfileDAO();
        try {
            if (!dao.editWatched(watch.getWatchedId(), Integer.parseInt(this.txtPercentage.getText()))) {
                handleError("Database error");
                return;
            }
        } catch (Exception e) {
            handleError("Please only enter numbers");
            return;
        }
        this.stage.setScene(new Profiles().profileList(this.stage, this.email));
    }

    public void handleWatch() {
        ProfileDAO dao = new ProfileDAO();
        ProgramDAO programDao = new ProgramDAO();

        try {
            if (!dao.setWatched(this.profile, programDao.getProgram(this.programId), Integer.parseInt(this.txtPercentage.getText()))) {
                handleError("Database error");
                return;
            }
        } catch (Exception e) {
            handleError("Please only enter numbers");
            return;
        }
        this.stage.setScene(new Profiles().profileList(this.stage, this.email));
    }

    public void handleAdd() {
        ProfileDAO dao = new ProfileDAO();
        Profile profile = new Profile(txtNameProfile.getText(), Integer.parseInt(txtAgeProfile.getText()), this.email);

        if (!dao.addProfile(profile)) {
            handleError("Database error");
            return;
        }
        this.stage.setScene(new Profiles().profileList(this.stage, this.email));
    }

    public void handleEdit() {
        if (!this.profile.setAge(Integer.parseInt(txtAgeProfile.getText()))) {
            handleError("Age is an incorrect format");
            return;
        }
        if (!this.profile.setName(txtNameProfile.getText())) {
            handleError("Name is an incorrect format");
            return;
        }

        Boolean result = new ProfileDAO().editProfile(this.profile);
        if (!result) {
            handleError("Database error");
            return;
        }

        String attachedEmail = new ProfileDAO().getEmailWithProfileId(this.profile.getProfileId());

        this.stage.setScene(new Profiles().profileList(this.stage, attachedEmail));
    }

    public void handleDelete() {
        ProfileDAO dao = new ProfileDAO();
        String attachedEmail = dao.getEmailWithProfileId(this.profile.getProfileId());

        int count = dao.profileCounter(attachedEmail);
        if (count == 1) {
            handleError("An account needs to have atleast one profile");
            return;
        } else {
            if (!dao.deleteProfile(this.profile)) {
                handleError("City is an incorrect format");
                return;
            }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public TextField getTxtPercentage() {
        return txtPercentage;
    }

    public void setTxtPercentage(TextField txtPercentage) {
        this.txtPercentage = txtPercentage;
    }

    public Watched getWatch() {
        return watch;
    }

    public void setWatch(Watched watch) {
        this.watch = watch;
    }
}