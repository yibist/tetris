package com.tetris;

import javafx.scene.layout.Pane;

import java.util.Collection;

public class Block {
    public Tile[] tiles;
    private int rotation;


    public boolean moving = true;

    public Block(BlockType type, int x, int y) {
        tiles = new Tile[type.getTileCount()];

        for (int i = 0; i < type.initialTilePositions.length; i++){
            tiles[i] = new Tile(type.initialTilePositions[i][0]+x, type.initialTilePositions[i][1]+y,32);
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

    public void move(String direction, Collection<Tile> placedTiles, int limitY) {
        if (moving) {
            for (Tile tile : tiles) {
                tile.move(direction);
            }
            checkCollision(placedTiles, limitY);
        }
    }

    public void checkCollision(Collection<Tile> placedTiles, int limitY) {
        for (Tile tile : tiles) {
            for (Tile otherTile : placedTiles) {
                if (tile.y + tile.size == otherTile.y && tile.x == otherTile.x) {
                    moving = false;
                    break;
                }
            }
            if (tile.y+(tile.size*2) >= limitY) {
                moving = false;
                break;
            }
            if (!moving){
                break;
            }
        }
    }
}