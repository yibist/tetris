package com.tetris;

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

    public int getCornerCount() {
        return cornerCount;
    }

    public String getImageLoc() {
        return imageLoc;
    }
}
