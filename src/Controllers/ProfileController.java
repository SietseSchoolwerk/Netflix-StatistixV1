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

    // Default constructor for the ProfileController
    public ProfileController(Stage stage, Profile profile) {
        this(stage);
        this.profile = profile;
    }

    /**
     * Show an error
     * @param stage
     */
    public ProfileController(Stage stage) {
        this.stage = stage;
        this.stage = stage;
        this.alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Error occured with profile");
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


    /**
     * Handle an error and show an alert with the parameter text
     * @param txt
     */
    public void handleError(String txt) {
        alert.setContentText(txt);
        alert.showAndWait();
        return;
    }


    /**
     * Delete a profile from the database
     */
    public void handleDeleteWatched() {
        ProfileDAO dao = new ProfileDAO();
        if (!dao.deleteWatched(watch.getWatchedId())) {
            handleError("Database Error");
            return;
        }
        this.stage.setScene(new Profiles().profileList(this.stage, this.email));
    }

    /**
     * Handle updating watched, and update it in the database through the matching DAO class
     */
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

    /**
     * Handle adding a new watched to the database
     */
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

    /**
     * Add a new profile to the database
     */
    public void handleAdd() {
        ProfileDAO dao = new ProfileDAO();
        Profile profile = new Profile(txtNameProfile.getText(), Integer.parseInt(txtAgeProfile.getText()), this.email);

        if (!dao.addProfile(profile)) {
            handleError("Database error");
            return;
        }
        this.stage.setScene(new Profiles().profileList(this.stage, this.email));
    }

    /**
     * Edit the profile object, if the data is in the correct format.
     */
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

    /**
     * Handle the deleteion of a profile, and call the matching DAO class to delte it from the database
     */
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

    /**
     * get the textNameProfile textfield
     * @return
     */
    public TextField getTxtNameProfile() {
        return txtNameProfile;
    }

    /**
     * Set the text in the nameProfile textfield
     * @param txtNameProfile
     */
    public void setTxtNameProfile(TextField txtNameProfile) {
        this.txtNameProfile = txtNameProfile;
    }

    /**
     * Get the ageProfile textfield
     * @return the ageProfile textfield
     */
    public TextField getTxtAgeProfile() {
        return txtAgeProfile;
    }

    /**
     * Set the text in the profile textfield
     * @param txtAgeProfile
     */
    public void setTxtAgeProfile(TextField txtAgeProfile) {
        this.txtAgeProfile = txtAgeProfile;
    }

    /**
     * get the email attribute
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * set the email attribute
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the profile attribute
     * @return the profile attribute
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     *  set the profile attribute to a new Profile object
     * @param profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * get the programId attribute
     * @return programId
     */
    public int getProgramId() {
        return programId;
    }

    /**
     * Set the programId attribute
     * @param programId
     */
    public void setProgramId(int programId) {
        this.programId = programId;
    }

    /**
     * Get the percentage attribute
     * @return the percentage Textfield
     */
    public TextField getTxtPercentage() {
        return txtPercentage;
    }

    /**
     * Set the text in the percentage textfield
     * @param txtPercentage
     */
    public void setTxtPercentage(TextField txtPercentage) {
        this.txtPercentage = txtPercentage;
    }

    /**
     * Get the watch attribute
     * @return the watch attribute object
     */
    public Watched getWatch() {
        return watch;
    }

    /**
     * Set the watch attribute to a new Watched object
     * @param watch
     */
    public void setWatch(Watched watch) {
        this.watch = watch;
    }
}