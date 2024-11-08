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
 * A test service that steers the game to achieve a level below the town level.
 */
public class ConsoleServiceTestImpl implements ConsoleService {

    private static final int[] DIRECTIONS = new int[]{Key.RIGHT, Key.LEFT, Key.UP, Key.DOWN};

    private final int[] INITIAL_STEPS = new int[]{Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            // Step down.
            Key.A,
            // Step up to the town level.
            Key.A,
            // Back down there.
            Key.A};

    private int keyPressed;

    private boolean keyReleased;

    private int step;

    private int stepsLeft = INITIAL_STEPS.length + 450;

    private final RandomNumbers random;

    public ConsoleServiceTestImpl(RandomNumbers random) {
        super();
        this.random = random;
    }

    @Override
    public void cls() {

    }

    @Override
    public void initConsole() {

    }

    @Override
    public int getKeyPressed() {
        keyReleased = true;
        return keyPressed;
    }

    @Override
    public boolean canPlay() {
        return stepsLeft > 0 || !keyReleased;
    }

    @Override
    public void lcdSwap() {

    }

    @Override
    public void waitForKeyPressed() {
        keyReleased = stepsLeft == 0;

        if (!keyReleased) {
            stepsLeft--;

            if (step < INITIAL_STEPS.length) {
                keyPressed = INITIAL_STEPS[step];
                step++;
            } else {
                // Make random steps at a level under the town.
                keyPressed = DIRECTIONS[random.getRandom(DIRECTIONS.length)];
            }

        }

    }

    @Override
    public void printScreen(String content) {
        // Don't print.
    }

    @Override
    public void printCharacter(int x, int y, char c) {
        // Don't print.
    }
}
