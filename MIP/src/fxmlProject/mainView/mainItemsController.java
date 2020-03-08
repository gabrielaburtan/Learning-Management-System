package fxmlProject.mainView;

import fxmlProject.mainClass;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;

public class mainItemsController {

    private mainClass main;

    @FXML
    private void goStudentScene() throws IOException {
        main.showStudentScene();
    }
    @FXML
    private void goTeacherScene() throws IOException {
        main.showTeacherScene();
    }
    @FXML
    private void goAdminView() throws IOException {
        main.showAdminView();
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
    private void goAddNewTeacher() throws IOException {
        main.showAddNewTeacher();
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

    public void closeApplication() throws Exception {
        main.primaryStage.close();
    }
}
