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
        int randInt = new Random().nextInt(7);
        return switch (randInt) {
            case 0 -> BlockType.LShape;
            case 1 -> BlockType.LShapeFlipped;
            case 2 -> BlockType.TShape;
            case 3 -> BlockType.Straight;
            case 4 -> BlockType.ZShape;
            case 5 -> BlockType.ZShapeFlipped;
            case 6 -> BlockType.Square;
            default -> throw new IllegalStateException("Unexpected value: " + randInt);
        };
    }

    /**
     * Get a random shape from the valid available ones.
     * @param lastElement
     * Shape to not be generated
     */
    public static BlockType getRandomBlockType(BlockType lastElement) {
        int randInt = new Random().nextInt(7);
        return switch (randInt) {
            case 0 -> {
                if (lastElement == BlockType.LShape) {
                    yield getRandomBlockType(lastElement);
                }
                yield BlockType.LShape;
            }
            case 1 -> {
                if (lastElement == BlockType.LShapeFlipped) {
                    yield getRandomBlockType(lastElement);
                }
                yield BlockType.LShapeFlipped;
            }
            case 2 -> {
                if (lastElement == BlockType.TShape) {
                    yield getRandomBlockType(lastElement);
                }
                yield BlockType.TShape;
            }
            case 3 -> {
                if (lastElement == BlockType.Straight) {
                    yield getRandomBlockType(lastElement);
                }
                yield BlockType.Straight;
            }
            case 4 -> {
                if (lastElement == BlockType.ZShape) {
                    yield getRandomBlockType(lastElement);
                }
                yield BlockType.ZShape;
            }
            case 5 -> {
                if (lastElement == BlockType.ZShapeFlipped) {
                    yield getRandomBlockType(lastElement);
                }
                yield BlockType.ZShapeFlipped;
            }
            case 6 -> {
                if (lastElement == BlockType.Square) {
                    yield getRandomBlockType(lastElement);
                }
                yield BlockType.Square;
            }
            default -> throw new IllegalStateException("Unexpected value: " + randInt);
        };
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
        return switch (randInt) {
            case 0 -> {
                if (lastElements.contains(BlockType.LShape)) {
                    yield getRandomBlockType(lastElements);
                }
                yield BlockType.LShape;
            }
            case 1 -> {
                if (lastElements.contains(BlockType.LShapeFlipped)) {
                    yield getRandomBlockType(lastElements);
                }
                yield BlockType.LShapeFlipped;
            }
            case 2 -> {
                if (lastElements.contains(BlockType.TShape)) {
                    yield getRandomBlockType(lastElements);
                }
                yield BlockType.TShape;
            }
            case 3 -> {
                if (lastElements.contains(BlockType.Straight)) {
                    yield getRandomBlockType(lastElements);
                }
                yield BlockType.Straight;
            }
            case 4 -> {
                if (lastElements.contains(BlockType.ZShape)) {
                    yield getRandomBlockType(lastElements);
                }
                yield BlockType.ZShape;
            }
            case 5 -> {
                if (lastElements.contains(BlockType.ZShapeFlipped)) {
                    yield getRandomBlockType(lastElements);
                }
                yield BlockType.ZShapeFlipped;
            }
            case 6 -> {
                if (lastElements.contains(BlockType.Square)) {
                    yield getRandomBlockType(lastElements);
                }
                yield BlockType.Square;
            }
            default -> throw new IllegalStateException("Unexpected value: " + randInt);
        };
    }

    /**
     * Returns the amount of tiles in a shape.
     */
    public int getTileCount() {
        return tileCount;
    }
}
