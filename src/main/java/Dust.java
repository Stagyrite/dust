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

import dust.*;

/**
 * The entry point to the Dust game.
 */
public class Dust {

    private final ConsoleService consoleService;
    private final LevelGeneratorTask generator;
    private final RandomNumbers random;

    public Dust(LevelGeneratorTask generator, ConsoleService consoleService, RandomNumbers random) {
        super();
        this.generator = generator;
        this.consoleService = consoleService;
        this.random = random;
    }

    public static void main(String[] args) {
        RandomNumbers random = new RandomNumbers();
        LevelGeneratorTask producer = new LevelGeneratorTask(random);

        try {
            Dust game = new Dust(producer, new NdsConsoleService(), random);
            game.play();
        } catch (Throwable e) {
            System.err.println("error: " + e.getMessage());
        } finally {
            // Stop the level generator thread.
            producer.cancel();
        }

    }

    /**
     * Initialize the console, and play the game.
     */
    public void play() {
        // Start the level producer now to pre-generate levels.
        generator.start();
        consoleService.initConsole();
        Player player = new Player(generator, consoleService, random);

        while (consoleService.canPlay()) {
            consoleService.waitForKeyPressed();

            if (player.moveTo()) {
                // The player has won the game.
                break;
            }

        }

        CowSay cowSay = player.getCowSay();
        cowSay.printCow();
    }

}
