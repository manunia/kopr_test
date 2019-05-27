package ru.maria_L.addres.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.maria_L.addres.Main;

public class AutorisationController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button canselButton;
    @FXML
    private Button okButton;

    private Main main;

    public AutorisationController() {
    }

    @FXML
    private void initialize() {
    }

    @FXML
    public void handleOk(){
        main.initRoot();
//        if ((loginField.getText() != "" || loginField.getText() != null) &&
//                (passwordField.getText() != "" || passwordField.getText() != null)) {
//            main.initRoot();
//
//        }else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(main.getPrimaryStage());
//            alert.setTitle("Fields is empty");
//            alert.setHeaderText("Field is empty");
//            alert.setContentText("Please enter login and password.");
//
//            alert.showAndWait();
//        }
    }

    @FXML
    public void handleCancel() {
        System.exit(0);
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
