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

public class Tile {
    /**
     * the door glyph
     */
    public static final char GLYPH_DOOR = '+';
    /**
     * the empty tile type
     */
    public static final char GLYPH_EMPTY = ' ';
    /**
     * the stairs up glyph
     */
    public static final char GLYPH_STAIRS_DOWN = '>';
    /**
     * the stairs up glyph
     */
    public static final char GLYPH_STAIRS_UP = '<';
    public static final char GLYPH_WALL = '=';
    private final int coordinateX;
    private final int coordinateY;
    private Character creature;
    private char type = GLYPH_EMPTY;

    public Tile(int coordinateX, int coordinateY) {
        super();
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    Character getCreature() {
        return this.creature;
    }

    public void setCreature(Character creature) {
        this.creature = creature;
    }

    char getType() {
        return this.type;
    }

    void setType(char type) {
        this.type = type;
    }

    public int index() {
        return coordinateX * Level.ROWS + coordinateY;
    }

    public int manhattanDistance(Tile tile) {
        int yDiff = coordinateY - tile.getCoordinateY();
        int xDiff = coordinateX - tile.getCoordinateX();
        return Math.abs(yDiff) + Math.abs(xDiff);
    }

    public boolean moveTo(Character newCreature) {
        // Empty fields, stairs and doors can be entered.
        boolean canEnter = type == GLYPH_EMPTY || type == GLYPH_STAIRS_UP || type == GLYPH_STAIRS_DOWN || type == GLYPH_DOOR;
        boolean canStep = creature == null || newCreature == null;

        if (canEnter && canStep) {
            creature = newCreature;
        }

        return canEnter && canStep;
    }
}