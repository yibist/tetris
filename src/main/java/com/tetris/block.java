package com.tetris;

public class block {
    private int[] cornerArr;
    private int rotation;
    private BlockTypes type;

    public block(BlockTypes type) {
        this.type = type;
        cornerArr = new int[this.type.getCornerCount()];
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
}