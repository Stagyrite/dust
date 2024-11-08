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
 * A console service.
 */
public interface ConsoleService {
    /**
     * Checks whether the start key is not pressed.
     *
     * @return true, if the start key is not pressed
     */
    boolean canPlay();

    /**
     * Clears the screen.
     */
    void cls();

    /**
     * Gets the currently pressed key.
     *
     * @return the key pressed
     */
    int getKeyPressed();

    /**
     * Clears the screen and resets the cursor.
     * Waits for the start key to be released.
     */
    void initConsole();

    /**
     * Swaps the two LCD screens.
     */
    void lcdSwap();

    /**
     * Prints a single character.
     *
     * @param x the X coordinates
     * @param y the Y coordinates
     * @param c the character
     */
    void printCharacter(int x, int y, char c);

    /**
     * Prints the new screen content.
     *
     * @param content the screen content
     */
    void printScreen(String content);

    /**
     * Waits until any key is pressed.
     */
    void waitForKeyPressed();
}
