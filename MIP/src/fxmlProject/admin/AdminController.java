package fxmlProject.admin;

import fxmlProject.mainClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    mainClass main;
    @FXML
    private void closeScene() throws IOException {
        main.showMainItems();
    }
    @FXML
    private void goToStudentScene() throws IOException {
        main.showStudentScene();
    }

    @FXML
    private void goToAdminScene() throws IOException {
        main.showAdminView();
    }
    @FXML
    private void goAddNewTeacher() throws IOException {
        main.showAddNewTeacher();
    }
    @FXML
    private void goListSubjects() throws IOException {
        main.showSubjectsListScene();
    }

    @FXML
    private void goAddNewSubject() throws IOException {
        main.showAddNewSubject();
    }

    @FXML
    private void goAddNewGroup() throws IOException {
        main.showAddNewGroup();
    }

    @FXML
    private void goGroupList() throws IOException {
        main.showGroupList();
    }

    @FXML
    private void goTeachersList() throws IOException {
        main.showTeachersList();
    }

    @FXML
    private void goAddNewRegistry() throws IOException {
        main.showAddNewRegistry();
    }

    @FXML
    private void goRegistryList() throws IOException {
        main.showRegistryList();
    }

    @FXML
    private void goListProfile() throws IOException {
        main.showListProfileScene();
    }

    @FXML
    private void goAddNewProfile() throws IOException {
        main.showAddNewProfile();
    }
}
