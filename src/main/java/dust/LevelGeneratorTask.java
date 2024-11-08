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

/*
 * A level generator that implements a single producer, single consumer design.
 * It's thread-safe on 3DS, as the {@code LevelProducer} object is fully constructed
 * before a consumer thread is started. The producer can always wait
 * for the next level if it's not already consumed (i.e., generated and memorized).
 *
 * The {@code LevelProducer} thread fills a buffer with generated levels, and
 * the {@code LevelGenerator} is used to create a level and to retrieve it
 * from the buffer.
 */
public class LevelGeneratorTask implements Runnable {

    /**
     * the consumer thread
     */
    private final Thread generator = new Thread(this);
    /**
     * a buffer that keeps a level i + 1 at index i
     */
    private final Level[] levels = new Level[LevelGenerator.END_GAME];
    private final RandomNumbers random;

    private volatile boolean cancelled;

    public LevelGeneratorTask(RandomNumbers random) {
        super();
        this.random = random;
    }

    public void cancel() {
        // The consumer won't be cancelled on 3DS.
        cancelled = true;
    }

    private void doGenerate(int depth) {
        Level level;
        int type = random.randPick();
        // Retry the level generation when its maze isn't solvable.

        do {
            level = new Level(random);
            level.generate(type);

            if (cancelled) {
                break;
            }

        } while (new Pathfinder(level).isTrapped());

        // The maze is solvable so the level can be published to a producer thread.
        levels[depth] = level;
        // Notify the producer thread that is possibly waiting for a new level.
        notify();
    }

    /**
     * Block the producer until a requested level is available.
     *
     * @param index the level depth minus one
     * @throws InterruptedException thrown when unexpectedly interrupted
     */
    protected void doWaitForIt(int index) throws InterruptedException {

        synchronized (this) {

            while (levels[index] == null) {
                wait();
            }

            // The consumer thread has notified that it generated a next level.
        }

    }

    /**
     * Generate a non-static level. The first level is static
     * and kept in the {@code LevelGenerator}.
     *
     * @param depth the level depth
     * @return the level
     */
    public Level generate(int depth) {

        while (levels[depth - 1] == null && !cancelled) {
            // It has not yet generated this level.
            waitForIt(depth - 1);
        }

        return levels[depth - 1];
    }

    public void run() {
        /*
         * Generate all the levels in the consumer thread. Thus, the player
         * doesn't have always to wait for the next level to be generated.
         */

        for (int i = 0; i < levels.length && !cancelled; i++) {

            synchronized (this) {
                doGenerate(i);
            }

        }

    }

    /**
     * Start a level consumer thread.
     */
    public void start() {
        generator.start();
    }

    protected void waitForIt(int index) {

        try {
            doWaitForIt(index);
        } catch (InterruptedException interrupted) {
            // This exception is virtually impossible on 3DS.
            throw new IllegalStateException("[illegal state] message: " + interrupted.getMessage(), interrupted);
        }

    }

}
