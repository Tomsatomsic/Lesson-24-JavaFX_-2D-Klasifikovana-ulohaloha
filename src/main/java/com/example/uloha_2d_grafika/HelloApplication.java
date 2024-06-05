package com.example.uloha_2d_grafika;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    public static final int SCREEN_WIDTH = 1900;
    public static final int SCREEN_HEIGHT = 920;

    GraphicsContext graphicsContext;


    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Klasifikovaná úloha");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}