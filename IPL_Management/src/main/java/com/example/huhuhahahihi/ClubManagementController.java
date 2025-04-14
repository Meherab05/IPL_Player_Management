package com.example.huhuhahahihi;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClubManagementController {
    @FXML public TextField name;
    @FXML public Button bsclick;
    @FXML public Button toggle;
    @FXML public TextArea playerss;
    @FXML public Label clubNameLabel;

    private Stage stage;
    private String clubName;
    private boolean toggled = false; // View state (own players or transfer window)
    private NetworkUtil networkUtil;

    public static List<Player> ownPlayers = new ArrayList<>();
    public static List<Player> transferWindowPlayers = new ArrayList<>();

    private Thread refreshThread;

    @FXML
    public void initialize() {
        startRefreshThread();
    }

    public ClubManagementController() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setNetworkUtil(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }

    public void setClubName(String name) {
        this.clubName = name.toUpperCase();
        clubNameLabel.setText("Club Name: ".toUpperCase() + name.toUpperCase());
    }

    private void refreshPlayerList() {
        Platform.runLater(() -> {
            playerss.clear();
            try {
                networkUtil.write("SHOW_TRANSFER:" + clubName);
                try {
                    Object o = networkUtil.read();
                    if (o instanceof ArrayList<?>) {
                        transferWindowPlayers = (List<Player>) o;
                    }
                } catch (Exception e) {

                }
            } catch (Exception e) {
            }
            try {
                networkUtil.write("SHOW:" + clubName);
                try {
                    Object o = networkUtil.read();
                    if (o instanceof ArrayList<?>) {
                        ownPlayers = (List<Player>) o;
                    }
                } catch (Exception e) {
                }
            } catch (Exception e) {
            }

            List<Player> currentList = toggled ? transferWindowPlayers : ownPlayers;

            if (currentList.isEmpty()) {
                playerss.setText("No players to show");
            } else {
                for (Player player : currentList) {
                    if (!(toggled && player.getClub().equalsIgnoreCase((clubName)))) {
                        playerss.appendText(player.toString3() + "\n");
                    }
                }
            }
        });
    }

    private void startRefreshThread() {
        refreshThread = new Thread(() -> {
            while (true) {
                try {
                    refreshPlayerList();
                    Thread.sleep(1000); // Refresh every 1 second

                } catch (Exception e) {
                    System.out.println("Refresh thread interrupted.");
                    break;
                }
            }
        });
        refreshThread.setDaemon(true); // Ensure the thread stops when the application exits
        refreshThread.start();
    }

    public void toggle(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        toggled = !toggled;
        toggle.setText(toggled ? "View Own Players" : "View Transfer Window");
        bsclick.setText(toggled ? "Buy" : "Sell");
        name.setPromptText(toggled ? "Enter player name to buy" : "Enter player name to sell");

    }

    public void bsclick(ActionEvent actionEvent) {
        String playerName = name.getText();

        if (playerName.isEmpty()) {
            showAlert("Enter a player name!");
            return;
        }

        try {boolean flag=false;
            if (toggled) { // Buy player
                for (Player player : transferWindowPlayers) {
                    if(playerName.equalsIgnoreCase(player.getName())) {
                        flag=true;
                        showAlert("SUCCESS: Player bought successfully.");
                        networkUtil.write("BUY:" + playerName + ":" + clubName);
                    }
                }
                if(!flag) {showAlert("ERROR: Player not found in the transfer window.");}

            } else { // Sell player
                if(playerName==null) {
                    networkUtil.write("ERROR: Player name cannot be null!");
                }
                else{
                    for(Player player : ownPlayers) {

                        if(playerName.equalsIgnoreCase(player.getName())) {
                            flag=true;
                            showAlert("SUCCESS: Player added to transfer window.");
                            networkUtil.write("SELL:" + clubName + ":" + playerName);
                        }
                    }
                    if(!flag) {showAlert("Error: Player not found in your club!");}

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 500);
            HomeController controller = fxmlLoader.getController();
            controller.setStage(stage);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
