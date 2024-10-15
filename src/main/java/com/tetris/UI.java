package com.tetris;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class UI extends Application {
    /**
     * Whether the game is running or not
     */
    private static final boolean running = true;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Main display of all game objects.
     */
    Pane pane = new Pane();
    /**
     * Contains the main pane of the program.
     */
    Scene gameScene = new Scene(pane);
    /**
     * The current block that is being controlled by the player.
     */
    Block currentBlock;
    /**
     * All placed tiles that are no longer moving and can be destroyed.
     */
    ArrayList<Block> placedBlocks = new ArrayList<>();
    /**
     * Previously placed shapes.
     */
    LinkedList<BlockType> lastBlockTypes = new LinkedList<>();

    // Window/grid size
    /**
     * **MUST BE DIVISIBLE BY TILESIZE**<br>
     * Height of the window.
     */
    private final static int height = 800;
    /**
     * **MUST BE 16 HIGHER THAN A MULTIPLE OF TILESIZE**<br>
     * Width of the window.
     */
    private final static int width = 336;

    //clock
    /**
     * Time that has passed since last frame
     */
    private int timePassed = 0;
    /**
     * Time the last frame happened
     */
    private long lastFrameTime = System.currentTimeMillis();
    /**
     * How much time between frames
     */
    private final int timeNeeded = 250;

    /**
     * @param gameStage the primary stage for this application, onto which
     *                  the application scene can be set.
     */
    @Override
    public void start(Stage gameStage) {
        setUI(gameStage);
        gameLoop();
    }

    /**
     * Initialization method for display and base variables
     *
     * @param gameStage the primary stage for this application, onto which
     *                  the application scene can be set.
     */
    private void setUI(Stage gameStage) {
        gameStage.setTitle("Tetris");
        gameStage.setHeight(height);
        gameStage.setWidth(width);
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
        gameStage.show();

        for (int i = 0; i < 3; i++) {
            lastBlockTypes.add(null);
        }
        NewBlock();

        gameScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
                if (currentBlock.checkCollision(placedBlocks, height, width, "left")) {
                    currentBlock.move("left");
                }
            }
            if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
                if (currentBlock.checkCollision(placedBlocks, height, width, "right")) {
                    currentBlock.move("right");
                }
            }
            if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
                if (currentBlock.checkCollision(placedBlocks, height, width)) {
                    currentBlock.move("down");
                }
            }
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
                currentBlock.rotate();
            }
        });
    }

    /**
     * Creates a new block using a random shape excluding a set amount of previously generated ones.
     */
    private void NewBlock() {
        BlockType currentBlockType = BlockType.getRandomBlockType(lastBlockTypes);
        currentBlock = new Block(currentBlockType, 0, 0);
        lastBlockTypes.removeFirst();
        lastBlockTypes.add(currentBlockType);
    }

    /**
     * The main loop of the program all the logic that executes multiple times should be done in here.
     */
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
            System.out.println(currentBlock.y);
            drawAll();


            try {
                //clock
                timePassed += (int) (System.currentTimeMillis() - lastFrameTime);
                lastFrameTime = System.currentTimeMillis();

                //gameTick actions
                if (timePassed > timeNeeded) {
                    if (currentBlock.checkCollision(placedBlocks, height, width)) {
                        currentBlock.move("down");
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

    /**
     * Draws all placed tiles on the provided pane
     */
    private void drawAll() {
        for (Block block : placedBlocks) {

        }
    }

    /**
     * moves all the tiles from a shape into the placed tiles array and creates a new shape.
     */
    private void placeBlock() {
        placedBlocks.add(currentBlock);
        NewBlock();
    }
}