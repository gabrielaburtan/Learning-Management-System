package fxmlProject.profile;

import DataBase.DataBaseConnection;
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

public class ListProfile {

    @FXML
    TableView<GroupTypeId> tableView = new TableView<GroupTypeId>();
    ObservableList<GroupTypeId> dataInsert = FXCollections.observableArrayList();
    private mainClass main;
    private boolean loaded = false;
    @FXML
    private TableColumn<GroupTypeId, String> profileName;

    @FXML
    private void loadTable() throws SQLException {
        if (loaded == false) {
            loaded = true;
            DataBaseConnection dataBaseConnection = new DataBaseConnection();
            Connection connection = dataBaseConnection.connect();

            Statement selectFromProfile = connection.createStatement();
            ResultSet result = selectFromProfile.executeQuery("selectNameFromGroupType");
            while (result.next())
                dataInsert.add(new GroupTypeId(result.getString(1)));

            profileName.setCellValueFactory(new PropertyValueFactory<GroupTypeId, String>("groupTypeName"));

            tableView.setItems(dataInsert);
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
