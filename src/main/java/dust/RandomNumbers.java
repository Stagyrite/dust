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

import java.util.Random;

/**
 * A pseudo-random numbers generator.
 */
public class RandomNumbers {

    private static final int MAX_RATIO = 10;

    private final Random random = new Random();

    /**
     * Generate a random number with a specified upper bound.
     *
     * @param bound the upper bound
     * @return the random number
     */
    public int getRandom(int bound) {
        return Math.abs(nextInt()) % bound;
    }

    /**
     * Generate a random number.
     *
     * @return the random number
     */
    private int nextInt() {
        return random.nextInt();
    }

    /**
     * Randomly pick, whether the next level either consists of caves or rooms.
     *
     * @return the level type
     */
    public int randPick() {
        int levelType;

        switch (getRandom(MAX_RATIO)) {
            case 0:
                levelType = Level.L_ROOMS;
                break;
            case 1:
                levelType = Level.L_MAZE;
                break;
            default:
                // The cave ratio is estimated to 80%.
                levelType = Level.L_CAVE;
                break;
        }

        return levelType;
    }

    /**
     * Generate a random odd number.
     *
     * @param max the upper bound
     * @return the random odd number
     */
    public int randomOdd(int max) {
        int number = getRandom(max / 2 - 1);
        number *= 2;
        return 1 + number;
    }

}
