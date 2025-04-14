package com.example.huhuhahahihi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class clubSearchController {
    @FXML
    TextField cl;
    @FXML
    TextArea playerss;
    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public clubSearchController() {}
    public void maxSalary(ActionEvent actionEvent) {
        playerss.clear();
        List<Player> ls=PlayerDatabase.searchByClub(cl.getText(),1);
        if(!ls.isEmpty()){
            for(Player p:ls){
                playerss.appendText(p.toString3()+"\n");
            }
        }
        else{
            playerss.setText("No player to show");
        }
    }

    public void maxAge(ActionEvent actionEvent) {
        playerss.clear();
        List<Player> ls=PlayerDatabase.searchByClub(cl.getText(),2);
        if(!ls.isEmpty()){
            for(Player p:ls){
                playerss.appendText(p.toString3()+"\n");
            }
        }
        else{
            playerss.setText("No player to show");
        }
    }

    public void maxHeight(ActionEvent actionEvent) {
        playerss.clear();
        List<Player> ls=PlayerDatabase.searchByClub(cl.getText(),3);
        if(!ls.isEmpty()){
            for(Player p:ls){
                playerss.appendText(p.toString3()+"\n");
            }
        }
        else{
            playerss.setText("No player to show");
        }
    }

    public void total(ActionEvent actionEvent) {
        playerss.clear();
        long total=PlayerDatabase.clubSalary(cl.getText());
        if(total>0){
            playerss.setText("Total salary: "+total);
        }
        else{
            playerss.setText("Not a valid club");
        }
    }

    public void back(ActionEvent actionEvent) {try{
        FXMLLoader fxmlLoader=new FXMLLoader(MainApplication.class.getResource("Home.fxml"));
        Scene scene=new Scene(fxmlLoader.load(),900,500);
        HomeController controller=fxmlLoader.getController();
        controller.setStage(stage);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();

    } catch (Exception e) {
        e.printStackTrace();
    }

    }
}
