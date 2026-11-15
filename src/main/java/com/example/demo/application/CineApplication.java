package com.example.demo.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class CineApplication extends Application
{

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(CineApplication.class.getResource("/com/example/demo/view/main-view.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setTitle("CINEMAUQ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
