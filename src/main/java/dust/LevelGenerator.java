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

public class LevelGenerator {
    /**
     * the last level
     */
    public static final int END_GAME = 99;
    /**
     * the initial player X and Y coordinates for the player in the town
     */
    public static final int PLAYER_POSITION = 17;
    /**
     * the initial cow X and Y coordinates for the player in the town
     */
    private static final int COW_POSITION = 18;
    private static final Character GLYPH_COW = new Character('c');
    /**
     * the random levels generator
     */
    private final LevelGeneratorTask generator;
    private final RandomNumbers random;
    /**
     * the current depth
     */
    private int depth;
    /**
     * the town level
     */
    private Level townLevel;

    public LevelGenerator(LevelGeneratorTask generator, RandomNumbers random) {
        super();
        this.random = random;
        this.generator = generator;
        generate();
    }

    /**
     * Change the current level by either going up or down the stairs.
     *
     * @param comingUp true, if coming up; false, if coming down
     * @return true, if the player hasn't reached the final level
     */
    public boolean changeLevel(boolean comingUp) {
        boolean canPlay = true;

        if (comingUp) {
            // The player is coming up.

            if (depth > 0) {
                // Ascend to the upper level.
                depth--;
            }

        } else if (depth == END_GAME) {
            // The game is over.
            canPlay = false;
        } else {
            // The player is coming down.
            depth++;
        }

        return canPlay;
    }

    /**
     * Generate a level at the current depth.
     *
     * @return the generated level
     */
    public Level generate() {
        Level level;

        if (depth == 0) {
            // It's a beginning of the game.

            if (townLevel == null) {
                // Create a town level.
                townLevel = new Level(random);
                townLevel.generate(Level.L_STATIC);
                // It's a cow in a ghost town.
                townLevel.moveTo(GLYPH_COW, COW_POSITION, COW_POSITION);
                // It's a first step ever.
                townLevel.moveTo(Player.GLYPH_PLAYER, PLAYER_POSITION, PLAYER_POSITION);
            }

            level = townLevel;
        } else {
            // Generate a new random level.
            level = generator.generate(depth);
        }

        return level;
    }

    public int getDepth() {
        return depth;
    }

}
