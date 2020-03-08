package fxmlProject.student;

import DataBase.DataBaseConnection;
import DataBase.group.Group;
import DataBase.group.GroupTypeId;
import DataBase.student.Student;
import DataBase.student.StudentGroup;
import DataBase.student.StudentTable;
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

public class StudentList {

    @FXML
    TableView<StudentTable> tableView = new TableView<StudentTable>();
    ObservableList<StudentTable> data = FXCollections.observableArrayList();
    private mainClass main;
    private boolean loaded = false;
    @FXML
    private TableColumn<StudentTable, String> studentName;
    @FXML
    private TableColumn<StudentTable, String> studentEmail;
    @FXML
    private TableColumn<StudentTable, String> group;
    @FXML
    private TableColumn<StudentTable, String> groupType;
    @FXML
    private TableColumn<StudentTable, String> startDate;
    @FXML
    private TableColumn<StudentTable, String> endDate;

    @FXML
    private void loadTable() throws SQLException {

        if (loaded == false) {
            loaded = true;
            DataBaseConnection db = new DataBaseConnection();
            Connection con = db.connect();

            Statement stmt = con.createStatement();
            System.out.println("Crapa");
            ResultSet rs = stmt.executeQuery("call selectAllStudents");
            while (rs.next())
                data.add(new StudentTable(new StudentGroup(new Student(rs.getString(1), rs.getString(2))
                        , new Group(new GroupTypeId(rs.getString(4)), rs.getString(3))
                        , rs.getDate(5).toString(), "-")));

            studentName.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("nameStudent"));
            studentEmail.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("emailStudent"));
            group.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("groupName"));
            groupType.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("groupTypeName"));
            startDate.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("startDate"));
            endDate.setCellValueFactory(new PropertyValueFactory<StudentTable, String>("endDate"));

            tableView.setItems(data);
        }
    }

    @FXML
    private void goMain() throws IOException {
        main.showMainItems();
    }
    @FXML
    private void goToStudentScene() throws IOException {
        main.showStudentScene();
    }
}
