package com.tetris;

public class Block {
    public Tile[] tiles;
    private int rotation;
    private final BlockType type;
    public int x;
    public int y;
    public boolean moving = true;

    public Block(BlockType type) {
        this.type = type;
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

    public String getImageLocation() {
        return type.getImageLoc();
    }

    public BlockType getBlockType() {
        return type;
    }

    public void drawT() {
        for (Tile tile : tiles) {
            tile.drawTile();
        }
    }

    public void move(String direction) {
        if (moving) {
            for (Tile tile : tiles) {
                tile.move(direction);
            }
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