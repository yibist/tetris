package com.tetris;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tile {
    /**
     * x position of the tile.
     */
    public int x;
    /**
     * y position of the tile.
     */
    public int y;
    /**
     * Size of the tile in pixels
     * <br>**Used in many locations including screen size and collisions**<br>
     * **CHANGE AT OWN RISK**
     */
    public static final int size = 32;
    /**
     * Tile image sprite
     */
    public static Image image = new Image("Tile.png", Tile.size, Tile.size, true, true);

    /**
     * **ALL PROVIDED PARAMETERS WILL BE MULTIPLIED BY SIZE**
     * @param x
     * x position of tile
     * @param y
     * y position of tile
     */
    public Tile(int x, int y) {
        this.x = x * size;
        this.y = y * size;
    }

    /**
     * Draw Tile on pane
     * @param pane
     * Pane tile will be drawn on
     */
    public void drawTile(Pane pane, Block block) {
        ImageView imageView = new ImageView(image);
        imageView.setX((this.x + block.x) * Tile.size);
        imageView.setY((this.y + block.y) * Tile.size);

        pane.getChildren().add(imageView);
    }

}
