package com.tetris;

import java.util.Random;

public enum BlockType {
    LShape(6, "LShape.png"),
    LShapeFlipped(6, "LShapeFlipped.png"),
    TShape(6, "TShape.png"),
    Straight(4, "Straight.png"),
    ZShape(6, "ZShape.png"),
    ZShapeFlipped(6, "ZShapeFlipped.png"),
    Square(4, "Square.png"),;

    private final int cornerCount;
    private final String imageLoc;

    BlockType(int cornerCount, String imageLoc) {
        this.cornerCount = cornerCount;
        this.imageLoc = imageLoc;
    }

    public BlockType getRandomBlockType() throws Exception {
        int randInt = new Random().nextInt(7);
        switch(randInt){
            case 0: return BlockType.LShape;
            case 1: return BlockType.LShapeFlipped;
            case 2: return BlockType.TShape;
            case 3: return BlockType.Straight;
            case 4: return BlockType.ZShape;
            case 5: return BlockType.ZShapeFlipped;
            case 6: return BlockType.Square;
            default: throw new Exception("No such block type associated with number: "+randInt);
        }
    }

    public int getCornerCount() {
        return cornerCount;
    }

    public String getImageLoc() {
        return imageLoc;
    }
}
