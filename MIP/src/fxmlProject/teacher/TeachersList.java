package fxmlProject.teacher;

import DataBase.DataBaseConnection;
import DataBase.subject.Subject;
import DataBase.subject.SubjectTypeId;
import DataBase.teachers.Teacher;
import DataBase.teachers.TeacherTable;
import fxmlProject.mainClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TeachersList {

    @FXML
    TableView<TeacherTable> tableView = new TableView<TeacherTable>();
    ObservableList<TeacherTable> data = FXCollections.observableArrayList();

    private mainClass main;
    private boolean loaded = false;

    @FXML
    private TableColumn<TeacherTable, String> teacherName;
    @FXML
    private TableColumn<TeacherTable, String> teacherEmail;
    @FXML
    private TableColumn<TeacherTable, String> subjectName;


    @FXML
    private void loadTable() throws SQLException {

        if (loaded == false) {
            loaded = true;
            DataBaseConnection dataBaseConnection = new DataBaseConnection();
            Connection connection = dataBaseConnection.connect();

            Statement selectTeacherStatement = connection.createStatement();
            ResultSet result = selectTeacherStatement.executeQuery("call selectAllProfesorSubject");

            while (result.next())
                data.add(new TeacherTable(new Teacher(result.getString(1), result.getString(2))
                        , new Subject(new SubjectTypeId(result.getString(4)), result.getString(3), result.getInt(5))));

            teacherName.setCellValueFactory(new PropertyValueFactory<TeacherTable, String>("Name"));
            teacherEmail.setCellValueFactory(new PropertyValueFactory<TeacherTable, String>("CNP"));
            subjectName.setCellValueFactory(new PropertyValueFactory<TeacherTable, String>("subjectName"));

            tableView.setItems(data);
        }
    }

    @FXML
    private void goMain() throws IOException {
        main.showMainItems();
    }
    @FXML
    private void goToAdminScene() throws IOException {
        main.showAdminView();
    }
}
