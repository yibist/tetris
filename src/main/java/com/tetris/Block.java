package com.tetris;

import javafx.scene.layout.Pane;

import java.util.Collection;

public class Block {
    /**
     * A list of all tiles currently associated with this block
     */
    public Tile[] tiles;
    /**
     * The current rotation of the block.
     * <li>0 - Down</li>
     * <li>1 - Right</li>
     * <li>2 - Up</li>
     * <li>3 - left</li>
     */
    private int rotation;
    /**
     * If the tiles in the block have or have not hit a blocker of some sort in the y direction.<br><br>
     * Some valid blockers are:
     * <li>The y coordinate limit</li>
     * <li>Other tiles</li>
     */
    public boolean moving = true;

    /**
     * Creates a new block with a set of tiles and the default rotation.
     * @param type
     * The type of block to be created. The valid types can be found in the {@link com.tetris.BlockType} enum list.
     * @param x
     * The x coordinate that the newly created block will be placed.
     * @param y
     * The y Coordinate that the newly created block will be placed.
     */
    public Block(BlockType type, int x, int y) {
        this(type, x, y, 0);
    }
    /**
     * Creates a new block with a set of tiles and a preset rotation.
     * @param type
     * The type of block to be created. The valid types can be found in the {@link com.tetris.BlockType} enum list.
     * @param x
     * The x coordinate that the newly created block will be placed.
     * @param y
     * The y Coordinate that the newly created block will be placed.
     */
    public Block(BlockType type, int x, int y, int rotation) {
        tiles = new Tile[type.getTileCount()];

        for (int i = 0; i < type.initialTilePositions.length; i++){
            tiles[i] = new Tile(type.initialTilePositions[i][0]+x, type.initialTilePositions[i][1]+y,32);
        }

        this.rotation = rotation;
    }

    /**
     * changes the rotation of this block which moves the tiles into their new positions
     * @param left
     * Used to determine if the rotation is happening in the left or right direction.
     * @return
     * returns new rotation of the block.
     */
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

    /**
     * Draws the positions of the tiles associated with this block on the UI pane.
     * @param pane
     * The pane on which the Tiles will be drawn.
     */
    public void drawT(Pane pane) {
        for (Tile tile : tiles) {
            tile.drawTile(pane);
        }
    }

    /**
     * Moves all the tiles that are associated with the current block in the specified direction.<br> **Does not have collision detection of any sort**
     * @param direction
     * The direction of movement valid inputs are "left", "right" and "down". This parameter is not case-sensitive.
     */
    public void move(String direction) {
        if (moving) {
            for (Tile tile : tiles) {
                tile.move(direction);
            }
        }
    }

    /**
     * Moves all the tiles that are associated with the current block in the specified direction.
     * @param direction
     * The direction of movement valid inputs are "left", "right" and "down". This parameter is not case-sensitive.
     * @param placedTiles
     * A collection of placed tiles to be used for determining tile on tile collision.
     * @param limitY
     * The limit to how far a block can move down before stopping.
     */
    public void move(String direction, Collection<Tile> placedTiles, int limitY) {
        if (moving) {
            for (Tile tile : tiles) {
                tile.move(direction);
            }
            checkCollision(placedTiles, limitY);
        }
    }

    /**
     * Checks for Collisions between tiles and any edges or tiles that may block movement and rotation.
     * @param placedTiles
     * A collection of already placed Tiles to be used for determining if a tile on tile collision would take place.
     * @param limitY
     * the limit for the y coordinate to be used if no block collisions take place.
     */
    public void checkCollision(Collection<Tile> placedTiles, int limitY) {
        for (Tile tile : tiles) {
            for (Tile otherTile : placedTiles) {
                //TODO fix collision so that it doesnt only detect the down direction
                if (tile.y + tile.size == otherTile.y && tile.x == otherTile.x) {
                    moving = false;
                    break;
                }
            }
            if (tile.y+(tile.size*2) >= limitY) {
                moving = false;
                break;
            }
            if (!moving){
                break;
            }
        }
    }
}