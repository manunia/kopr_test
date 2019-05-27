package ru.maria_L.addres;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.maria_L.addres.controller.AutorisationController;
import ru.maria_L.addres.model.Place;
import ru.maria_L.addres.view.RootController;

import java.io.IOException;

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


    public ObservableList<Place> getPlaceData() {
        return placeData;
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

            rootStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}