package fxmlProject.profile;

import DataBase.DataBaseConnection;
import fxmlProject.mainClass;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddNewProfile {

    @FXML
    TextField profileName;
    @FXML
    Button okButton;
    private mainClass main;
    private DataBaseConnection dataBaseConnection = new DataBaseConnection();
    private Connection connection = dataBaseConnection.connect();

    @FXML
    private void initialize() {
        okButton.setDisable(false);
    }

    @FXML
    private boolean verifyProfileName() {
        String profileNameText = profileName.getText();
        if (profileNameText.length() < 3) {
            profileName.clear();
            profileName.setText("Invalid name!");
            profileName.selectAll();
            okButton.setDisable(false);
            return false;
        }
        for (int index = 0; index < profileNameText.length(); index++) {
            if (!Pattern.matches("[a-zA-Z ]+", profileNameText)) {
                profileName.clear();
                profileName.setText("Invalid name!");
                profileName.selectAll();
                okButton.setDisable(false);
                return false;
            }
        }
        enableOk();
        return true;
    }

    @FXML
    private void enableOk() {
        okButton.setDisable(false);
    }

    @FXML
    private void closeScene() throws IOException {
        main.showMainItems();
    }

    @FXML
    private void addProfile() throws SQLException, IOException {
        PreparedStatement profileInsert = connection.prepareStatement("call insertGroupType(?)");
        profileInsert.setString(1, profileName.getText());
        profileInsert.executeQuery();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Added Successfully!");
        alert.showAndWait();
        Refresh();
    }
    @FXML
    private void goToAdminScene() throws IOException {
        main.showAdminView();
    }
    @FXML
    private void Refresh() throws IOException {
        main.showAddNewProfile();
    }
}
