package ru.maria_L.addres.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.maria_L.addres.Main;
import ru.maria_L.addres.model.Place;

import java.io.File;

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
            descriptionLabel.setText(String.valueOf(place.getDescriptoin()));
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

    //обработчик нажатия на кнопку New, вызывает диалог редактирования
    @FXML
    private void handleNewPlace() {
        Place tempPlace = new Place();
        boolean okClicked = main.showEditDialog(tempPlace);
        if (okClicked) {
            main.getPlaceData().add(tempPlace);
        }
    }
    //обработчик нажатия на кнопку Edit
    @FXML
    private void handleEditPlace() {
        Place selectedPlace = placeTable.getSelectionModel().getSelectedItem();
        if (selectedPlace != null) {
            boolean okClicked = main.showEditDialog(selectedPlace);
            if (okClicked) {
                showPlaceDetails(selectedPlace);
            }else {
                // Ничего не выбрано.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(main.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Place Selected");
                alert.setContentText("Please select some place in the table.");

                alert.showAndWait();
            }
        }
    }

    public void setMain(Main main) {
        this.main = main;

        placeTable.setItems(main.getPlaceData());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //создание нового списка
    @FXML
    private void handleNew() {
        main.getPlaceData().clear();
        main.setPlaceFilePath(null);
    }

    //открыть файл с адресами
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог загрузки файла
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            main.loadDataFromFile(file);
        }
    }

    //сохранение данных в существующий xml файл
    @FXML
    private void handleSave() {
        File personFile = main.getPlaceFilePath();
        if (personFile != null) {
            main.saveDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    //сохранение в новый xml файл
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог сохранения файла
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            main.saveDataToFile(file);
        }
    }


     //Открывает диалоговое окно about.
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Address List");
        alert.setHeaderText("About");
        alert.setContentText("Created by Maria_L");

        alert.showAndWait();
    }


     //Закрывает приложение.
    @FXML
    private void handleExit() {
        System.exit(0);
    }

}
