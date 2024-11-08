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
 * A pathfinder: finds a way through a cave. It's useful for testing whether
 * the game is fair at a certain level. A level gets memorized, so it's necessary
 * to keep all the levels passable.
 */
public class Pathfinder {
    /**
     * the level
     */
    private final Level level;
    /**
     * it's true at an index i, when i was visited by the pathfinder
     */
    private final boolean[] visited = new boolean[Level.ROWS * Level.COLUMNS];

    public Pathfinder(Level level) {
        super();
        this.level = level;
    }

    private boolean doFindPath(Tile current) {
        boolean passed = false;

        for (int x = current.getCoordinateX() - 1; x <= current.getCoordinateX() + 1; x++) {

            for (int y = current.getCoordinateY() - 1; y <= current.getCoordinateY() + 1; y++) {
                Tile nextTile = level.getTile(x, y);

                if (nextTile != null && findPath(nextTile)) {
                    passed = true;
                    break;
                }

            }

        }

        return passed;
    }

    private boolean findPath(Tile current) {
        Stairs stairs = level.getStairs();
        // Check whether the current position leads up.
        boolean passed = current.index() == stairs.getStairsUp().index();
        // Don't search further when the tile is a wall or anything already visited.

        if (!passed && !visited[current.index()] && current.getType() != Tile.GLYPH_WALL) {
            visited[current.index()] = true;
            passed = doFindPath(current);
        }

        return passed;
    }

    /**
     * Checks whether a player can be trapped within a level.
     *
     * @return true, when the player is trapped
     */
    public boolean isTrapped() {
        Stairs stairs = level.getStairs();
        Tile stairsDown = stairs.getStairsDown();
        boolean stairless = stairsDown == null || stairs.getStairsUp() == null;
        // Some rooms may not have enough stairs.
        return stairless || !findPath(stairsDown);
    }
}
