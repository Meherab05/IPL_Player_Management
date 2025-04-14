package com.example.huhuhahahihi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchByNameController {
    private Stage stage;
    public SearchByNameController() {}
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML TextField playerNameField;
    @FXML Label searchByNameOutputLabel;
    @FXML
    public void search(ActionEvent actionEvent) {
        searchByNameOutputLabel.setText("");
        Player player = PlayerDatabase.searchPlayerByName(playerNameField.getText());
        if (player != null) {
            searchByNameOutputLabel.setText(player.toString2());
        } else {
            playerNameField.setPromptText("Enter Player Name");
            searchByNameOutputLabel.setText("No player to show");
        }
        playerNameField.setPromptText("Enter Player Name");
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
