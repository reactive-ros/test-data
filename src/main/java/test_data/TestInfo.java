package test_data;

import org.reactive_ros.Stream;

import java.util.Arrays;
import java.util.Queue;

/**
 * @author Orestis Melkonian
 */
public class TestInfo {
    public String name;
    public Stream s1, s2;
    public Queue q1, q2;

    public TestInfo(String name, Stream s1, Stream s2) {
        this.name = name;
        this.s1 = s1;
        this.s2 = s2;
    }

    public boolean equality() {
        q1 = s1.toBlocking().toQueue();
        q2 = s2.toBlocking().toQueue();
        return Arrays.equals(q1.toArray(), q2.toArray());
//        return (boolean) Stream.sequenceEqual(s1, s2).toBlocking().first();
    }
}
