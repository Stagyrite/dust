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

class Rooms {
    private static final int HUNDRED = 100;

    private static final int MAX_COUNT = 4;

    private static final int TEN = 10;

    private final Level level;
    private final RandomNumbers random;
    private int count;
    private int dir;

    Rooms(Level level, RandomNumbers random) {
        super();
        this.level = level;
        this.random = random;
    }

    private static int tenPercentOf(int x) {
        return TEN * x / HUNDRED;
    }

    void draw() {
        init();
        split(0, Level.COLUMNS, 0, Level.ROWS);
        level.makeWalls();
    }

    private void init() {

        for (int y = 1; y < Level.ROWS - 1; y++) {

            for (int x = 1; x < Level.COLUMNS - 1; x++) {
                // tile type: wall
                level.setTileType(x, y, Tile.GLYPH_WALL);
            }

        }

    }

    private void split(int minx, int x, int miny, int y) {
        dir ^= 0x1;

        if (count == MAX_COUNT) {
            return;
        }

        int ly;
        int lx;

        if (dir == 1) {
            lx = minx;
            int bound = Math.max(1, y - miny);
            ly = miny + random.getRandom(bound);

            for (; lx < x; lx++) {
                level.dig(lx, ly);
            }

            lx = 0;
        } else {
            int ten = tenPercentOf(x);
            ly = miny;
            lx = ten + random.getRandom(x - ten * 2);

            for (; ly < y; ly++) {
                level.dig(lx, ly);
            }

            ly = 0;
        }

        count += 1;

        if (lx == 0) {
            split(minx, x, miny, ly);
        } else {
            split(minx, lx, miny, y);
        }

        if (ly == 0) {
            split(lx, x, miny, y);
        } else {
            split(minx, x, ly, y);
        }

    }

}
