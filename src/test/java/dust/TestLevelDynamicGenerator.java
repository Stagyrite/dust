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

import org.junit.Assert;
import org.junit.Test;

import static java.util.Arrays.setAll;

/**
 * A level pregenerator test that tests a thread-safe
 * version of it. The 3DS version has a single-producer,
 * single-consumer design.
 *
 * @author mmatiaszowski
 */
public class TestLevelDynamicGenerator {
    /**
     * A level pregenerator with thread-safe cancellation.
     * The {@code LevelPregenerator} supporting 3DS would hang
     * when testing on a computer.
     */
    private static class ThreadSafeLevelDynamicGenerator extends LevelGeneratorTask {
        public ThreadSafeLevelDynamicGenerator(RandomNumbers random) {
            super(random);
        }

        @Override
        public void cancel() {
            super.cancel();

            synchronized (this) {
                // Cancel the consumer now.
                Thread.currentThread().interrupt();
            }

        }

        @Override
        protected void waitForIt(int index) {

            try {
                doWaitForIt(index);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }

        }

    }

    private final RandomNumbers random = new RandomNumbers();

    /**
     * Test a multiple-producer, single-consumer design.
     * Nevertheless, the game has a single producer.
     */
    @Test
    public void testMultipleProducersSingleConsumer() {
        // Don't cancel.
        testMultipleProducersSingleConsumer(LevelGenerator.END_GAME);
    }

    /**
     * Test a cancellation of the multiple-producer, single-consumer
     * design.
     */
    @Test
    public void testMultipleProducersSingleConsumerCancellation() {
        // Cancel soon after the start.
        testMultipleProducersSingleConsumer(5);
    }

    private void testMultipleProducersSingleConsumer(int cancelAt) {
        LevelGeneratorTask dynamicGenerator = new ThreadSafeLevelDynamicGenerator(random);
        LevelGenerator[] producers = new LevelGenerator[LevelGenerator.END_GAME];
        setAll(producers, i -> new LevelGenerator(dynamicGenerator, random));
        dynamicGenerator.start();
        crawlDown(dynamicGenerator, producers, cancelAt);
    }

    private void crawlDown(LevelGeneratorTask pregenerator, LevelGenerator[] producers, int cancelAt) {

        for (int i = 0; i < producers.length; i++) {
            // Start in the down.
            LevelGenerator producer = producers[i];
            producer.generate();

            if (i == cancelAt) {
                pregenerator.cancel();
            }

            crawlDown(producer, i, i < cancelAt);
            Assert.assertEquals(producer.getDepth(), i);
        }

    }

    private void crawlDown(LevelGenerator producer, int currentLevel, boolean running) {

        for (int j = 0; j < currentLevel; j++) {
            // Crawl down.
            producer.changeLevel(false);
            Level level = producer.generate();
            // The level can be null only if the producer and consumer are cancelled.

            if (running) {
                Assert.assertNotNull(level);
            }

        }

    }

}
