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

public class Display {
    private static final int COLUMNS_VISIBLE = 31;
    private final ConsoleService consoleService;
    private final Player player;
    private final char[][] window = new char[Level.ROWS][Level.COLUMNS];
    /**
     * the minimum visible X coordinate
     */
    private int offset;

    public Display(Player player) {
        super();
        this.consoleService = player.getConsoleService();
        this.player = player;
        clear();
    }

    private void addChar(int x, int y, char c) {
        window[y][x] = c;
    }

    public void clear() {
        offset = 0;
        playerMoved(true);
    }

    /**
     * Finds the current offset of the X coordinate.
     *
     * @return the current offset
     */
    private int findOffset() {
        int part;

        if (player.getCurrentX() < COLUMNS_VISIBLE) {
            part = 0;
        } else if (player.getCurrentX() < 2 * COLUMNS_VISIBLE) {
            part = 1;
        } else {
            part = 2;
        }

        return part * COLUMNS_VISIBLE;
    }

    void playerMoved(boolean alwaysPrint) {
        int nextOffset = findOffset();

        if (offset == nextOffset) {

            if (alwaysPrint) {
                print();
            }

        } else {
            offset = nextOffset;
            // Prevent gluing the first part to the last.
            consoleService.cls();
            print();
        }

    }

    void print() {

        for (int y = 0; y < Level.ROWS; y++) {

            for (int x = 0; x < Level.COLUMNS; x++) {
                tilePrint(x, y);
            }

        }

        consoleService.printScreen(visibleTilesAsString());
    }

    void refresh(int x, int y) {
        tilePrint(x, y);
        int xOffset = x - offset;

        if (xOffset >= 0 && xOffset <= COLUMNS_VISIBLE && x < window[y].length && y < Level.ROWS) {
            consoleService.printCharacter(xOffset, y, window[y][x]);
        }

    }

    void tilePrint(int x, int y) {
        Level level = player.getLevel();
        tilePrint(level.getTile(x, y), x, y);
    }

    private void tilePrint(Tile tile, int x, int y) {
        char type = tile.getType();
        addChar(x, y, type);
        Character creature = tile.getCreature();

        if (creature != null) {
            addChar(x, y, creature.charValue());
        }

    }

    private String visibleTilesAsString() {
        StringBuffer visibleTiles = new StringBuffer();

        for (int y = 0; y < Level.ROWS; y++) {

            for (int x = 0; x <= COLUMNS_VISIBLE; x++) {
                int xOffset = x + offset;

                if (xOffset < window[y].length) {
                    visibleTiles.append(window[y][xOffset]);
                } else {
                    visibleTiles.append(' ');
                }

            }

        }

        return visibleTiles.toString();
    }

}