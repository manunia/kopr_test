package ru.maria_L.addres.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ru.maria_L.addres.Main;
import ru.maria_L.addres.model.Place;

public class RootController {

    @FXML
    private TableView<Place> placeTable;
    @FXML
    private TableColumn<Place,String> countryColumn;
    @FXML
    private TableColumn<Place,String> regionColumn;

    @FXML
    private Label countryLabel;
    @FXML
    private Label regionLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label houseLabel;
    @FXML
    private Label descriptionLabel;

    private Stage stage;

    private Main main;

    public RootController() {
    }

    @FXML
    private void initialize() {
        //инициализация таблицы
        countryColumn.setCellValueFactory(cellData->cellData.getValue().countryProperty());
        regionColumn.setCellValueFactory(cellData->cellData.getValue().regionProperty());
        //очистка полей с описанием
        showPlaceDetails(null);
        //прослушиваем изменения выбора
        placeTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue,newValue)->showPlaceDetails(newValue)
        );
    }

    private void showPlaceDetails(Place place) {
        if(place != null) {
            countryLabel.setText(place.getCountry());
            regionLabel.setText(place.getRegion());
            cityLabel.setText(place.getCity());
            streetLabel.setText(place.getStreet());
            houseLabel.setText(place.getHouse());
            descriptionLabel.setText(place.getDescriptoin());
        } else {
            countryLabel.setText("");
            regionLabel.setText("");
            cityLabel.setText("");
            streetLabel.setText("");
            houseLabel.setText("");
            descriptionLabel.setText("");
        }
    }

    //удаление элементов
    @FXML
    private void handleDeletePlace() {
        int selectedIndex = placeTable.getSelectionModel().getFocusedIndex();
        if (selectedIndex >= 0) {
            placeTable.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Place Selected");
            alert.setContentText("Please select some place in the table.");

            alert.showAndWait();
        }
    }

    public void setMain(Main main) {
        this.main = main;

        placeTable.setItems(main.getPlaceData());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
