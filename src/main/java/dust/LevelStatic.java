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

class LevelStatic {
    /**
     * a town level
     */
    private static final char[] TOWN = ("===============================================================================\n"
                                        + "=                                                                             =\n"
                                        + "=                                                                             =\n"
                                        + "=                                                                             =\n"
                                        + "=                                                                             =\n"
                                        + "=                                                                             =\n"
                                        + "=                                                                             =\n"
                                        + "=             ============= =============                                     =\n"
                                        + "=             =           = =           =>                                    =\n"
                                        + "=             =           = =           =                                     =\n"
                                        + "=             =           = =           =                                     =\n"
                                        + "=             =           = =           =                                     =\n"
                                        + "=  ==+======= =           = =           =                                     =\n"
                                        + "=  =    =   = =           = =           =                                     =\n"
                                        + "=  =    +   + +           + +           =                                     =\n"
                                        + "=  =    =   = =           = =           =                                     =\n"
                                        + "=  ========== ============= =============                                     =\n"
                                        + "=                                                                             =\n"
                                        + "=                                                                             =\n"
                                        + "=                                                                             =\n"
                                        + "=                                                                             =\n"
                                        + "=                                                                             =\n"
                                        + "=                                                                             =\n"
                                        + "===============================================================================").toCharArray();

    private final Level level;

    private final Stairs stairs;

    LevelStatic(Level level, RandomNumbers random) {
        super();
        this.level = level;
        stairs = new Stairs(random);
    }

    Stairs draw() {

        for (int y = 0; y < Level.ROWS; y++) {

            for (int x = 0; x < Level.COLUMNS; x++) {
                draw(x, y);
            }

        }

        return stairs;
    }

    private void draw(int x, int y) {
        char tile = getTile(x, y);

        switch (tile) {
            case Tile.GLYPH_EMPTY:
                level.dig(x, y);
                break;
            case Tile.GLYPH_STAIRS_DOWN:
                stairs.setStairsDown(level.getTile(x, y));
                level.setTileType(x, y, tile);
                break;
            case Tile.GLYPH_STAIRS_UP:
                stairs.setStairsUp(level.getTile(x, y));
            case Tile.GLYPH_WALL:
            case Tile.GLYPH_DOOR:
                level.setTileType(x, y, tile);
                break;
            case '\0':
            case '\r':
            case '\n':
                break;
            default:
                throw new IllegalStateException(tile + ": unknown tile");
        }

    }

    private char getTile(int x, int y) {
        char tile;
        int index = x + y * Level.COLUMNS;

        if (index < TOWN.length) {
            tile = TOWN[index];
        } else {
            // out of bounds
            tile = Tile.GLYPH_EMPTY;
        }

        return tile;
    }

}
