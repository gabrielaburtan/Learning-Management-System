package fxmlProject.teacher;

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

public class AddTeacher {

    ObservableList<String> SubjectsList = FXCollections.observableArrayList();

    @FXML
    TextField teacherName;
    @FXML
    TextField teacherEmail;
    @FXML
    ComboBox subject;
    @FXML
    Button okButton;

    private mainClass main;
    private DataBaseConnection dataBaseConnection = new DataBaseConnection();
    private Connection connection = dataBaseConnection.connect();


    @FXML
    private void closeScene() throws IOException {
        main.closeAddStudentScene();
    }

    @FXML
    private void initialize() throws SQLException {

        Statement selectSubjectStatement = connection.createStatement();
        ResultSet result = selectSubjectStatement.executeQuery("select Name from subject");
        while (result.next())
            SubjectsList.add(result.getString(1));

        subject.setItems(SubjectsList);
        teacherEmail.setDisable(false);
        subject.setDisable(false);
        okButton.setDisable(false);
    }

    @FXML
    private boolean verifyTeacherName() {
        String teacherNames = teacherName.getText();
        if (teacherNames.length() < 3) {
            teacherName.clear();
            teacherName.setText("Invalid name!");
            teacherName.selectAll();
            teacherEmail.setDisable(false);
            subject.setDisable(true);
            okButton.setDisable(true);
            return false;
        }
        for (int index = 0; index < teacherNames.length(); index++) {
            if (!Pattern.matches("[a-zA-Z -]+", teacherNames)) {
                teacherName.clear();
                teacherName.setText("Invalid name!");
                teacherName.selectAll();
                teacherEmail.setDisable(false);
                subject.setDisable(true);
                okButton.setDisable(true);
                return false;
            }
        }
        enableEmail();
        return true;
    }

    private void enableEmail() {
        teacherEmail.setDisable(false);
        subject.setDisable(true);
        okButton.setDisable(true);
    }

    @FXML
    private boolean verifyEmailInput() {
        String email = teacherEmail.getText();
        if (email.length() < 3) {
            teacherEmail.clear();
            teacherEmail.setText("Invalid email");
            teacherEmail.selectAll();
            subject.setDisable(false);
            okButton.setDisable(true);
            return false;
        }
        if (email.charAt(0) == '@') {
            teacherEmail.clear();
            teacherEmail.setText("Invalid email");
            teacherEmail.selectAll();
            subject.setDisable(false);
            okButton.setDisable(true);
            return false;
        }
        if (email.charAt(email.length() - 1) == '.') {
            teacherEmail.clear();
            teacherEmail.setText("Invalid email");
            teacherEmail.selectAll();
            subject.setDisable(false);
            okButton.setDisable(true);
            return false;
        }
        boolean ok = false;
        for (int index = 0; index < email.length(); index++) {
            if (email.charAt(index) == '@') {
                if (email.charAt(index + 1) == '.') {
                    teacherEmail.clear();
                    teacherEmail.setText("Invalid email");
                    teacherEmail.selectAll();
                    subject.setDisable(false);
                    okButton.setDisable(true);
                    return false;
                }
                ok = true;
            }
            if (email.charAt(index) == '.' && ok) {
                enableGroup();
                return true;
            }
        }
        teacherEmail.clear();
        teacherEmail.setText("Invalid email");
        teacherEmail.selectAll();
        subject.setDisable(false);
        okButton.setDisable(true);
        return false;
    }

    private void enableGroup() {
        subject.setDisable(false);
        okButton.setDisable(true);
    }

    @FXML
    private void enableOk() {
        okButton.setDisable(false);
    }

    @FXML
    private void addTeacher() throws SQLException, IOException {

        String teacherMail = teacherEmail.getText();

        PreparedStatement selectTeacherEmailStatement = connection.prepareStatement("call selectTeacherByEmail(?)");
        selectTeacherEmailStatement.setString(1, teacherMail);
        ResultSet result = selectTeacherEmailStatement.executeQuery();

        Integer idTeacher = 0;

        while (result.next()) {
            idTeacher = result.getInt(1);
        }

        if (idTeacher != 0) {

            PreparedStatement teacherSubjectInsert = connection.prepareStatement("call insertIntoTeacherSubject(?,?)");
            teacherSubjectInsert.setString(1, teacherEmail.getText());
            teacherSubjectInsert.setString(2, subject.getValue().toString());
            teacherSubjectInsert.executeQuery();
        } else {

            PreparedStatement teacherInsert = connection.prepareStatement("call insertIntoProfesor(?,?)");
            teacherInsert.setString(1, teacherName.getText());
            teacherInsert.setString(2, teacherEmail.getText());
            teacherInsert.executeQuery();

            PreparedStatement teacherSubjectInsert = connection.prepareStatement("call insertIntoTeacherSubject(?,?)");
            teacherSubjectInsert.setString(1, teacherEmail.getText());
            teacherSubjectInsert.setString(2, subject.getValue().toString());
            teacherSubjectInsert.executeQuery();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Added Successfully!");
        alert.showAndWait();
        Refresh();
    }
    @FXML
    private void Refresh() throws IOException {
        main.showAddNewTeacher();
    }
    @FXML
    private void goToAdminScene() throws IOException {
        main.showAdminView();
    }
}
