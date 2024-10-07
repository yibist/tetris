package com.tetris;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;


public class UI extends Application {
    private static boolean running = true;

    public static void main(String[] args) {
        launch(args);
    }

    Pane pane = new Pane();
    Group root = new Group();
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

    private void setUI(Stage gameStage) throws Exception {
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
    }

    private void gameLoop() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(25);
                return null;
            }
        };
        task.setOnSucceeded(event -> {
            // game step
            // draw
            currentBlock.drawT(pane);
            //TODO Figure out how tf multithreading works to avoid doing this
            if (running) gameLoop();
        });
        new Thread(task).start();
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