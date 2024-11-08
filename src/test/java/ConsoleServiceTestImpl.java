import nds.Key;
import dust.ConsoleService;
import dust.RandomNumbers;

/**
 * A test service that steers the game to achieve a level below the town level.
 */
public class ConsoleServiceTestImpl implements ConsoleService {

    private static final int[] DIRECTIONS = new int[]{Key.RIGHT, Key.LEFT, Key.UP, Key.DOWN};

    private final int[] INITIAL_STEPS = new int[]{Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.RIGHT,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            Key.UP,
            // Step down.
            Key.A,
            // Step up to the town level.
            Key.A,
            // Back down there.
            Key.A};
    private final char[][] currentWindow = new char[32][25];

    private int keyPressed;

    private boolean keyReleased;

    private int step;

    private int stepsLeft = INITIAL_STEPS.length + 450;

    private final RandomNumbers random;

    public ConsoleServiceTestImpl(RandomNumbers random) {
        super();
        this.random = random;
    }

    @Override
    public void cls() {

    }

    @Override
    public void initConsole() {

    }

    @Override
    public int getKeyPressed() {
        keyReleased = true;
        return keyPressed;
    }

    @Override
    public boolean canPlay() {
        return stepsLeft > 0 || !keyReleased;
    }

    @Override
    public void lcdSwap() {

    }

    @Override
    public void waitForKeyPressed() {
        keyReleased = stepsLeft == 0;

        if (!keyReleased) {
            stepsLeft--;

            if (step < INITIAL_STEPS.length) {
                keyPressed = INITIAL_STEPS[step];
                step++;
            } else {
                // Make random steps at a level under the town.
                keyPressed = DIRECTIONS[random.getRandom(DIRECTIONS.length)];
            }

        }

    }

    @Override
    public void printScreen(String content) {
        int row = 0, col = 0;

        for (int i = 0; i < content.length(); i++) {
            currentWindow[col][row] = content.charAt(i);
            col++;

            if (i % 32 == 0) {
                row++;
                col = 0;
            }

        }

        printCurrentWindow();
    }

    private void printCurrentWindow() {

        for (int row = 0; row < currentWindow[0].length; row++) {

            for (char[] col : currentWindow) {
                System.out.print(col[row]);
            }

            System.out.println();
        }

    }

    @Override
    public void printCharacter(int x, int y, char c) {
        // Don't print.
    }
}
