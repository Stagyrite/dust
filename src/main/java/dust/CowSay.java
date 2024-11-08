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
 * A speaking cow that says about the game end.
 */
public class CowSay {
    private static final int NO_SPACE_DEPTH = 10;
    private final Player player;

    public CowSay(Player player) {
        super();
        this.player = player;
    }

    private String formatDepth() {
        LevelGenerator factory = player.getFactory();
        String depth = String.valueOf(factory.getDepth());

        if (factory.getDepth() < NO_SPACE_DEPTH) {
            depth += " ";
        }

        return depth;
    }

    public void printCow() {

        if (player.isEndGame()) {
            printCow("$$", " ");
        } else {
            printCow("xx", "U");
        }

    }

    private void printCow(String eyes, String mouth) {
        String cow = " __________\n"
                     + "< Level " + formatDepth() + " >\n"
                     + " ----------\n"
                     + "        \\   ^__^\n"
                     + "         \\  (" + eyes + ")\\_______\n"
                     + "            (__)\\       )\\/\\\n"
                     + "             " + mouth + "  ||----w |\n"
                     + "                ||     ||\n\n\n\n\n\n";
        ConsoleService consoleService = player.getConsoleService();
        consoleService.cls();
        consoleService.printScreen(cow);
    }
}
