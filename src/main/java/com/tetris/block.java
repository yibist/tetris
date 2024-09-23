package com.tetris;

public class block {
    private int[] cornerArr;
    private int rotation;
    private BlockTypes type;

    public block(BlockTypes type) {
        this.type = type;
        rotation = 0;
    }
}
