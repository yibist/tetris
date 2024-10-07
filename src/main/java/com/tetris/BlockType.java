package com.tetris;

import java.util.LinkedList;
import java.util.Random;

public enum BlockType {
    LShape(4, new int[][]{{3,0}, {4,0}, {5,0}, {5,1}}),
    LShapeFlipped(4, new int[][]{{3,0}, {4,0}, {5,0}, {3,1}}),
    TShape(4, new int[][]{{3,0}, {4,0}, {5,0}, {4,1}}),
    Straight(4, new int[][]{{3,0}, {4,0}, {5,0}, {6,0}}),
    ZShape(4, new int[][]{{3,0}, {4,0}, {4,1}, {5,1}}),
    ZShapeFlipped(4, new int[][]{{3,1}, {4,1}, {4,0}, {5,0}}),
    Square(4, new int[][]{{4,0}, {5,0}, {4,1}, {5,1}});

    private final int tileCount;
    public final int[][] initialTilePositions;

    BlockType(int tileCount, int[][] intitialTilePositions) {
        this.tileCount = tileCount;
        this.initialTilePositions = intitialTilePositions;
    }

    public static BlockType getRandomBlockType() throws Exception {
        int randInt = new Random().nextInt(7);
        return switch (randInt) {
            case 0 -> BlockType.LShape;
            case 1 -> BlockType.LShapeFlipped;
            case 2 -> BlockType.TShape;
            case 3 -> BlockType.Straight;
            case 4 -> BlockType.ZShape;
            case 5 -> BlockType.ZShapeFlipped;
            case 6 -> BlockType.Square;
            default -> throw new Exception("No such block type associated with number: " + randInt);
        };
    }

    public static BlockType getRandomBlockType(LinkedList<BlockType> lastElements) throws Exception {
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
            default -> throw new Exception("No such block type associated with number: " + randInt);
        };
    }

    public int getTileCount() {
        return tileCount;
    }
}
