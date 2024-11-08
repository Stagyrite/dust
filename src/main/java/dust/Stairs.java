/*
 * Copyright (c) 2024 Maciej Matiaszowski
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 */
package dust;

/**
 * The stairs leading up and down the dungeon.
 */
public class Stairs {
    /**
     * the minimum distance between the stairs
     */
    private static final int SAFE_DISTANCE = 30;
    private final RandomNumbers random;
    /**
     * the array of empty tiles
     */
    private Tile[] emptyTiles;
    /**
     * the stairs coming down
     */
    private Tile stairsDown;
    /**
     * the stairs coming up
     */
    private Tile stairsUp;
    /**
     * the index of the top element of the 'emptyTiles' array
     */
    private int top = -1;

    public Stairs(RandomNumbers random) {
        super();
        this.random = random;
    }

    public Stairs(Tile[] tiles, RandomNumbers random) {
        this(random);
        emptyTiles = new Tile[tiles.length];

        for (int i = 0; i < tiles.length; i++) {
            Tile tile = tiles[i];

            if (tile.getType() == Tile.GLYPH_EMPTY) {
                // tile type: empty
                emptyTiles[++top] = tile;
            }

        }

        addStairs();
    }

    private Tile addStair(char tileType) {
        int tileIndex = random.getRandom(top + 1);
        Tile emptyTile = emptyTiles[tileIndex];
        emptyTile.setType(tileType);
        emptyTiles[tileIndex] = emptyTiles[top];
        top--;
        return emptyTile;
    }

    private void addStairs() {

        if (top >= 0) {
            stairsDown = addStair(Tile.GLYPH_STAIRS_DOWN);
        }

        while (top >= 0) {
            stairsUp = addStair(Tile.GLYPH_STAIRS_UP);

            if (stairsDown.getCoordinateX() != stairsUp.getCoordinateX()
                && stairsDown.getCoordinateY() != stairsUp.getCoordinateY()
                && stairsDown.manhattanDistance(stairsUp) > SAFE_DISTANCE) {
                // Stairs should be located far from each other in different rows and columns.
                break;
            } else if (top >= 0) {
                stairsUp.setType(Tile.GLYPH_EMPTY);
            }

        }

    }

    public Tile getStairsDown() {
        return stairsDown;
    }

    public void setStairsDown(Tile stairsDown) {
        this.stairsDown = stairsDown;
    }

    public Tile getStairsUp() {
        return stairsUp;
    }

    public void setStairsUp(Tile stairsUp) {
        this.stairsUp = stairsUp;
    }

    public Tile getWayBack(boolean comingUp) {
        return comingUp ? stairsDown : stairsUp;
    }
}
