package ru.maria_L.addres.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.maria_L.addres.model.Annotation;
import ru.maria_L.addres.model.Place;

public class EditDialogController {
    @FXML
    private TextField countryField;
    @FXML
    private TextField regionField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField houseField;
    @FXML
    private TextArea descriptionArea;

    private Stage editDialogStage;
    private Place place;
    private Annotation description;
    private boolean okClicked = false;

    @FXML
    private void initialize(){}

    public void setEditDialogStage(Stage editDialogStage) {
        this.editDialogStage = editDialogStage;
    }

    public void setPlace(Place place) {
        this.place = place;

        countryField.setText(place.getCountry());
        regionField.setText(place.getRegion());
        cityField.setText(place.getCity());
        streetField.setText(place.getStreet());
        houseField.setText(place.getHouse());
        descriptionArea.setText(String.valueOf(place.getDescriptoin()));
        descriptionArea.setWrapText(true);
    }

    public boolean isOkClicked() {
        return okClicked;
    }
    //слушатели на кнопки
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            place.setCountry(countryField.getText());
            place.setRegion(regionField.getText());
            place.setCity(cityField.getText());
            place.setStreet(streetField.getText());
            place.setHouse(houseField.getText());
            place.setDescriptoin(descriptionArea.getText());

            okClicked = true;
            editDialogStage.close();
        }
    }
    @FXML
    private void handleCancel() {
        editDialogStage.close();
    }
    //проверка корректности ввода в поля
    private boolean isInputValid() {
        String errorMessage = "";

        if (countryField.getText() == null || countryField.getText().length() == 0) {
            errorMessage += "No valid country!\n";
        }
        if (regionField.getText() == null || regionField.getText().length() == 0) {
            errorMessage += "No valid region!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (houseField.getText() == null || houseField.getText().length() == 0) {
            errorMessage += "No valid house!\n";
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        //description может быть путым, т.к. описание может отсутствовать

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editDialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
