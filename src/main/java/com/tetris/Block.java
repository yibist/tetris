package com.tetris;

public class Block {
    public Tile[] tiles;
    private int rotation;
    private final BlockType type;
    public int x;
    public int y;

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

    }
}