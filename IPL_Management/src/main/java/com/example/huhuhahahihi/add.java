package com.example.huhuhahahihi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class add {
    @FXML
    private TextField nameField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField clubField;

    @FXML
    private ComboBox<String> positionComboBox;

    @FXML
    private TextField jerseyNumberField;

    @FXML
    private TextField salaryField;

    @FXML
    private TextArea outputArea;

    Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
    }
    public add(){}
    public void back(ActionEvent actionEvent) {
        try{
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

    public void submit(ActionEvent actionEvent) {
        outputArea.clear();
        String name = nameField.getText().trim();
        String country = countryField.getText().trim();
        String ageStr = ageField.getText().trim();
        String heightStr = heightField.getText().trim();
        String club = clubField.getText().trim();
        String position = positionComboBox.getValue();
        String jerseyNumberStr = jerseyNumberField.getText().trim();
        String salaryStr = salaryField.getText().trim();
        if (name.isEmpty() || country.isEmpty() || ageStr.isEmpty() || heightStr.isEmpty() ||
                club.isEmpty() || position == null || salaryStr.isEmpty()) {
            outputArea.setText("Error: Please fill out all fields!");
            return;
        }

        int age; String jerseyNumber;
        double height;Long salary;

        try {
            age = Integer.parseInt(ageStr);
            if (age <= 0) throw new NumberFormatException();
        } catch (Exception e) {
            outputArea.setText("Error: Age must be a positive integer!");
            return;
        }

        try {
            height = Double.parseDouble(heightStr);
            if (height <= 0) throw new NumberFormatException();
        } catch (Exception e) {
            outputArea.setText("Error: Height must be a positive number!");
            return;
        }
if(!jerseyNumberStr.isEmpty()) {
    try {
       int i= Integer.parseInt(jerseyNumberStr);
        if(i<=0) throw new NumberFormatException();
    } catch (Exception e) {
        outputArea.setText("Error: Jersey Number must be a positive integer or empty!");
        return;
    }
}
        try {
            salary = Long.parseLong(salaryStr);
            if (salary <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Salary must be a positive number!");
            return;
        }

        Player newPlayer = new Player(name, country, age, height, club, position, jerseyNumberStr, salary);
        PlayerDatabase.addPlayer(newPlayer);
        outputArea.setText("Player added successfully:\n" + newPlayer.toString());
    }
}
