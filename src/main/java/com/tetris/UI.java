package com.tetris;

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
    ArrayList<Block> placedBlocks = new ArrayList<>();
    LinkedList<BlockType> lastBlockTypes = new LinkedList<>();

    //clock
    private int timePassed = 0;
    private long lastFrameTime = System.currentTimeMillis();
    private int timeNeeded = 1000;

    @Override
    public void start(Stage gameStage) throws Exception {
        setUI(gameStage);
        gameLoop();
    }

    private void setUI(Stage gameStage) throws Exception {
        gameStage.setTitle("Tetris");
        gameStage.setHeight(800);
        gameStage.setWidth(480);
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
        gameStage.show();

        NewBlock();
    }

    private void NewBlock() throws Exception {
        BlockType currentBlockType = BlockType.getRandomBlockType(lastBlockTypes);
        currentBlock = new Block(currentBlockType, 0,0);
        if (lastBlockTypes.size() >= 3) {
            lastBlockTypes.removeFirst();
            lastBlockTypes.add(currentBlockType);
        } else {
            lastBlockTypes.add(currentBlockType);
        }
    }

    private void gameLoop() {


        Task<Void> task = new Task<>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(25);
                return null;
            }
        };
        task.setOnSucceeded(_ -> {
            // game step
            // draw
            pane.getChildren().clear();
            currentBlock.drawT(pane);



            try {
                //clock
                timePassed += (int) (System.currentTimeMillis()-lastFrameTime);
                lastFrameTime = System.currentTimeMillis();





                //gameTick actions
                if (timePassed > timeNeeded) {
                    currentBlock.y++;
                    timePassed = 0;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //TODO Figure out how tf multithreading works to avoid doing this
            if (running) gameLoop();
        });
        new Thread(task).start();
    }

    private void placeBlock() throws Exception {
        placedBlocks.add(currentBlock);
        lastBlockTypes.add(currentBlock.getBlockType());
        lastBlockTypes.removeFirst();
        currentBlock = new Block(BlockType.getRandomBlockType(lastBlockTypes), 0, 0);


        // block check
        // new block to next block
    }
}