package com.example.huhuhahahihi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SearchPlayerController {
    private Stage stage;
   public SearchPlayerController(){}
    public void setStage(Stage stage) {this.stage = stage;}
    public void searchByName(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("SearchByName.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),900,500);
            SearchByNameController controller=fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("Search By Name");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchByClubAndCountry(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("SearchByClubAndCountry.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),900,500);
            SearchByClubAndCountryController controller=fxmlLoader.getController();
controller.setStage(stage);
            stage.setTitle("Search By club and country");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void searchByPosition(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("ByPosition.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),900,500);
            positionController controller=fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("Search By Position");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchBySalaryRange(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("BySalaryRange.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),900,500);
            SalarywiseController controller=fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("Search By salary");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void countryWisePlayerCount(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("Countrywise.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),900,500);
            CountrywiseController controller=fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("Countrywise Count");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void backToMainMenu(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("Home.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),900,500);
            HomeController controller=fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
