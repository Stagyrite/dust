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

import nds.Bios;
import nds.Console;
import nds.Key;
import nds.Video;

/**
 * A console service for use with J2ME MIDP implementation.
 */
public class NdsConsoleService implements ConsoleService {
    private static final int REPEAT = 10;

    private int keyPressed;

    public boolean canPlay() {
        Key.scan();
        return (keyPressed & Key.START) == 0;
    }

    public void cls() {
        Console.cls();
    }

    public int getKeyPressed() {
        return keyPressed;
    }

    public void initConsole() {
        Console.cll();
        // Wait for the start key to be released unless it's not pressed at all.

        while ((Key.held() & Key.START) != 0) {
            Key.scan();
            Bios.swiWaitForVBlank();
        }

    }

    public void lcdSwap() {
        Video.lcdSwap();
    }

    public void printCharacter(int x, int y, char c) {
        Console.setpos(x, y);
        System.out.print(c);
    }

    public void printScreen(String content) {
        Console.setpos(0, 0);
        System.out.print(content);
    }

    public void waitForKeyPressed() {
        // press it

        while ((keyPressed = Key.held()) == 0) {
            Key.scan();
            Bios.swiWaitForVBlank();
        }

        // release it
        int repeat = REPEAT;

        while ((Key.held() & keyPressed) != 0 && repeat-- > 0) {
            Key.scan();
            Bios.swiWaitForVBlank();
        }

    }
}
