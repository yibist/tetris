package com.tetris;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private final static int height = 800;
    private final static int width = 400;
    public final static int tileSize = 32;

    //clock
    private int timePassed = 0;
    private long lastFrameTime = System.currentTimeMillis();
    private final int timeNeeded = 250;

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

        lastBlockTypes.add(null);
        lastBlockTypes.add(null);
        lastBlockTypes.add(null);
        NewBlock();
        gameScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                currentBlock.move("left");
            }
            if (e.getCode() == KeyCode.D) {
                currentBlock.move("right");
            }
            if (e.getCode() == KeyCode.S) {
                if(currentBlock.checkCollision(placedTiles, height))
                    currentBlock.move("down");
            }
        });
    }

    private void NewBlock() {
        BlockType currentBlockType = BlockType.getRandomBlockType(lastBlockTypes);
        currentBlock = new Block(currentBlockType, 0, 0);
        lastBlockTypes.removeFirst();
        lastBlockTypes.add(currentBlockType);
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
                timePassed += (int) (System.currentTimeMillis() - lastFrameTime);
                lastFrameTime = System.currentTimeMillis();

                //gameTick actions
                if (timePassed > timeNeeded) {
                    if (currentBlock.checkCollision(placedTiles, height)) {
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

    private void drawAll() {
        for (Tile tile : placedTiles) {
            tile.drawTile(pane);
        }
    }

    private void placeBlock() throws Exception {
        placedTiles.addAll(List.of(currentBlock.tiles));
        NewBlock();
    }
}