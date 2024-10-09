package com.tetris;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tile {
    public int x;
    public int y;
    public int size;
    public static Image image = new Image("Tile.png", 32.0d, 32.0d, true, true);

    Tile(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void drawTile(Pane pane, int x, int y) {
        ImageView imageView = new ImageView(image);
        imageView.setX((this.x + x) * this.size);
        imageView.setY((this.y + y) * this.size);

        pane.getChildren().add(imageView);
    }

    public void move(String direction) {
        switch (direction) {
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
