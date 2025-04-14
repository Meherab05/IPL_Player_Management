package com.example.huhuhahahihi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Map;


public class CountrywiseController {
    private Stage stage;
    public CountrywiseController(){}
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private TableView<Map.Entry<String, Integer>> tableView;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> countryColumn;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, Integer> countColumn;

    @FXML
    private void initialize() {
        countryColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getKey()));
        countColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getValue()).asObject());
        loadCountryPlayerCounts();
    }

    private void loadCountryPlayerCounts() {
        // Call the displayCount method to get the data
        Map<String, Integer> data = PlayerDatabase.displayCount();

        // Convert the data into an ObservableList
        ObservableList<Map.Entry<String, Integer>> tableData = FXCollections.observableArrayList(data.entrySet());

        // Set data in the TableView
        tableView.setItems(tableData);
    }
    public void back(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("SearchPlayer.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),900,500);
            SearchPlayerController controller=fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("SearchPlayer");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
