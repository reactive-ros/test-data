package test_data;

import org.reactive_ros.Stream;

/**
 * @author Orestis Melkonian
 */
public class TestInfo {
    public String name;
    public Stream s1;
    public Stream s2;

    public TestInfo(String name, Stream s1, Stream s2) {
        this.name = name;
        this.s1 = s1;
        this.s2 = s2;
    }

    public boolean equality() {
        return (boolean) Stream.sequenceEqual(s1, s2).toBlocking().first();
    }
}
