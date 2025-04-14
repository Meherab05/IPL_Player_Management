package com.example.huhuhahahihi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class SearchByClubAndCountryController {
    @FXML public TextField cl;
    @FXML public TextField cn;
    @FXML public TextArea playerss;
    private Stage stage;
    public SearchByClubAndCountryController(){}
    public void setStage(Stage stage){
        this.stage=stage;
    }
    public void search(ActionEvent actionEvent) {
        playerss.clear();
        String club = cl.getText();
        String country = cn.getText();
        List<Player> ls=PlayerDatabase.searchPlayerByCountry(country,club);
        if(!ls.isEmpty()){
            for(Player p:ls){
                playerss.appendText(p.toString3()+"\n");
            }
        }
        else{
            playerss.setText("No player to show");
        }

    }

    public void back(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("SearchPlayer.fxml"));
            Scene scene=new Scene(fxmlLoader.load(),900,500);
            SearchPlayerController controller=(SearchPlayerController) fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("SearchPlayer");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
