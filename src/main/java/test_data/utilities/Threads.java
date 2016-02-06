package test_data.utilities;

/**
 * @author Orestis Melkonian
 */
public final class Threads {
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep() {
        sleep(Long.MAX_VALUE);
    }
}
