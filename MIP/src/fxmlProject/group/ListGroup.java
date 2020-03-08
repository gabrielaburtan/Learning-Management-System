package fxmlProject.group;

import DataBase.DataBaseConnection;
import DataBase.group.Group;
import DataBase.group.GroupTable;
import DataBase.group.GroupTypeId;
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

public class ListGroup {

    @FXML
    TableView<GroupTable> tableView = new TableView<GroupTable>();
    ObservableList<GroupTable> data = FXCollections.observableArrayList();
    private mainClass main;
    private boolean loaded = false;
    @FXML
    private TableColumn<GroupTable, String> groupName;
    @FXML
    private TableColumn<GroupTable, String> groupTypeName;

    @FXML
    private void loadTable() throws SQLException {
        if (loaded == false) {
            loaded = true;
            DataBaseConnection dataBaseConnection = new DataBaseConnection();
            Connection connection = dataBaseConnection.connect();

            Statement callGroupList = connection.createStatement();
            ResultSet result = callGroupList.executeQuery("call groupList");
            while (result.next())
                data.add(new GroupTable(new Group(new GroupTypeId(result.getString(2)), result.getString(1))));

            groupName.setCellValueFactory(new PropertyValueFactory<GroupTable, String>("GroupName"));
            groupTypeName.setCellValueFactory(new PropertyValueFactory<GroupTable, String>("GroupType"));

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
