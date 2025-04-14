package com.example.huhuhahahihi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClubController {
    @FXML public TextField clubNameField;
    private Stage stage;
    String serverAddress = "127.0.0.1"; // Replace with your actual server address
    int serverPort = 1234;              // Replace with your actual server port
    private NetworkUtil networkUtil;



    public ClubController() {}

    public void setStage(Stage stage) {
        this.stage = stage;
        setNetworkUtil();
    }

    public void setNetworkUtil() {

            try {
                networkUtil = new NetworkUtil(serverAddress, serverPort);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onLogin(ActionEvent actionEvent) {
        String clubName = clubNameField.getText();  // Trim extra spaces
        if (clubName.isEmpty()) {
            showAlert("Please enter a club name!");
            return;
        }

        try {
            System.out.println("Sending LOGIN request for club: " + clubName);  // Debugging statement
            networkUtil.write("LOGIN:" + clubName);
            Object response = networkUtil.read();
            System.out.println("Received response: " + response);  // Debugging statement

            if ("SUCCESS".equals(response)) {
                navigateToClubManagement(clubName);
            } else {
                showAlert("LOGIN FAILED: Club does not exist. Please register first.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Unable to communicate with the server.");
        }
    }

    public void onRegister(ActionEvent actionEvent) {
        String clubName = clubNameField.getText();
        if (clubName.isEmpty()) {
            showAlert("Please enter a club name!");
            return;
        }

        try {
            // Send the registration request to the server
            networkUtil.write("REGISTER:" + clubName);
            Object response = networkUtil.read();

            if ("SUCCESS".equals(response)) {
                navigateToClubManagement(clubName);
            } else {
                showAlert("REGISTRATION FAILED: Club already exists.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Unable to communicate with the server.");
        }
    }

    private void navigateToClubManagement(String clubName) throws IOException, ClassNotFoundException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("clubwindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 500);

            ClubManagementController controller = fxmlLoader.getController();
            controller.setStage(stage);
            controller.setNetworkUtil(networkUtil);
            controller.setClubName(clubName);

            stage.setTitle("Club Management");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}