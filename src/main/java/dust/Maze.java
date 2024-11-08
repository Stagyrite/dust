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

public class Maze {
    private static final int EAST = 3;
    private static final int NORTH = 0;
    private static final int SOUTH = 1;
    private static final int WEST = 2;
    private static final int[] DIRECTIONS = {NORTH, SOUTH, WEST, EAST};
    private final Level level;
    private final RandomNumbers random;
    private int col;
    private int row;

    Maze(Level level, RandomNumbers random) {
        super();
        this.level = level;
        this.random = random;
    }

    private boolean digMaze(int direction_row, int direction_col) {
        int newRow = row + direction_row * 2;
        int newCol = col + direction_col * 2;
        boolean canDig = valid_position(newRow, Level.COLUMNS)
                         && valid_position(newCol, Level.ROWS)
                         && !isWall(newRow, newCol);

        if (canDig) {
            setTile(newRow, newCol);
            setTile(row + direction_row, col + direction_col);
            row = newRow;
            col = newCol;
        }

        return canDig;
    }

    private boolean digMazeInDirection(int direction) {
        boolean can_dig;

        switch (direction) {
            case NORTH:
                can_dig = digMaze(-1, 0);
                break;
            case SOUTH:
                can_dig = digMaze(1, 0);
                break;
            case WEST:
                can_dig = digMaze(0, -1);
                break;
            case EAST:
                can_dig = digMaze(0, 1);
                break;
            default:
                throw new IllegalStateException(direction + ": unknown direction");
        }

        return can_dig;
    }

    public void generate() {
        int done = 0;

        do {
            row = random.randomOdd(Level.COLUMNS);
            col = random.randomOdd(Level.ROWS);

            if (done == 0) {
                setTile(row, col);
            }

            if (isWall(row, col)) {
                generateMaze();
                done++;
            }

        } while (done < Level.ROWS * Level.COLUMNS / 4);

        level.makeWalls();
    }

    private void generateMaze() {
        int i;
        boolean blocked;

        shuffleDirections();

        do {
            blocked = true;

            if (random.getRandom(DIRECTIONS.length - 1) == 0) {
                /* new direction */
                shuffleDirections();
            }

            for (i = 0; i < DIRECTIONS.length; i++) {

                if (digMazeInDirection(DIRECTIONS[i])) {
                    blocked = false;
                    break;
                }

            }

        } while (!blocked);

    }

    private boolean isWall(int x, int y) {
        char tileType = level.getTileType(x, y);
        return tileType == Tile.GLYPH_WALL;
    }

    private void setTile(int x, int y) {
        level.setTileType(x, y, Tile.GLYPH_WALL);
    }

    private void shuffleDirections() {
        int i;

        for (i = DIRECTIONS.length - 1; i > 0; i--) {
            int j = random.getRandom(i);
            int tmp = DIRECTIONS[i];
            DIRECTIONS[i] = DIRECTIONS[j];
            DIRECTIONS[j] = tmp;
        }

    }

    private boolean valid_position(int position, int max) {
        return position >= 1 && position < max;
    }
}
