package com.example.huhuhahahihi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {

    private Stage stage;

    public HomeController() {
    }

    @FXML
    public void SearchPlayers() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("SearchPlayer.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 500);
            SearchPlayerController controller = fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("Search Players");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void clubSearch(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("clubSearch.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),900,500);
            clubSearchController controller=fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("clubSearch");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("add.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),900,500);
            add controller=fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("Add");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){e.printStackTrace();}
    }

    public void Exit(ActionEvent actionEvent) {
        stage.close();
    }

    public void clubLogin(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("club.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),900,500);
            ClubController controller=fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("Club");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
