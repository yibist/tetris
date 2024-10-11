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
    public void drawTile(Pane pane) {
        ImageView imageView = new ImageView(image);
        imageView.setX(this.x);
        imageView.setY(this.y);

        pane.getChildren().add(imageView);
    }

    /**
     * Moves the tile in the specified direction.
     * @param direction
     * The direction of movement.<br>
     * Valid inputs are:
     * <li>left</li> <li>right</li> <li>down</li>
     * **This parameter is not case-sensitive**.
     */
    public void move(String direction) {
        switch ((direction.toLowerCase())) {
            case "down":
                y += size;
                break;
            case "left":
                x -= size;
                break;
            case "right":
                x += size;
                break;
        }
    }
}
