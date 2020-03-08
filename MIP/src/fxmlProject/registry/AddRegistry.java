package fxmlProject.registry;

import DataBase.DataBaseConnection;
import DataBase.student.Student;
import DataBase.teachers.Teacher;
import fxmlProject.mainClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.sql.*;

public class AddRegistry {

    ObservableList<String> profileList = FXCollections.observableArrayList();
    ObservableList<String> groupList = FXCollections.observableArrayList();
    ObservableList<Student> studentList = FXCollections.observableArrayList();
    ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
    ObservableList<String> subjectList = FXCollections.observableArrayList();
    ObservableList<Integer> gradeList = FXCollections.observableArrayList();
    @FXML
    ComboBox profileName;
    @FXML
    ComboBox groupName;
    @FXML
    ComboBox studentName;
    @FXML
    ComboBox teacherName;
    @FXML
    ComboBox subjectName;
    @FXML
    DatePicker dateSubject;
    @FXML
    ComboBox grade;
    @FXML
    Button okButton;
    private mainClass main;
    private DataBaseConnection dataBaseConnection = new DataBaseConnection();
    private Connection connection = dataBaseConnection.connect();

    @FXML
    private void initialize() throws SQLException {
        profileList.clear();

        Statement selectFromProfileStatement = connection.createStatement();
        ResultSet result = selectFromProfileStatement.executeQuery("call selectNameFromGroupType()");
        while (result.next())
            profileList.add(result.getString(1));
        profileName.setValue("Select");
        profileName.setItems(profileList);

        groupName.setDisable(false);
        groupName.getItems().clear();
        studentName.setDisable(false);
        studentName.getItems().clear();
        teacherName.setDisable(false);
        teacherName.getItems().clear();
        subjectName.setDisable(false);
        subjectName.getItems().clear();
        dateSubject.setDisable(false);
        okButton.setDisable(false);

        gradeList.clear();
        gradeList.addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        grade.setItems(gradeList);
        grade.setDisable(false);
    }

    @FXML
    private void setGroupList() throws SQLException {

        groupList.clear();
        PreparedStatement callProfileSelectStatement = connection.prepareStatement("call selectGroupByType(?)");
        callProfileSelectStatement.setString(1, profileName.getValue().toString());
        ResultSet result = callProfileSelectStatement.executeQuery();

        while (result.next()) {
            groupList.add(result.getString(1));
        }

        groupName.setItems(groupList);
        groupName.setDisable(false);
        studentName.setDisable(true);
        studentName.getItems().clear();
        teacherName.setDisable(true);
        teacherName.getItems().clear();
        subjectName.setDisable(true);
        subjectName.getItems().clear();
        dateSubject.setDisable(true);
        grade.setDisable(true);
        okButton.setDisable(true);
    }

    @FXML
    private void setStudentList() throws SQLException {

        studentList.clear();
        if (groupName.getItems().size() != 0) {
            PreparedStatement selectStudentByGroupStatement = connection.prepareStatement("call selectStudentByGroup(?)");
            selectStudentByGroupStatement.setString(1, groupName.getValue().toString());
            ResultSet result = selectStudentByGroupStatement.executeQuery();

            while (result.next()) {
                studentList.add(new Student(result.getString(1), result.getString(2)));
            }

            ObservableList<String> StudentListTemp = FXCollections.observableArrayList();

            for (int index = 0; index < studentList.size(); index++)
                StudentListTemp.add(studentList.get(index).getName());

            studentName.setItems(StudentListTemp);
            studentName.setDisable(false);
            teacherName.setDisable(true);
            teacherName.getItems().clear();
            subjectName.setDisable(true);
            subjectName.getItems().clear();
            dateSubject.setDisable(true);
            grade.setDisable(true);
            okButton.setDisable(true);
        }
    }

    @FXML
    private void setTeacherList() throws SQLException {
        teacherList.clear();
        Statement selectTeacherStatement = connection.createStatement();
        ResultSet result = selectTeacherStatement.executeQuery("call selectTeacherInfo()");
        while (result.next())
            teacherList.add(new Teacher(result.getString(1), result.getString(2)));

        ObservableList<String> teacherListTemp = FXCollections.observableArrayList();

        for (int index = 0; index < teacherList.size(); index++)
            teacherListTemp.add(teacherList.get(index).getTeacherName());

        teacherName.setItems(teacherListTemp);
        teacherName.setDisable(false);
        subjectName.setDisable(true);
        subjectName.getItems().clear();
        dateSubject.setDisable(true);
        grade.setDisable(true);
        okButton.setDisable(true);
    }

    private int teacherPosition() {
        for (int index = 0; index < teacherList.size(); index++)
            if (teacherName.getValue() == teacherList.get(index).getTeacherName())
                return index;
        return 0;
    }

    private int studentPosition() {
        for (int index = 0; index < studentList.size(); index++)
            if (studentName.getValue() == studentList.get(index).getName())
                return index;
        return 0;
    }

    @FXML
    private void setSubjectList() throws SQLException {
        subjectList.clear();

        int index = teacherPosition();

        PreparedStatement selectSubjectByTeacherStatement = connection.prepareStatement("call selectSubjectByTeacher(?)");
        selectSubjectByTeacherStatement.setString(1, teacherList.get(index).getTeacherEmail());
        ResultSet result = selectSubjectByTeacherStatement.executeQuery();

        while (result.next()) {
            subjectList.add(result.getString(1));
        }

        subjectName.setItems(subjectList);
        subjectName.setDisable(false);
        dateSubject.setDisable(true);
        grade.setDisable(true);
        okButton.setDisable(true);
    }

    @FXML
    private void setDateVisible() {
        dateSubject.setDisable(false);
        grade.setDisable(true);
        okButton.setDisable(true);
    }

    @FXML
    private void setGradeVisible() {
        grade.setDisable(false);
        okButton.setDisable(true);
    }

    @FXML
    private void setOkVisible() {
        okButton.setDisable(false);
    }

    @FXML
    private void insertRegistry() throws SQLException, IOException {

        int indexStudent = studentPosition();
        int indexTeacher = teacherPosition();

        int year = dateSubject.getValue().getYear();
        int mounts = dateSubject.getValue().getMonthValue();
        int day = dateSubject.getValue().getDayOfMonth();

        String data = Integer.toString(year);
        data += '-';
        data += Integer.toString(mounts);
        data += '-';
        data += Integer.toString(day);

        int grades = Integer.parseInt(grade.getValue().toString());

        PreparedStatement insertIntoRegistryStatement = connection.prepareStatement("call insertIntoRegistry(?,?,?,?,?)");
        insertIntoRegistryStatement.setString(1, studentList.get(indexStudent).getEmail());
        insertIntoRegistryStatement.setString(2, teacherList.get(indexTeacher).getTeacherEmail());
        insertIntoRegistryStatement.setString(3, subjectName.getValue().toString());
        insertIntoRegistryStatement.setString(4, data);
        insertIntoRegistryStatement.setInt(5, grades);
        insertIntoRegistryStatement.executeQuery();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Added Successfully!");
        alert.showAndWait();
        Refresh();
    }

    @FXML
    private void closeScene() throws IOException {
        main.closeAddStudentScene();
    }
    @FXML
    private void goToTeacherScene() throws IOException {
        main.showTeacherScene();
    }
    @FXML
    private void Refresh() throws IOException {
        main.showAddNewRegistry();
    }
}
