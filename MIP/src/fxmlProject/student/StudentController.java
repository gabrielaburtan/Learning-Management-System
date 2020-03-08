package fxmlProject.student;

import com.sun.tools.javac.Main;
import fxmlProject.mainClass;
import javafx.fxml.FXML;

import java.io.IOException;

public class StudentController {
    mainClass main;
    @FXML
    private void closeScene() throws IOException {
        main.showMainItems();
    }
    @FXML
    private void goStudentListScene() throws IOException {
        main.showStudentListScene();
    }

    @FXML
    private void goAddStudentScene() throws IOException {
        main.showAddStudentScene();
    }
}
