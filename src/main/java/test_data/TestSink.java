package test_data;

import org.rhea_core.io.Sink;
import org.reactivestreams.Subscription;

/**
 * @author Orestis Melkonian
 */
public class TestSink implements Sink<Integer> {
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println(integer + 100);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(t);
    }

    @Override
    public void onComplete() {
        System.out.println("Complete");
    }
}
