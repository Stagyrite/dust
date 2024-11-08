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

import nds.Key;

/**
 * A player character crawling the dungeon.
 */
public class Player {
    /**
     * the player glyph
     */
    public static final Character GLYPH_PLAYER = new Character('@');
    private final ConsoleService consoleService;
    private final CowSay cowSay;
    private final Display display;
    private final LevelGenerator generator;
    /**
     * the current coordinate X
     */
    private int currentX = LevelGenerator.PLAYER_POSITION;
    /**
     * the current coordinate Y
     */
    private int currentY = LevelGenerator.PLAYER_POSITION;
    private boolean endGame;

    public Player(LevelGeneratorTask dynamicGenerator, ConsoleService consoleService, RandomNumbers random) {
        super();
        this.consoleService = consoleService;
        generator = new LevelGenerator(dynamicGenerator, random);
        cowSay = new CowSay(this);
        display = new Display(this);
    }

    public ConsoleService getConsoleService() {
        return consoleService;
    }

    public CowSay getCowSay() {
        return cowSay;
    }

    public int getCurrentX() {
        return currentX;
    }

    public LevelGenerator getFactory() {
        return generator;
    }

    public Level getLevel() {
        return generator.generate();
    }

    private void handleDirections() {
        int nextX = currentX;

        if (isKeyPressed(Key.RIGHT)) {
            nextX++;
        } else if (isKeyPressed(Key.LEFT)) {
            nextX--;
        }

        int nextY = currentY;

        if (isKeyPressed(Key.DOWN)) {
            nextY++;
        } else if (isKeyPressed(Key.UP)) {
            nextY--;
        }

        moveTo(nextX, nextY);
    }

    private void handleStairs() {
        Level level = getLevel();
        char type = level.getTileType(currentX, currentY);
        boolean comingUp = type == Tile.GLYPH_STAIRS_UP;

        if (type == Tile.GLYPH_STAIRS_DOWN || comingUp) {

            if (generator.changeLevel(comingUp)) {
                handleStairs(comingUp);
            } else {
                endGame = true;
            }

        }

    }

    private void handleStairs(boolean comingUp) {
        consoleService.cls();
        Level level = generator.generate();
        Stairs stairs = level.getStairs();
        Tile wayBack = stairs.getWayBack(comingUp);
        wayBack.setCreature(GLYPH_PLAYER);
        currentX = wayBack.getCoordinateX();
        currentY = wayBack.getCoordinateY();
        display.clear();
    }

    public boolean isEndGame() {
        return endGame;
    }

    private boolean isKeyPressed(int key) {
        int pressed = consoleService.getKeyPressed();
        return (pressed & key) != 0;
    }

    private void moveTo(int nextX, int nextY) {
        Level level = getLevel();

        if (level.moveTo(GLYPH_PLAYER, nextX, nextY)) {
            // Clean the previously occupied tile.
            level.moveTo(null, currentX, currentY);
            display.refresh(nextX, nextY);
            display.refresh(currentX, currentY);
            currentX = nextX;
            currentY = nextY;
            display.playerMoved(false);
        }

    }

    public boolean moveTo() {
        handleDirections();

        if (isKeyPressed(Key.A)) {
            handleStairs();
        } else if (isKeyPressed(Key.SELECT)) {
            consoleService.lcdSwap();
        }

        return endGame;
    }
}
