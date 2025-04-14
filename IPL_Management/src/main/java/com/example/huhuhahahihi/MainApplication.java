package com.example.huhuhahahihi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Home.fxml"));


        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        HomeController controller = (HomeController)fxmlLoader.getController();
       controller.setStage(stage);
        stage.setTitle("Hello!");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        final String INPUT_FILE_NAME = "src/main/java/com/example/huhuhahahihi/players.txt";
         final String OUTPUT_FILE_NAME = "src/main/java/com/example/huhuhahahihi/players.txt";

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line == null)
                break;
            String[] s = line.split(",");
            PlayerDatabase.addPlayer(new Player(s[0], s[1], Integer.parseInt(s[2]), Double.parseDouble(s[3]), s[4],
                    s[5], s[6], Long.parseLong(s[7])));
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        launch();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Player p : PlayerDatabase.players) {
            try {
                bw.write(p.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                bw.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}