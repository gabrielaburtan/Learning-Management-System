package fxmlProject.subject;

import DataBase.DataBaseConnection;
import DataBase.subject.Subject;
import DataBase.subject.SubjectTable;
import DataBase.subject.SubjectTypeId;
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

public class ListSubject {

    @FXML
    TableView<SubjectTable> tableView = new TableView<SubjectTable>();
    ObservableList<SubjectTable> data = FXCollections.observableArrayList();

    private mainClass main;
    private boolean Loaded = false;
    @FXML
    private TableColumn<SubjectTable, String> subjectTypeName;
    @FXML
    private TableColumn<SubjectTable, String> subjectName;
    @FXML
    private TableColumn<SubjectTable, String> credits;

    @FXML
    private void loadTable() throws SQLException {

        if (Loaded == false) {
            Loaded = true;
            DataBaseConnection dataBaseConnection = new DataBaseConnection();
            Connection connection = dataBaseConnection.connect();

            Statement selectSubjectsStatement = connection.createStatement();
            ResultSet result = selectSubjectsStatement.executeQuery("call selectAllSubjects");
            while (result.next())
                data.add(new SubjectTable(new Subject(new SubjectTypeId(result.getString(1)), result.getString(2), result.getInt(3))));

            subjectTypeName.setCellValueFactory(new PropertyValueFactory<SubjectTable, String>("subjectTypeName"));
            subjectName.setCellValueFactory(new PropertyValueFactory<SubjectTable, String>("subjectName"));
            credits.setCellValueFactory(new PropertyValueFactory<SubjectTable, String>("credits"));

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
