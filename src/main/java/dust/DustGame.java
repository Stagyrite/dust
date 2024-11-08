/*
 * Copyright (c) 2024-current GenB, Inc.
 * All Rights Reserved.
 */
package dust;

/**
 * The Dust game
 *
 * @author mmatiaszowski
 */
public class DustGame {

    private final ConsoleService consoleService;
    private final LevelGeneratorTask generator;
    private final RandomNumbers random;

    public DustGame(LevelGeneratorTask generator, ConsoleService consoleService, RandomNumbers random) {
        super();
        this.generator = generator;
        this.consoleService = consoleService;
        this.random = random;
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
