package fxmlProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class mainClass extends Application {

    public static Stage primaryStage;
    private static BorderPane mainLayout;
    private static Stage dialogScene;


    public static void main(String[] args) {
        launch(args);
    }

    public static void showMainItems() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("mainView/mainItems.fxml"));
        BorderPane mainItems = loader.load();
        mainLayout.setCenter(mainItems);
    }

    public static void showStudentScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("student/StudentControl.fxml"));
        BorderPane StudentList = loader.load();
        mainLayout.setCenter(StudentList);
    }

    public static void showTeacherScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("registry/RegistryControl.fxml"));
        BorderPane StudentList = loader.load();
        mainLayout.setCenter(StudentList);
    }

    public static void showAdminView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("admin/AdminView.fxml"));
        BorderPane AdminView = loader.load();
        mainLayout.setCenter(AdminView);
    }

    public static void showStudentListScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("student/StudentList.fxml"));
        BorderPane StudentList = loader.load();
        mainLayout.setCenter(StudentList);
    }

    public static void showAddStudentScene() throws IOException {
        //dialogScene = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("student/AddStudent.fxml"));
        BorderPane AddStudent = loader.load();
        mainLayout.setCenter(AddStudent);
//        dialogScene.setTitle("Add new student");
//        dialogScene.initModality(Modality.WINDOW_MODAL);
//        dialogScene.initOwner(primaryStage);
//        Scene scene = new Scene(AddStudent);
//        dialogScene.setScene(scene);
//        dialogScene.showAndWait();
    }

    public static void showAddNewProfile() throws IOException {
        //dialogScene = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("profile/AddNewProfile.fxml"));
        BorderPane AddProfile = loader.load();
        mainLayout.setCenter(AddProfile);
//        dialogScene.setTitle("Add new profile");
//        dialogScene.initModality(Modality.WINDOW_MODAL);
//        dialogScene.initOwner(primaryStage);
//        Scene scene = new Scene(AddProfile);
//        dialogScene.setScene(scene);
//        dialogScene.showAndWait();
    }

    public static void showListProfileScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("profile/ListProfile.fxml"));
        BorderPane ListProfile = loader.load();
        mainLayout.setCenter(ListProfile);
    }

    public static void showAddNewSubject() throws IOException {
//        dialogScene = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("subject/AddSubject.fxml"));
        BorderPane AddNewSubject = loader.load();
        mainLayout.setCenter(AddNewSubject);
//        dialogScene.setTitle("Add new subject");
//        dialogScene.initModality(Modality.WINDOW_MODAL);
//        dialogScene.initOwner(primaryStage);
//        Scene scene = new Scene(AddNewSubject);
//        dialogScene.setScene(scene);
//        dialogScene.showAndWait();
    }

    public static void showSubjectsListScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("subject/ListSubject.fxml"));
        BorderPane ListMaterii = loader.load();
        mainLayout.setCenter(ListMaterii);
    }

    public static void showAddNewGroup() throws IOException {
//        dialogScene = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("group/AddNewGroup.fxml"));
        BorderPane AddNewGrupa = loader.load();
        mainLayout.setCenter(AddNewGrupa);
//        dialogScene.setTitle("Add new group");
//        dialogScene.initModality(Modality.WINDOW_MODAL);
//        dialogScene.initOwner(primaryStage);
//        Scene scene = new Scene(AddNewGrupa);
//        dialogScene.setScene(scene);
//        dialogScene.showAndWait();
    }

    public static void showGroupList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("group/ListGroup.fxml"));
        BorderPane ListGroup = loader.load();
        mainLayout.setCenter(ListGroup);
    }

    public static void showAddNewTeacher() throws IOException {
//        dialogScene = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("teacher/AddNewTeacher.fxml"));
        BorderPane AddNewTeacher = loader.load();
        mainLayout.setCenter(AddNewTeacher);
//        dialogScene.setTitle("Add new group");
//        dialogScene.initModality(Modality.WINDOW_MODAL);
//        dialogScene.initOwner(primaryStage);
//        Scene scene = new Scene(AddNewTeacher);
//        dialogScene.setScene(scene);
//        dialogScene.showAndWait();
    }

    public static void showTeachersList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("teacher/TeachersList.fxml"));
        BorderPane TeachersList = loader.load();
        mainLayout.setCenter(TeachersList);
    }

    public static void showAddNewRegistry() throws IOException {
//        dialogScene = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("registry/AddRegistry.fxml"));
        BorderPane AddNewRegistry = loader.load();
        mainLayout.setCenter(AddNewRegistry);
//        dialogScene.setTitle("Add new registry");
//        dialogScene.initModality(Modality.WINDOW_MODAL);
//        dialogScene.initOwner(primaryStage);
//        Scene scene = new Scene(AddNewRegistry);
//        dialogScene.setScene(scene);
//        dialogScene.showAndWait();
    }

    public static void showRegistryList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("registry/RegistryList.fxml"));
        BorderPane RegistryList = loader.load();
        mainLayout.setCenter(RegistryList);
    }

    public static void closeAddStudentScene() {
        dialogScene.close();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        mainClass.primaryStage = primaryStage;
        mainClass.primaryStage.setTitle("LEARNING MANAGEMENT SYSTEM");
        showMainView();
        showMainItems();
    }

    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainClass.class.getResource("mainView/mainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
