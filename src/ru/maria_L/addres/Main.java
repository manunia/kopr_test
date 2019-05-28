package ru.maria_L.addres;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.maria_L.addres.view.AutorisationController;
import ru.maria_L.addres.model.Place;
import ru.maria_L.addres.view.EditDialogController;
import ru.maria_L.addres.view.RootController;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main extends Application {

    private Stage primaryStage;
    private AnchorPane autorizePane;

    private ObservableList<Place> placeData = FXCollections.observableArrayList();
    /**
     * Конструктор
     */
    public Main() {
        // В качестве образца добавляем некоторые данные
        placeData.add(new Place("Russia","Rostov region"));
        placeData.add(new Place("Russia","Dagestan Republic"));

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Autorisation");
        initApp();
    }

    private void initApp() {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resourses/autorisation.fxml"));
            autorizePane = (AnchorPane) loader.load();
            Scene scene = new Scene(autorizePane);
            primaryStage.setScene(scene);

            AutorisationController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Place> getPlaceData() {
        return placeData;
    }

    public void initRoot() {
        try {
            FXMLLoader loader  = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resourses/root.fxml"));
            AnchorPane rootPane = (AnchorPane) loader.load();

            Stage rootStage = new Stage();
            rootStage.setTitle("Address List");
            rootStage.initModality(Modality.WINDOW_MODAL);
            rootStage.initOwner(primaryStage);

            Scene scene = new Scene(rootPane);
            rootStage.setScene(scene);

            RootController controller = loader.getController();
            controller.setStage(rootStage);
            controller.setMain(this);
            primaryStage.close();
            rootStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //загрузка окна диалога редактирования данных
    public boolean showEditDialog(Place place) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resourses/editDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Address");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адрес в контроллер.
            EditDialogController controller = loader.getController();
            controller.setEditDialogStage(dialogStage);
            controller.setPlace(place);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //сохранение текущих настроек приложения
    public File getPlaceFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    //восстановление состояния
    public void setPlaceFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Обновление заглавия сцены.
            primaryStage.setTitle("Address List - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Обновление заглавия сцены.
            primaryStage.setTitle("Address List");
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
