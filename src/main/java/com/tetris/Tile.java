package com.tetris;

public class Tile {
    public int x;
    public int y;
    public int speed;
    public int size;

    Tile(int x, int y, int speed, int size) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
    }

    public void drawTile() {

    }

    public void move(String direction) {
        switch (direction) {
            case "down": y += speed; break;
            case "left": x -= speed; break;
            case "right": x += speed; break;
        }
    }
}
