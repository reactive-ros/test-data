package test_data;

import org.reactive_ros.Stream;
import org.reactive_ros.util.functions.Func0;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Orestis Melkonian
 */
public final class TestData {

    public static Iterable<TestInfo> tests() {
        Tests tests = new Tests();

        /**
         * Operators
         */
        tests.put("all",
                Stream.just(1, 2, 3, 4, 5).all(i -> i < 6),
                Stream.just(true)
        );
        tests.put("amb_exists",
                Stream.amb(Stream.just(0), Stream.just(1)).exists(i -> i == 0 || i == 1),
                Stream.just(true)
        );
        tests.put("buffer",
                Stream.range(0, 6).buffer(2),
                Stream.just(Arrays.asList(0, 1), Arrays.asList(2, 3), Arrays.asList(4, 5))
        );
        tests.put("cache",
                Stream.range(0,10).cache().cache(),
                Stream.range(0, 10)
        );
        tests.put("cast",
                Stream.range(0, 10).cast(Number.class),
                Stream.range(0, 10)
        );
        tests.put("collect",
                Stream.range(0, 4).collect((Func0<ArrayList<Integer>>) ArrayList::new, ArrayList::add),
                Stream.just(Arrays.asList(0, 1, 2, 3))
        );
        tests.put("concat",
                Stream.concat(Stream.just(1,2,3), Stream.just(4,5,6), Stream.just(7,8,9,10)),
                Stream.range(1,10)
        );
        tests.put("concatMap",
                Stream.just(0, 1, 2).concatMap(i -> Stream.just(i + 10)),
                Stream.just(10, 11, 12)
        );
        tests.put("contains",
                Stream.range(0, 10).contains(5),
                Stream.just(true)
        );
        tests.put("count",
                Stream.range(0, 10).filter(i -> i % 2 == 0).count(),
                Stream.just(5)
        );
        tests.put("defer",
                Stream.defer(() -> Stream.just(1, 2, 3, 4, 5)),
                Stream.just(1, 2, 3, 4, 5)
        );
        tests.put("delay",
                Stream.range(0,6).delay(1, TimeUnit.SECONDS),
                Stream.range(0, 6)
        );
        tests.put("distinct",
                Stream.just(1, 2, 2, 3, 3, 3, 3, 4, 3, 5, 2, 5, 5, 6, 6, 7, 8, 9, 10, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0).distinct(),
                Stream.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0)
        );
        tests.put("elementAt",
                Stream.range(0, 100).elementAt(67),
                Stream.just(67)
        );
        tests.put("exists",
                Stream.range(0, 10).exists(i -> i > 9),
                Stream.just(false)
        );
        tests.put("filter",
                Stream.range(0, 10).filter(i -> i > 8),
                Stream.just(9)
        );
        tests.put("first",
                Stream.range(0, 100).first(),
                Stream.just(0)
        );
        tests.put("flatMap",
                Stream.just(0, 10, 20).flatMap(i -> Stream.range(i, 10)),
                Stream.range(0, 30)
        );
        tests.put("from",
                Stream.from(Arrays.asList(1, 2, 3, 4, 5)),
                Stream.just(1, 2, 3, 4, 5)
        );
        tests.put("id",
                Stream.range(0, 100).id().id().id().id(),
                Stream.range(0, 100)
        );
        tests.put("ignoreElements",
                Stream.range(0, 10).ignoreElements(),
                Stream.empty()
        );
        tests.put("interval_take",
                Stream.interval(100, TimeUnit.MILLISECONDS).take(5).map(Long::intValue),
                Stream.just(0, 1, 2, 3, 4)
        );
        tests.put("loop",
                Stream.just(1).loop(entry -> entry.map(i -> i * 2)).take(5),
                Stream.just(2, 4, 8, 16 ,32)
        );
        tests.put("map",
                Stream.just(0, 1, 2, 3, 4).map(i -> i * i),
                Stream.just(0, 1, 4, 9, 16)
        );
        tests.put("merge1",
                Stream.merge(Stream.just(0,2,4), Stream.just(6,8,10)).exists(i -> i % 2 != 0),
                Stream.just(false)
        );
        tests.put("merge2",
                Stream.merge(Stream.just(Stream.just(0,2,4), Stream.just(6,8,10))).exists(i -> i % 2 != 0),
                Stream.just(false)
        );
        tests.put("ofType",
                Stream.just(0, 1.0, 2, 3.0).ofType(Integer.class),
                Stream.just(0, 2)
        );
        tests.put("onErrorResumeNext",
                Stream.just(0, 1, 2).concatMap(i -> (i == 1) ? Stream.error(new Throwable()) : Stream.just(i)).onErrorResumeNext(Stream.just(10, 20)),
                Stream.just(0, 10, 20)
        );
        tests.put("onErrorReturn",
                Stream.just(0, 1, 2).concatMap(i -> (i == 1) ? Stream.error(new Throwable()) : Stream.just(i)).onErrorReturn(t -> 100),
                Stream.just(0, 100)
        );
        tests.put("reduce1",
                Stream.range(0, 10).reduce((i, j) -> i + j),
                Stream.just(45)
        );
        tests.put("reduce2",
                Stream.range(0, 5).reduce("", (str, i) -> str + i),
                Stream.just("01234")
        );
        tests.put("repeat",
                Stream.just(1, 2, 3).repeat(3),
                Stream.just(1, 2, 3, 1, 2, 3, 1, 2, 3)
        );
        tests.put("scan",
                Stream.just(1, 2, 3, 4).scan(0, (i1, i2) -> i1 + i2),
                Stream.just(0, 1, 3, 6, 10)
        );
        tests.put("sequenceEqual",
                Stream.sequenceEqual(Stream.just(0, 10, 20, 30, 40), Stream.range(0, 5).map(i -> i * 10)),
                Stream.just(true)
        );
        tests.put("skip",
                Stream.range(1, 100).skip(50),
                Stream.range(51, 50)
        );
        tests.put("skipLast",
                Stream.range(1, 100).skipLast(50),
                Stream.range(1, 50)
        );
        tests.put("skipWhile",
                Stream.range(1, 100).skipWhile(i -> i <= 50),
                Stream.range(51, 50)
        );
        tests.put("take",
                Stream.range(0,5).take(2),
                Stream.just(0,1)
        );
        tests.put("takeLast",
                Stream.range(1, 5).takeLast(2),
                Stream.just(4, 5)
        );
        tests.put("takeWhile",
                Stream.range(1, 1000).takeWhile(i -> i < 10),
                Stream.just(1,2,3,4,5,6,7,8,9)
        );
        tests.put("using",
                Stream.using(
                        (Func0<HashSet<Integer>>) HashSet::new,
                        set -> Stream.range(1, 5).filter(i -> i % 2 == 0).map(i -> {
                            set.add(i);
                            return i;
                        }),
                        HashSet::clear
                ),
                Stream.just(2, 4)
        );
        tests.put("window",
                Stream.range(0, 6).window(3),
                Stream.just(Stream.just(0, 1, 2), Stream.just(3, 4, 5))
        );
        tests.put("zip",
                Stream.zip(Stream.just(1, 2, 3), Stream.just("One", "Two", "Three"), (i, str) -> str + "[" + i + "]"),
                Stream.just("One[1]", "Two[2]", "Three[3]")
        );

        /**
         * General tests
         */
        tests.put("test1",
                Stream.just(6,7,8,9,10),
                Stream.just(6,7,8,9,10)
        );
        tests.put("test2",
                Stream.from(1,2,3,4).map(i -> i * i).filter(i -> i % 2 != 0),
                Stream.from(1,9)
        );
        tests.put("test3",
                Stream.from(0,1,2,3,4,5,6,7,8,9,10).take(5).repeat(2),
                Stream.from(0,1,2,3,4,0,1,2,3,4)
        );
        tests.put("test4",
                Stream.just(6f,7f,8f,9f,10f).cache().reduce("", (s, f) -> s + Float.toString(f)),
                Stream.just("6.07.08.09.010.0")
        );
        tests.put("test5",
                Stream.just(3.0).repeat(10).distinct().filter(d -> d > 1).map(d -> d * 5).flatMap(d -> Stream.just(2.0 * d - 5.0)),
                Stream.just(25.0)
        );
        tests.put("test6",
                Stream.just(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5))).repeat(2).doOnNext(l -> l.removeIf(e -> e % 2 == 0)).buffer(2).take(1).map(l -> l.get(0)).map(l -> l.get(0) + l.get(1)).repeat(4),
                Stream.just(4,4,4,4)
        );
        tests.put("test7",
                Stream.just(1, 2, 3, 4, 5).collect((Func0<HashMap<Integer, String>>) HashMap::new, (m, i) -> {
                    if (i % 2 == 0) m.put(i, Integer.toString(i));
                }).map(h -> new HashSet<>(h.values())).map(c -> {
                    String ret = "";
                    for (String s : c) ret += s;
                    return ret;
                }),
                Stream.just("24")
        );
        tests.put("test8",
                Stream.interval(100, TimeUnit.MILLISECONDS).takeWhile(i -> i < 10).map(i -> Long.toString(i)).map(String::length).reduce(0, (i1, i2) -> i1 + i2).cast(Integer.class),
                Stream.just(10)
        );
        tests.put("test9",
                Stream.just(Arrays.asList(1,2,3,4,5)).repeat(2).take(1).map(l -> {
                    int sum = 0;
                    for (int i : l) sum += i;
                    return sum;
                }),
                Stream.just(15)
        );

        return tests.queue;
    }

    private static class Tests {
        public Queue<TestInfo> queue = new LinkedList<>();
        public void put(String name, Stream s1, Stream s2) {
            queue.add(new TestInfo(name, s1, s2));
        }
    }
}