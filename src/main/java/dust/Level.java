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

public class Level {
    /**
     * level type: static
     */
    public static final int L_STATIC = 2;

    static final int COLUMNS = 80;

    /**
     * level type: cave
     */
    static final int L_CAVE = 0;
    /**
     * level type: maze
     */
    static final int L_MAZE = 3;
    /**
     * level type: rooms
     */
    static final int L_ROOMS = 1;
    static final int ROWS = 24;
    private final RandomNumbers random;
    private final Tile[] tiles = new Tile[ROWS * COLUMNS];
    private Stairs stairs;

    Level(RandomNumbers random) {
        super();
        this.random = random;

        for (int y = 0; y < ROWS; y++) {

            for (int x = 0; x < COLUMNS; x++) {
                tiles[x * ROWS + y] = new Tile(x, y);
            }

        }

    }

    void dig(int x, int y) {
        // tile type: empty
        setTileType(x, y, Tile.GLYPH_EMPTY);
    }

    void generate(int levelType) {

        switch (levelType) {
            case L_CAVE:
                Cave cave = new Cave(this, random);
                cave.draw();
                stairs = new Stairs(tiles, random);
                break;
            case L_ROOMS:
                Rooms rooms = new Rooms(this, random);
                rooms.draw();
                stairs = new Stairs(tiles, random);
                break;
            case L_STATIC:
                LevelStatic levelStatic = new LevelStatic(this, random);
                stairs = levelStatic.draw();
                break;
            case L_MAZE:
                Maze maze = new Maze(this, random);
                maze.generate();
                stairs = new Stairs(tiles, random);
                break;
            default:
                throw new IllegalStateException(levelType + ": no such level type");
        }

    }

    public Stairs getStairs() {
        return stairs;
    }

    Tile getTile(int x, int y) {
        Tile tileXY = null;
        int index = x * ROWS + y;

        if (y >= 0 && x >= 0 && index < tiles.length) {
            // The coordinates are valid.
            tileXY = tiles[index];
        }

        return tileXY;
    }

    char getTileType(int x, int y) {
        Tile tile = getTile(x, y);
        return tile.getType();
    }

    boolean isDiggable(int x, int y) {
        return getTileType(x, y) == Tile.GLYPH_WALL;
    }

    void makeWalls() {

        for (int y = 0; y < Level.ROWS; y++) {
            // tile type: wall
            setTileType(0, y, Tile.GLYPH_WALL);
            setTileType(Level.COLUMNS - 1, y, Tile.GLYPH_WALL);
        }

        for (int x = 0; x < Level.COLUMNS; x++) {
            // tile type: wall
            setTileType(x, 0, Tile.GLYPH_WALL);
            setTileType(x, Level.ROWS - 1, Tile.GLYPH_WALL);
        }

    }

    boolean moveTo(Character creature, int x, int y) {
        Tile destination = getTile(x, y);
        return destination.moveTo(creature);
    }

    void setTileType(int x, int y, char type) {
        Tile tile = getTile(x, y);
        tile.setType(type);
    }

}