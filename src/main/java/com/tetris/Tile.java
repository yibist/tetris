package com.tetris;

    import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tile {
    public int x;
    public int y;
    public int speed;
    public int size;
    public static Image image = new Image("Tile.png", 32.0d, 32.0d, true, true);

    Tile(int x, int y, int speed, int size) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
    }

    public void drawTile(Pane pane) {
        ImageView imageView = new ImageView(image);
        imageView.setX(this.x);
        imageView.setY(this.y);

        pane.getChildren().add(imageView);
    }

    public void move(String direction) {
        switch (direction) {
            case "down": y += speed; break;
            case "left": x -= speed; break;
            case "right": x += speed; break;
        }
    }
}
