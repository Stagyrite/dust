import dust.LevelGenerator;
import dust.LevelGeneratorTask;
import dust.RandomNumbers;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * A test that covers nearly all the lines of code.
 */
public class TestFightersGuild {

    @Test
    public void generateAllLevels() {
        LevelGeneratorTask consumer = new LevelGeneratorTask(new RandomNumbers());
        consumer.start();
        assertNotNull(consumer.generate(LevelGenerator.END_GAME));
        consumer.cancel();
    }

    @Test
    public void play() {
        RandomNumbers random = new RandomNumbers();
        LevelGeneratorTask consumer = new LevelGeneratorTask(random);
        Dust game = new Dust(consumer, new ConsoleServiceTestImpl(random), random);
        game.play();
        assertNotNull(consumer.generate(LevelGenerator.END_GAME));
    }

}
