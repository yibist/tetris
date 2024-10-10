package com.tetris;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tile {
    public int x;
    public int y;
    public static final int size = 32;
    public static Image image = new Image("Tile.png", Tile.size, Tile.size, true, true);

    Tile(int x, int y) {
        this.x = x * size;
        this.y = y * size;
    }

    public void drawTile(Pane pane) {
        ImageView imageView = new ImageView(image);
        imageView.setX(this.x);
        imageView.setY(this.y);

        pane.getChildren().add(imageView);
    }

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
