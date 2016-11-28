package com.fererlab.java8;

import com.fererlab.java8.enterprise.UserEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8 {

    public static void main(String[] args) {
        int targetTotal = 24502500;
        int result = n3find(targetTotal, 1, 1);
        System.out.println("result = " + result);
    }

    private static int n3find(int targetTotal, int current, int currentTotal) {
        if (currentTotal > targetTotal)
            return targetTotal - currentTotal;
        else if (currentTotal == targetTotal)
            return current;
        else
            return n3find(targetTotal, current + 1, currentTotal + ((current + 1) * (current + 1) * (current + 1)));
    }


    private void findDuplicatesImperative() {
        int[] integers = IntStream.generate(() -> new Random().nextInt(100)).limit(101).toArray();
        Map<Integer, Integer> intAndCount = new HashMap<>();
        for (int i : integers) {
            if (intAndCount.containsKey(i)) {
                intAndCount.put(i, intAndCount.get(i) + 1);
            } else {
                intAndCount.put(i, 1);
            }
        }
        for (Integer key : intAndCount.keySet()) {
            if (intAndCount.get(key) > 1) {
                System.out.println("found duplicate: " + key + " #" + intAndCount.get(key));
            }
        }
    }


    private void test() {
        streamGenerate();
        streamOf();
        streamInt();
        streamIntFuncs();
        streamUser();
        streamPredicate(2);
        streamFindDouble();
        streamFunctionPredicate();
        streamReduce();
    }

    private void streamReduce() {
        int total = IntStream.range(0, 1000).boxed().reduce(0, Math::addExact);
        System.out.println("reduce total = " + total);
    }

    private void streamFunctionPredicate() {
        Function<Integer, Predicate<Integer>> isGreaterThan = greater -> i -> i > greater;

        int total = IntStream.generate(() -> new Random().nextInt(1_000_000)).limit(1_000).boxed()
                .filter(isGreaterThan.apply(3)).reduce((i1, i2) -> i1 + i2).get();
        System.out.println("total = " + total);
    }

    private void streamFindDouble() {
        System.out.println("---streamFindDouble");

        long time = System.nanoTime();
        int[] ints = IntStream.generate(() -> new Random().nextInt(1_000_000_000)).limit(10_000_000/*_000*/).toArray();
        IntStream integerStream = IntStream.of(ints);
        System.out.println(((System.nanoTime() - time) / 1_000_000_000.0) + " ints created");

        time = System.nanoTime();
        int total = 0;
        for (int i : ints) {
            if (i > 3 && i % 2 == 0) {
                total += new UserEntity(i, String.valueOf(i)).getId();
            }
        }
        System.out.println(((System.nanoTime() - time) / 1_000_000_000.0) + " total = " + total);


        time = System.nanoTime();
        total = integerStream/*.filter(i-> i > 3 && i % 2 == 0).*/
                .parallel()
                .map(i -> {
                    if (i > 3 && i % 2 == 0)
                        return new UserEntity(i, String.valueOf(i)).getId();
                    else
                        return 0;
//            return i*2;
                }).sum();
        System.out.println(((System.nanoTime() - time) / 1_000_000_000.0) + " total = " + total);
        System.out.println("---streamFindDouble");
    }

    private void streamPredicate(int number) {
        Predicate<Integer> predicate = i -> i % number == 0;
        String filtered = Stream.of(0, 1, 2, 3, 4, 5).map(i -> i + 1).filter(predicate).map(String::valueOf).collect(Collectors.joining(","));
        System.out.println("filtered = " + filtered);
    }

    private void streamGenerate() {
        Stream.generate(Date::new).limit(10).forEach(System.out::println);
        Stream.generate(System::nanoTime).limit(100).forEach(System.out::println);
    }

    private void streamUser() {
        String ids = IntStream.range(0, 100).mapToObj(i -> new UserEntity(i, String.valueOf(i))).collect(Collectors.toList())
                .stream().map(UserEntity::getId).map(String::valueOf).collect(Collectors.joining(","));
        System.out.println("ids = " + ids);
    }

    private void streamIntFuncs() {
        int[] ints = IntStream.range(0, 100).filter(i -> i % 3 == 0).map(i -> i + 1).toArray();
        IntStream intStream;
        intStream = IntStream.of(ints);
        System.out.println("average: " + intStream.average().getAsDouble());
        intStream = IntStream.of(ints);
        System.out.println("max: " + intStream.max().getAsInt());
        intStream = IntStream.of(ints);
        System.out.println("min: " + intStream.min().getAsInt());
        intStream = IntStream.of(ints);
        System.out.println("sum: " + intStream.sum());
        intStream = IntStream.of(ints);
        System.out.println("count: " + intStream.count());
        intStream = IntStream.of(ints);
        System.out.println("any: " + intStream.findAny().getAsInt());
        intStream = IntStream.of(ints);
        System.out.println("first: " + intStream.findFirst().getAsInt());
        intStream = IntStream.of(ints);
        System.out.println("none match: " + intStream.noneMatch(i -> i % 4 == 0));
        intStream = IntStream.of(ints);
        System.out.println("all match: " + intStream.allMatch(i -> i > 1));
        intStream = IntStream.of(ints);
        System.out.println("skip: " + intStream.skip(10).sorted().mapToObj(String::valueOf).collect(Collectors.joining(",")));
        intStream = IntStream.of(ints);
        System.out.println("limit: " + intStream.limit(10).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        intStream = IntStream.of(ints);
        System.out.println("distinct: " + intStream.distinct().mapToObj(String::valueOf).collect(Collectors.joining(",")));
    }

    private void streamInt() {
        int total = IntStream.range(0, 100).filter(i -> i % 2 == 0).map(i -> i * 10).reduce((i1, i2) -> i1 + i2).orElseGet(() -> 0);
        System.out.println("total int = " + total);
    }

    private void streamOf() {
        int total = Stream.of(1, 2, 3, 4).map(i -> i * 2).filter(i -> i < 8).reduce((i1, i2) -> i1 + i2).orElseGet(() -> 0);
        System.out.println("total of = " + total);
    }

}
