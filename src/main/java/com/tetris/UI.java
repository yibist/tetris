package com.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class UI extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    Pane pane = new Pane();
    Scene gameScene = new Scene(pane);
    Block currentBlock;
    BlockTypes nextBlockType;
    ArrayList<Block> placedBlocks = new ArrayList<>();

    @Override
    public void start(Stage gameStage) throws Exception {
        setUI(gameStage);
        gameLoop();
    }

    private void setUI (Stage gameStage) {
        gameStage.setTitle("Tetris");
        gameStage.setMinHeight(0);
        gameStage.setMinWidth(0);
        gameStage.setScene(gameScene);
        gameStage.show();
    }

    private void gameLoop() {
        while (true) {

        }
    }

    private void placeBlock() {
        placedBlocks.add(currentBlock);
        currentBlock = new Block(nextBlockType);


        // block check
        // new block to next block
    }
}