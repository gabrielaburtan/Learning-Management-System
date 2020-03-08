package fxmlProject.registry;

import fxmlProject.mainClass;
import javafx.fxml.FXML;

import java.io.IOException;

public class RegistryController {
    mainClass main;
    @FXML
    private void goMain() throws IOException {
        main.showMainItems();
    }
    @FXML
    private void goAddNewRegistry() throws IOException {
        main.showAddNewRegistry();
    }
    @FXML
    private void goRegistryList() throws IOException {
        main.showRegistryList();
    }
}
