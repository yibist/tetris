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
    ArrayList<Tile> placedTiles = new ArrayList<>();
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
     * the application scene can be set.
     */
    @Override
    public void start(Stage gameStage) {
        setUI(gameStage);
        gameLoop();
    }

    /**
     * Initialization method for display and base variables
     * @param gameStage
     * the primary stage for this application, onto which
     * the application scene can be set.
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
                if(currentBlock.checkCollision(placedTiles, height, width, "left")) {
                    currentBlock.move("left");
                }
            }
            if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
                if(currentBlock.checkCollision(placedTiles, height, width, "right")) {
                    currentBlock.move("right");
                }
            }
            if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
                if(currentBlock.checkCollision(placedTiles, height, width)) {
                    currentBlock.move("down");
                }
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
            drawAll();


            try {
                //clock
                timePassed += (int) (System.currentTimeMillis() - lastFrameTime);
                lastFrameTime = System.currentTimeMillis();

                //gameTick actions
                if (timePassed > timeNeeded) {
                    if (currentBlock.checkCollision(placedTiles, height, width)) {
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
        for (Tile tile : placedTiles) {
            tile.drawTile(pane);
        }
    }

    /**
     * moves all the tiles from a shape into the placed tiles array and creates a new shape.
     */
    private void placeBlock() {
        placedTiles.addAll(List.of(currentBlock.tiles));
        postPlacementChecks();
        NewBlock();
    }

    /**
     * Deletes all placed Tiles at specified y values of the screen.
     */
    //TBD = To be deleted
    private void deleteRows(Collection<Integer> rowsTBD){
        ArrayList<Tile> tilesTBD = new ArrayList<>();

        if (!rowsTBD.isEmpty()) {
            for (Tile tile: placedTiles) {
                if(rowsTBD.contains(tile.y)){
                    tilesTBD.add(tile);
                }
            }
            for (Tile tile: tilesTBD) {
                placedTiles.remove(tile);
            }
        }
    }

    /**
     * Searches for all complete rows that need to be removed.
     * @return
     * Returns a collection of y values that are complete rows and therefore need to be removed.
     */
    private Collection<Integer> getAllRowsTBD(){
        int count = 0;

        ArrayList<Integer> returnVal = new ArrayList<>();
        for (Tile tile: currentBlock.tiles) {
            if(!returnVal.contains(tile.y)){
                returnVal.add(tile.y);
            }
        }

        for (int i = returnVal.size()-1; i >= 0; i--) {
            for (Tile tile: placedTiles) {
                if (tile.y == returnVal.get(i)) count++;
                if(count >= ((height-16) / Tile.size)){
                    break;
                }
            }
            if(count < ((height-16) / Tile.size)){
                returnVal.remove(returnVal.get(i));
            }
            count = 0;
        }
        return returnVal;
    }

    /**
     * Moves all tiles that fit certain characteristics such as:
     * <li>No tiles directly next to it that have empty spaces below them</li>
     * <li>have empty space below them</li>
     */
    private void postDelMove(){
        // ntc = needs to check
        // ntc to check through all tiles so probably a for(Tile tile: placedTiles) loop.
        // would probably need a second one for going through the tiles again

        // ntc for space below it (if that is false it can move straight to the next tile in the array).
        // something like if(tile.y + Tile.size != otherTile.y && tile.x == otherTile.x)
        // will need to store all the tiles that fulfill the requirements

        // ntc if there are tiles right next to it and that those fulfill the same requirements.
        // if the current block has an empty space below it but has neighbors directly above or to the left/right
        // the neighbors will have to be checked for all the other requirements other than the one they were flagged for
        // (if its above check for bellow it won't be run, if its to one of the sides it won't check for that side).

        // loop through all valid tiles and moves them down then check again, but only for the moved tiles
        // to not do excessive calculations for things that have already been calculated.
    }

    /**
     * Runs all checks and operations that should happen after a block is placed.
     */
    private void postPlacementChecks(){
        ArrayList<Integer> rowsTBD = new ArrayList<>(getAllRowsTBD());
        while(!rowsTBD.isEmpty()){
            deleteRows(rowsTBD);
            postDelMove();
            rowsTBD = new ArrayList<>(getAllRowsTBD());
        }
    }
}