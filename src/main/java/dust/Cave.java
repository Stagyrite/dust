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
 * A cave level.
 */
class Cave {
    private static final int CAVE_STEP = 3;

    private static final int MAX_RATIO = 10;

    private static final int MIN_WALL = 5;

    private static final int WALL_RATIO = 4;

    private final Level level;

    private final RandomNumbers random;

    Cave(Level level, RandomNumbers random) {
        super();
        this.level = level;
        this.random = random;
    }

    private int countSurroundingWalls(int x, int y) {
        int numberOfWalls = 0;

        for (int offsetY = -1; offsetY <= 1; offsetY++) {

            for (int offsetX = -1; offsetX <= 1; offsetX++) {

                if (level.isDiggable(x + offsetX, y + offsetY)) {
                    numberOfWalls++;
                }

            }

        }

        return numberOfWalls;
    }

    void draw() {
        init();

        for (int i = 0; i < CAVE_STEP; i++) {
            reduceNoise();
        }

    }

    private void init() {

        for (int y = 1; y < Level.ROWS - 1; y++) {

            for (int x = 1; x < Level.COLUMNS - 1; x++) {
                level.setTileType(x, y, randPick());
            }

        }

        level.makeWalls();
    }

    private char randPick() {
        int x = random.getRandom(MAX_RATIO);
        char tileType;

        if (x < WALL_RATIO) {
            tileType = Tile.GLYPH_WALL;
        } else {
            tileType = Tile.GLYPH_EMPTY;
        }

        return tileType;
    }

    /**
     * For each field check whether it's surrounded by at least 5 walls.
     * If yes, turn it into a wall. If not, dig through it.
     */
    private void reduceNoise() {

        for (int y = 1; y < Level.ROWS - 1; y++) {

            for (int x = 1; x < Level.COLUMNS - 1; x++) {
                int numberOfWalls = countSurroundingWalls(x, y);

                if (numberOfWalls >= MIN_WALL) {
                    // tile type: wall
                    level.setTileType(x, y, Tile.GLYPH_WALL);
                } else {
                    level.dig(x, y);
                }

            }

        }

    }

}
