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
        this.x = x * size;
        this.y = y * size;
        this.size = size;
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
