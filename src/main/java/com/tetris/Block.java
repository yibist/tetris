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

    public Block(BlockType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;

        tiles = new Tile[type.getTileCount()];

        for (int i = 0; i < this.type.initialTilePositions.length; i++){
            tiles[i] = new Tile(type.initialTilePositions[i][0]+this.x, type.initialTilePositions[i][1]+this.y,32);
        }

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
            tile.drawTile(pane, this.x, this.y);
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