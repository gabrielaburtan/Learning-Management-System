package fxmlProject.subject;

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

public class AddSubject {

    ObservableList<String> SubjectsList = FXCollections.observableArrayList();
    ObservableList<Integer> CreditList = FXCollections.observableArrayList();
    @FXML
    TextField subjectName;
    @FXML
    ComboBox credits;
    @FXML
    ComboBox subjectType;
    @FXML
    Button okButton;

    private mainClass main;
    private DataBaseConnection dataBaseConnection = new DataBaseConnection();
    private Connection connection = dataBaseConnection.connect();

    @FXML
    private boolean verifySubjectName() {
        String subjectNameText = subjectName.getText();
        if (subjectNameText.length() < 3) {
            subjectName.clear();
            subjectName.setText("Invalid name!");
            subjectName.selectAll();
            credits.setDisable(false);
            subjectType.setDisable(true);
            okButton.setDisable(true);
            return false;
        }
        for (int index = 0; index < subjectNameText.length(); index++) {
            if (!Pattern.matches("[a-zA-Z ]+", subjectNameText)) {
                subjectName.clear();
                subjectName.setText("Invalid name!");
                subjectName.selectAll();
                credits.setDisable(false);
                subjectType.setDisable(true);
                okButton.setDisable(true);
                return false;
            }
        }
        enableCredits();
        return true;
    }

    private void enableCredits() {
        credits.setDisable(false);
        subjectType.setDisable(true);
        okButton.setDisable(true);
    }

    @FXML
    private void enableProfile() {
        subjectType.setDisable(false);
        okButton.setDisable(true);
    }

    @FXML
    private void enableOk() {
        okButton.setDisable(false);
    }

    @FXML
    private void closeScene() throws IOException {
        main.closeAddStudentScene();
    }

    @FXML
    private void initialize() throws SQLException {

        Statement selectSubjectTypeStatement = connection.createStatement();
        ResultSet result = selectSubjectTypeStatement.executeQuery("call selectNameFromSubjectType()");
        while (result.next())
            SubjectsList.add(result.getString(1));

        subjectType.setValue("Select");
        subjectType.setItems(SubjectsList);

        CreditList.addAll(1, 2, 3, 4, 5, 6);
        credits.setValue("Select");
        credits.setItems(CreditList);

        credits.setDisable(false);
        subjectType.setDisable(false);
        okButton.setDisable(false);

    }

    @FXML
    private void addSubject() throws SQLException, IOException {
        int grade = credits.getSelectionModel().getSelectedIndex() + 1;

        PreparedStatement subjectInsertStatement = connection.prepareStatement("call insertintoSubject(?,?,?)");
        subjectInsertStatement.setString(1, subjectName.getText());
        subjectInsertStatement.setInt(2, grade);
        subjectInsertStatement.setString(3, subjectType.getSelectionModel().getSelectedItem().toString());
        subjectInsertStatement.executeQuery();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Added Successfully!");
        alert.showAndWait();
        Refresh();
    }

    @FXML
    private void goMain() throws IOException {
        main.showMainItems();
    }
    @FXML
    private void goToAdminScene() throws IOException {
        main.showAdminView();
    }
    @FXML
    private void Refresh() throws IOException {
        main.showAddNewSubject();
    }
}
