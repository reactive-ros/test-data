package test_data;

import org.rhea_core.io.Source;
import org.reactivestreams.Subscriber;

/**
 * @author Orestis Melkonian
 */
public class TestSource implements Source<Integer> {
    @Override
    public void subscribe(Subscriber<? super Integer> s) {
        for (int i = 0; i < 10; i++)
            s.onNext(i);
        s.onComplete();
    }
}
