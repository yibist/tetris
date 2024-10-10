package com.tetris;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class UI extends Application {
    private static boolean running = true;

    public static void main(String[] args) {
        launch(args);
    }

    Pane pane = new Pane();
    Group root = new Group();
    Scene gameScene = new Scene(pane);
    Block currentBlock;
    ArrayList<Tile> placedTiles = new ArrayList<>();
    LinkedList<BlockType> lastBlockTypes = new LinkedList<>();

    // Window/grid size
    private final int height = 800;
    private final int width = 400;

    //clock
    private int timePassed = 0;
    private long lastFrameTime = System.currentTimeMillis();
    private int timeNeeded = 250;

    @Override
    public void start(Stage gameStage) throws Exception {
        setUI(gameStage);
        gameLoop();
    }

    private void setUI(Stage gameStage) throws Exception {
        gameStage.setTitle("Tetris");
        gameStage.setHeight(height);
        gameStage.setWidth(width);
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
            drawAll();



            try {
                //clock
                timePassed += (int) (System.currentTimeMillis()-lastFrameTime);
                lastFrameTime = System.currentTimeMillis();

                //gameTick actions
                if (timePassed > timeNeeded) {
                    if(currentBlock.moving){
                        currentBlock.move("down", placedTiles, height);
                    } else {
                        placeBlock();
                    }
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

    private void drawAll(){
        for(Tile tile: placedTiles){
            tile.drawTile(pane);
        }
    }

    private void placeBlock() throws Exception {
        placedTiles.addAll(List.of(currentBlock.tiles));
        NewBlock();
    }
}