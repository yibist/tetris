package com.tetris;

import java.lang.reflect.Type;

public class Block {
    private int[] cornerArr;
    private Tile[] tiles;
    private int rotation;
    private BlockTypes type;

    public Block(BlockTypes type) {
        this.type = type;
        cornerArr = new int[type.getCornerCount()];
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
}