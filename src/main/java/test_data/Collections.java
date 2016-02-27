package test_data;

import org.reactive_ros.Stream;
import org.reactive_ros.internal.output.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Orestis Melkonian
 */
public class Collections {
    public static List<Stream> streams() {
        return TestData.tests().stream().map(testInfo -> testInfo.s1).collect(Collectors.toList());
    }

    public static List<Output> outputs() {
        return Arrays.asList(
                new NoopOutput(),
                new ActionOutput<>(i -> System.out.println(i)),
                new ActionOutput<>(System.out::println),
                new SinkOutput<>(new TestSink()),
                new MultipleOutput(Arrays.asList(new NoopOutput(),
                        new ActionOutput<>(i -> System.out.println(i)),
                        new ActionOutput<>(System.out::println),
                        new SinkOutput<>(new TestSink())))
        );
    }
}
