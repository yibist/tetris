package com.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;


public class UI extends Application {
    private static boolean running = false;

    public static void main(String[] args) {
        launch(args);
    }
    Pane pane = new Pane();
    Scene gameScene = new Scene(pane);
    Block currentBlock;
    BlockType nextBlockType;
    ArrayList<Block> placedBlocks = new ArrayList<>();
    LinkedList<BlockType> lastBlockTypes = new LinkedList<>();

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
        while (running) {
            
        }
    }

    private void placeBlock() {
        placedBlocks.add(currentBlock);
        currentBlock = new Block(nextBlockType);
        new Block(BlockType.LShape);


        // block check
        // new block to next block
    }
}