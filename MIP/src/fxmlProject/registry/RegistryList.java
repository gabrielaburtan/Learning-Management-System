package fxmlProject.registry;

import DataBase.DataBaseConnection;
import DataBase.registry.RegistryTable;
import DataBase.student.Student;
import fxmlProject.mainClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;

public class RegistryList {

    @FXML
    TableView<RegistryTable> tableView = new TableView<RegistryTable>();
    ObservableList<RegistryTable> data = FXCollections.observableArrayList();
    ObservableList<String> ProfileName = FXCollections.observableArrayList();
    ObservableList<String> GroupName = FXCollections.observableArrayList();
    ObservableList<Student> StudentList = FXCollections.observableArrayList();
    String startEmail = "";
    private mainClass main;
    private DataBaseConnection dataBaseConnection = new DataBaseConnection();
    private Connection connection = dataBaseConnection.connect();
    @FXML
    private TableColumn<RegistryTable, String> StudentName;
    @FXML
    private TableColumn<RegistryTable, String> TeacherName;
    @FXML
    private TableColumn<RegistryTable, String> SubjectName;
    @FXML
    private TableColumn<RegistryTable, String> Date;
    @FXML
    private TableColumn<RegistryTable, String> Grade; //vezi ca e int
    @FXML
    private ComboBox<String> Profile;
    @FXML
    private ComboBox<String> Group;
    @FXML
    private ComboBox<String> StudentComboBox;
    @FXML
    private Button LoadButton;


    @FXML
    private void initialize() throws SQLException {

        ProfileName.clear();
        Statement selectProfileStatement = connection.createStatement();
        ResultSet result = selectProfileStatement.executeQuery("call selectFromGroupType()");
        while (result.next())
            ProfileName.add(result.getString(1));

        Profile.setValue("Select");
        Profile.setItems(ProfileName);
        Group.setDisable(false);
        StudentComboBox.setDisable(false);
        LoadButton.setDisable(false);
    }

    @FXML
    private void GroupList() throws SQLException {

        GroupName.clear();
        PreparedStatement selectGroupByTypeStatement = connection.prepareStatement("call selectGroupByType(?)");
        selectGroupByTypeStatement.setString(1, Profile.getValue().toString());
        ResultSet result = selectGroupByTypeStatement.executeQuery();

        while (result.next()) {
            GroupName.add(result.getString(1));
        }

        Group.setItems(GroupName);
        Group.setDisable(false);
        StudentComboBox.setDisable(true);
        LoadButton.setDisable(true);
    }

    @FXML
    private void StudentList() throws SQLException {

        StudentList.clear();
        PreparedStatement selectStudentByGroupStatement = connection.prepareStatement("call selectStudentByGroup(?)");
        selectStudentByGroupStatement.setString(1, Group.getValue());
        ResultSet result = selectStudentByGroupStatement.executeQuery();

        while (result.next()) {
            StudentList.add(new Student(result.getString(1), result.getString(2)));
        }

        ObservableList<String> StudentListTemp = FXCollections.observableArrayList();
        for (int index = 0; index < StudentList.size(); index++)
            StudentListTemp.add(StudentList.get(index).getName());

        StudentComboBox.setItems(StudentListTemp);
        StudentComboBox.setDisable(false);
        LoadButton.setDisable(true);
    }

    @FXML
    private void setLoad() {
        LoadButton.setDisable(false);
    }

    private int position() {
        for (int index = 0; index < StudentList.size(); index++)
            if (StudentComboBox.getValue() == StudentList.get(index).getName())
                return index;
        return 0;
    }

    @FXML
    private void loadTable() throws SQLException {

        int index = position();
        String names = StudentList.get(index).getEmail();
        if (names != startEmail) {
            startEmail = names;

            for (int i = 0; i < tableView.getItems().size(); i++) {
                tableView.getItems().clear();
            }

            PreparedStatement selectRegistryStatement = connection.prepareStatement("call selectRegistryBy(?)");
            selectRegistryStatement.setString(1, StudentList.get(index).getEmail());
            ResultSet result = selectRegistryStatement.executeQuery();
            while (result.next()) {
                data.add(new RegistryTable(result.getString(1), result.getString(2), result.getString(3), result.getString(4), Integer.toString(result.getInt(5))));
            }

            StudentName.setCellValueFactory(new PropertyValueFactory<RegistryTable, String>("studentName"));
            TeacherName.setCellValueFactory(new PropertyValueFactory<RegistryTable, String>("teacherName"));
            SubjectName.setCellValueFactory(new PropertyValueFactory<RegistryTable, String>("subjectName"));
            Date.setCellValueFactory(new PropertyValueFactory<RegistryTable, String>("date"));
            Grade.setCellValueFactory(new PropertyValueFactory<RegistryTable, String>("grade"));

            tableView.setItems(data);
        }

    }
    @FXML
    private void goToTeacherScene() throws IOException {
        main.showTeacherScene();
    }

}
