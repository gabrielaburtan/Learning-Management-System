package fxmlProject.student;

import DataBase.DataBaseConnection;
import fxmlProject.mainClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import java.util.regex.Pattern;

public class AddStudent {

    ObservableList<String> GroupList = FXCollections.observableArrayList();
    ObservableList<String> GroupTypeList = FXCollections.observableArrayList();
    @FXML
    TextField studentName;
    @FXML
    TextField studentEmail;
    @FXML
    DatePicker addStartDate;
    @FXML
    ComboBox addGroup;
    @FXML
    ComboBox addGroupType;
    @FXML
    Button okButton;
    private mainClass main;
    private DataBaseConnection dataBaseConnection = new DataBaseConnection();
    private Connection connection = dataBaseConnection.connect();

    @FXML
    private boolean verifyStudentName() {
        String email = studentEmail.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String studentNames = studentName.getText();
        if (studentNames.length() < 3) {
            studentName.clear();
            studentName.setText("Invalid name!");
            studentName.selectAll();
            studentEmail.setDisable(false);
            addStartDate.setDisable(true);
            addGroup.setDisable(true);
            addGroupType.setDisable(true);
            okButton.setDisable(true);
            return false;
        }
        for (int index = 0; index < studentNames.length(); index++) {
            if (!Pattern.matches("[a-zA-Z -]+", studentNames)) {
                studentName.clear();
                studentName.setText("Invalid name!");
                studentName.selectAll();
                studentEmail.setDisable(false);
                addStartDate.setDisable(true);
                addGroup.setDisable(true);
                addGroupType.setDisable(true);
                okButton.setDisable(true);
                return false;
            }
        }
        enableEmail();
        return true;
    }

    @FXML
    private boolean verifyEmailInput() {
        String email = studentEmail.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Added Successfully!");

        if (email.length() < 3) {
            studentEmail.clear();
            studentEmail.setText("Invalid email");
            studentEmail.selectAll();
            addStartDate.setDisable(false);
            addGroup.setDisable(true);
            addGroupType.setDisable(true);
            okButton.setDisable(true);
            return false;
        }
        if (email.charAt(0) == '@') {
            studentEmail.clear();
            studentEmail.setText("Invalid email");
            studentEmail.selectAll();
            addStartDate.setDisable(false);
            addGroup.setDisable(true);
            addGroupType.setDisable(true);
            okButton.setDisable(true);
            return false;
        }
        if (email.charAt(email.length() - 1) == '.') {
            studentEmail.clear();
            studentEmail.setText("Invalid email");
            studentEmail.selectAll();
            addStartDate.setDisable(false);
            addGroup.setDisable(true);
            addGroupType.setDisable(true);
            okButton.setDisable(true);
            return false;
        }
        boolean ok = false;
        for (int index = 0; index < email.length(); index++) {
            if (email.charAt(index) == '@') {
                if (email.charAt(index + 1) == '.') {
                    studentEmail.clear();
                    studentEmail.setText("Invalid email");
                    studentEmail.selectAll();
                    addStartDate.setDisable(false);
                    addGroup.setDisable(true);
                    addGroupType.setDisable(true);
                    okButton.setDisable(true);
                    return false;
                }
                ok = true;
            }
            if (email.charAt(index) == '.' && ok) {
                enableDate();
                return true;
            }
        }
        studentEmail.clear();
        studentEmail.setText("Invalid email");
        alert.showAndWait();
        studentEmail.selectAll();
        addStartDate.setDisable(false);
        addGroup.setDisable(true);
        addGroupType.setDisable(true);
        okButton.setDisable(true);
        return false;
    }

    @FXML
    private void closeScene() throws IOException {
        main.showMainItems();
    }

    @FXML
    private void initialize() throws SQLException {

        Statement selectProfileStatement = connection.createStatement();
        ResultSet result = selectProfileStatement.executeQuery("call selectNameFromGroupType()");
        while (result.next())
            GroupTypeList.add(result.getString(1));

        addGroupType.setItems(GroupTypeList);
        studentEmail.setDisable(false);
        addStartDate.setDisable(false);
        addGroup.setDisable(false);
        addGroupType.setDisable(false);
        okButton.setDisable(false);
    }

    @FXML
    private void enableEmail() {
        studentEmail.setDisable(false);
        addStartDate.setDisable(true);
        addGroup.setDisable(true);
        addGroupType.setDisable(true);
        okButton.setDisable(true);
    }

    @FXML
    private void enableDate() {
        addStartDate.setDisable(false);
        addGroup.setDisable(true);
        addGroupType.setDisable(true);
        okButton.setDisable(true);
    }

    @FXML
    private void enableGroup() {
        addGroup.setDisable(false);
        okButton.setDisable(true);
    }

    @FXML
    private void enableProfile() {
        addGroupType.setDisable(false);
        addGroup.setDisable(true);
        okButton.setDisable(true);
    }

    @FXML
    private void enableOk() {
        okButton.setDisable(false);
    }

    @FXML
    private void getGroupList() throws SQLException {
        GroupList.clear();

        PreparedStatement selectGroupByProfileStatement = connection.prepareStatement("call selectGroupByType(?)");
        selectGroupByProfileStatement.setString(1, addGroupType.getSelectionModel().getSelectedItem().toString());
        ResultSet result = selectGroupByProfileStatement.executeQuery();
        while (result.next()) {
            GroupList.add(result.getString(1));
        }

        addGroup.setItems(GroupList);
        enableGroup();
    }

    @FXML
    private void addStudent() throws SQLException, IOException {

        int year = addStartDate.getValue().getYear();
        int mounts = addStartDate.getValue().getMonthValue();
        int day = addStartDate.getValue().getDayOfMonth();

        String data = Integer.toString(year);
        data += '-';
        data += Integer.toString(mounts);
        data += '-';
        data += Integer.toString(day);

        PreparedStatement studentInsert = connection.prepareStatement("call insertIntoStudent(?,?)");
        studentInsert.setString(1, studentName.getText());
        studentInsert.setString(2, studentEmail.getText());
        studentInsert.executeQuery();

        PreparedStatement studentGroupInsert = connection.prepareStatement("call insertIntoStudentGroup(?,?,?)");
        studentGroupInsert.setString(1, studentEmail.getText());
        studentGroupInsert.setString(2, addGroup.getSelectionModel().getSelectedItem().toString());
        studentGroupInsert.setString(3, data);
        studentGroupInsert.executeQuery();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Added Successfully!");
        alert.showAndWait();
        Refresh();
    }
    @FXML
    private void goToStudentScene() throws IOException {
        main.showStudentScene();
    }
    @FXML
    private void Refresh() throws IOException {
        main.showAddStudentScene();
    }
}
