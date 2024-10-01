package com.tetris;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class UI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tetris");
        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, 300, 300));

        Image tile = new Image("tile.png", 16.0d, 16.0d, true, true);
        ImageView tileView = new ImageView(tile);
        tileView.setX(1);
        tileView.setY(1);
        tileView.setFitWidth(16.0d);

        primaryStage.show();
    }
}