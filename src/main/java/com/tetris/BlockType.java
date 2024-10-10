package com.tetris;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Enum contains all the valid shapes and the corresponding starting positions in the shape for its tiles.
 * <br><br> The valid shapes are:
 * <li>LShape</li> <li>LShapeFlipped</li>
 * <li>TShape</li> <li>Straight</li>
 * <li>ZShape</li> <li>ZShapeFlipped</li>
 * <li>Square</li>
 */
public enum BlockType {
    LShape(4, new int[][]{{0,0}, {1,0}, {2,0}, {2,1}}),
    LShapeFlipped(4, new int[][]{{0,0}, {1,0}, {2,0}, {0,1}}),
    TShape(4, new int[][]{{0,0}, {1,0}, {2,0}, {1,1}}),
    Straight(4, new int[][]{{0,0}, {1,0}, {2,0}, {3,0}}),
    ZShape(4, new int[][]{{0,0}, {1,0}, {1,1}, {2,1}}),
    ZShapeFlipped(4, new int[][]{{0,1}, {1,1}, {1,0}, {2,0}}),
    Square(4, new int[][]{{0,0}, {1,0}, {0,1}, {1,1}});

    /**
     * The amount of tiles per shape.
     */
    private final int tileCount;
    /**
     * The starting positions of tiles in a basic grid stored as an array of {x, y} positions.
     */
    public final int[][] initialTilePositions;

    /**
     * @param tileCount
     * Amount of tiles in the shape.
     * @param initialTilePositions
     * The starting positions of tiles in the shape.
     */
    BlockType(int tileCount, int[][] initialTilePositions) {
        this.tileCount = tileCount;
        this.initialTilePositions = initialTilePositions;
    }

    /**
     * Get a random shape from a list of the valid available ones.
     */
    public static BlockType getRandomBlockType() {
        int randInt = new Random().nextInt(values().length);
        return values()[randInt];
    }

    /**
     * Get a random shape from the valid available ones.
     * @param lastElement
     * Shape to not be generated
     */
    public static BlockType getRandomBlockType(BlockType lastElement) {
        int randInt = new Random().nextInt(values().length);
        if(lastElement == values()[randInt]) {
            return getRandomBlockType(lastElement);
        }
        return values()[randInt];
    }

    /**
     * Get a random shape from the valid available ones.
     * @param lastElements
     * List of shapes to not be generated.
     */
    public static BlockType getRandomBlockType(List<BlockType> lastElements) {
        if (lastElements.isEmpty()){
            return getRandomBlockType();
        }
        int randInt = new Random().nextInt(7);
        if(lastElements.contains(values()[randInt])) {
            return getRandomBlockType(lastElements);
        }
        return values()[randInt];
    }

    /**
     * Returns the amount of tiles in a shape.
     */
    public int getTileCount() {
        return tileCount;
    }
}
