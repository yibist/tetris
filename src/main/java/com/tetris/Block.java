package com.tetris;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class Block {
    public Tile[] tiles;
    private int rotation;
    private final BlockType type;
    public int x;
    public int y;
    public boolean moving = true;

    public Block(BlockType type) {
        this.type = type;
        this.tiles = new Tile[] {
                new Tile(1,1,1,1),
                new Tile(2,1,1,1),
                new Tile(3,1,1,1),
                new Tile(4,1,1,1),
        };
        rotation = 0;
    }

    public int rotate(boolean left) {
        if(left){
            rotation--;
        } else {
            rotation++;
        }
        if(rotation < 0){
            rotation = 3;
        } else if(rotation > 3){
            rotation = 0;
        }
        return rotation;
    }

    public BlockType getBlockType() {
        return type;
    }

    public void drawT(Pane pane) {
        for (Tile tile : tiles) {
            tile.drawTile(pane);
        }
    }

    public void move(String direction) {
        if (moving) {
            for (Tile tile : tiles) {
                tile.move(direction);
            }
        }
    }

    public void move(String direction, Tile[] tiles) {
        if (moving) {
            for (Tile tile : tiles) {
                tile.move(direction);
            }
            checkCollision(tiles);
        }
    }

    public void checkCollision(Tile[] currentTiles) {
        for (Tile tile : currentTiles) {
            for (Tile otherTile : tiles) {
                if (tile.y == otherTile.y+tile.size) {
                    moving = false;
                    break;
                }
            }
            if (!moving){
                break;
            }
        }
    }
}