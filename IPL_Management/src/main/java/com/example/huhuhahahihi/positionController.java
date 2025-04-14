package com.example.huhuhahahihi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class positionController {
    @FXML
    public TextField position;
    @FXML
   public TextArea playerss;
    Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
    }
    public positionController(){}
    public void search(ActionEvent actionEvent) {
        playerss.clear();
        String pos=position.getText();
        if(!pos.equalsIgnoreCase("batsman") && !pos.equalsIgnoreCase("bowler") && !pos.equalsIgnoreCase("Allrounder") && !pos.equalsIgnoreCase("Wicketkeeper")){
            playerss.setText("Invalid Position"); return;
        }
        else {
            List<Player> players=PlayerDatabase.searchPlayerByPosition(pos);
            for(Player p:players){
                playerss.appendText(p.toString3()+"\n");
            }
        }


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
