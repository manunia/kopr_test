package ru.maria_L.addres.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.maria_L.addres.Main;
import ru.maria_L.addres.dao.XMLParser;
import ru.maria_L.addres.model.User;

import java.util.List;

public class AutorisationController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    private Main main;

    public AutorisationController() {
    }

    @FXML
    private void initialize() {
    }

    @FXML
    public void handleOk(){
        if ((loginField.getText() == null || loginField.getText().length() == 0) ||
                (passwordField.getText() == null || passwordField.getText().length() == 0)) {
            showAlert("Fields is empty","Please enter login and password.");
        }else {
            XMLParser read = new XMLParser();
            List<User> readConfig = read.readConfig("userDB.xml");
            for (User u:readConfig) {
                while (loginField.getText().equals(u.getLogin()) && passwordField.getText().equals(u.getPassword())) {
                    if (loginField.getText().equals(u.getLogin()) && passwordField.getText().equals(u.getPassword())) {
                        System.out.println("user id = " + u.getId());
                        System.out.println("login: " + u.getLogin());
                        System.out.println("password: " + u.getPassword());
                        main.initRoot();
                        break;
                    } else {
                        showAlert("Invalid login and password", "Enter valid login and password");
                        break;
                    }
                }
            }
        }
        System.out.println("entered login: " + loginField.getText());
        System.out.println("entered password: " + passwordField.getText());
    }

    @FXML
    public void handleCancel() {
        System.exit(0);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void showAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle(header);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
