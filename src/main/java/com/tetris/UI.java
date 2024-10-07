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

    private void setUI (Stage gameStage) throws Exception {
        gameStage.setTitle("Tetris");
        gameStage.setMinHeight(0);
        gameStage.setMinWidth(0);
        gameStage.setScene(gameScene);
        gameStage.show();

        // don't know how to initialize a linked list with values
        lastBlockTypes.add(BlockType.TShape);
        lastBlockTypes.add(null);
        lastBlockTypes.add(null);


        currentBlock = new Block(BlockType.getRandomBlockType());

        placeBlock();
    }

    private void gameLoop() {
        while (running) {


            // game step

            // draw
        }
    }

    private void placeBlock() throws Exception {
        placedBlocks.add(currentBlock);
        lastBlockTypes.add(currentBlock.getBlockType());
        lastBlockTypes.removeFirst();
        currentBlock = new Block(BlockType.getRandomBlockType(lastBlockTypes));


        // block check
        // new block to next block
    }
}