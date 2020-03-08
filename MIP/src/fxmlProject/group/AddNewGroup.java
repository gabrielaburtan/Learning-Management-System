package fxmlProject.group;

import DataBase.DataBaseConnection;
import fxmlProject.mainClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.util.regex.Pattern;

public class AddNewGroup {

    ObservableList<String> groupTypeList = FXCollections.observableArrayList();
    @FXML
    TextField groupName;
    @FXML
    ComboBox groupType;
    @FXML
    Button okButton;

    private mainClass main;
    private DataBaseConnection db = new DataBaseConnection();
    private Connection con = db.connect();

    @FXML
    private void closeScene() {
        mainClass.closeAddStudentScene();
    }

    @FXML
    private void initialize() throws SQLException {

        Statement selectGroupType = con.createStatement();
        ResultSet rs = selectGroupType.executeQuery("call selectNameFromGroupType()");
        while (rs.next())
            groupTypeList.add(rs.getString(1));

        groupType.setValue("Select");
        groupType.setItems(groupTypeList);

        groupType.setDisable(false);
        okButton.setDisable(false);
    }

    @FXML
    private boolean verifyGroupName() {
        String groupNameText = groupName.getText();
        if (groupNameText.length() < 3) {
            groupName.clear();
            groupName.setText("Invalid name!");
            groupName.selectAll();
            groupType.setDisable(false);
            okButton.setDisable(true);
            return false;
        }
        for (int index = 0; index < groupNameText.length(); index++) {
            if (!Pattern.matches("[a-zA-Z 0-9]+", groupNameText)) {
                groupName.clear();
                groupName.setText("Invalid name!");
                groupName.selectAll();
                groupType.setDisable(false);
                okButton.setDisable(true);
                return false;
            }
        }
        enableGroupType();
        return true;
    }

    private void enableGroupType() {
        groupType.setDisable(false);
        okButton.setDisable(true);
    }

    @FXML
    private void enableOk() {
        okButton.setDisable(false);
    }


    @FXML
    private void addGroup() throws SQLException, IOException {
        PreparedStatement groupInsert = con.prepareStatement("call insertGroup(?,?)");
        groupInsert.setString(1, groupName.getText());
        groupInsert.setString(2, groupType.getSelectionModel().getSelectedItem().toString());
        groupInsert.executeQuery();

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
        main.showAddNewGroup();
    }
}
