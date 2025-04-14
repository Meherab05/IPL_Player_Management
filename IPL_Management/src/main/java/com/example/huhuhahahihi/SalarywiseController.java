package com.example.huhuhahahihi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.List;

public class SalarywiseController {
    @FXML
    TextArea playerss;
    @FXML
    TextField minsalary;
    @FXML
    TextField maxsalary;
    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public SalarywiseController() {}
    public void search(ActionEvent actionEvent) {playerss.clear();
        long mn,mx;
        try{
        mn = Long.parseLong(minsalary.getText());}catch(Exception e){playerss.setText("Invalid input");return;}
        try {
            mx = Long.parseLong(maxsalary.getText());
        }catch(Exception e){playerss.setText("Invalid input");return;}
        List<Player> ls = PlayerDatabase.searchPlayerBySalary(mn,mx);
        if(!ls.isEmpty()
        ){
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
