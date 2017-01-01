package com.fererlab.java8.main;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class MainOrig {


    public static void main(String args[]) {
        MainOrig mainOrig = new MainOrig();
        mainOrig.predicate();
    }

    private void predicate() {
        Predicate<String> p1 = s -> s.length() < 20;
        Predicate<String> p2 = s -> s.length() > 10;

        Predicate<String> p3 = p1.and(p2);

        Predicate<String> id = Predicate.isEqual(p1);


//        List<String>

    }

    private void collection() {
        List<String> stringList = new ArrayList<>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        stringList.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("--> " + s);
            }
        });

        stringList.forEach((s) -> {
            System.out.println("--> " + s);
        });

        stringList.forEach(System.out::println);


        stringList.forEach(
                ((Consumer<String>) s -> System.out.println("1s = " + s))
                        .andThen(s -> System.out.println("2s = " + s))
                        .andThen(s -> System.out.println("3s = " + s))
                        .andThen(s -> System.out.println("4s = " + s))
                        .andThen(s -> System.out.println("5s = " + s))
        );

    }

    private void functions() {
        Function<Integer, String> f = new Function<Integer, String>() {
            @Override
            public String apply(Integer i) {
                return "" + i;
            }
        };
        System.out.println(f.apply(1));

        Function<Integer, String> fl = i -> "" + i;
        System.out.println(fl.apply(2));


        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };
        consumer.accept(0);

        Consumer<Integer> consumerLambda = i -> System.out.println(i);
        consumerLambda.accept(10);

        Consumer<Integer> consumerLambdaMethodReference = System.out::println;
        consumerLambdaMethodReference.accept(20);

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1.equals(i2) ? 0 : i1 > i2 ? 1 : -1;
            }
        };
        System.out.println(comparator.compare(3, 4));

        Comparator<Integer> comparatorLambda = (i1, i2) -> i1.equals(i2) ? 0 : i1 > i2 ? 1 : -1;
        System.out.println(comparatorLambda.compare(4, 4));

        Comparator<Integer> comparatorLambdaMethodReference = Integer::compare;
        System.out.println(comparatorLambdaMethodReference.compare(5, 4));


    }

    public void test() {
        FileFilter fileFilterAnonymous = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().endsWith(".java");
            }
        };
        System.out.println("anonymous file filter: " + fileFilterAnonymous);
        FileFilter fileFilterLambda = (File file) -> file.getName().endsWith(".java");
        System.out.println("lambda file filter: " + fileFilterLambda);
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.forEach(System.out::println);
    }
} 
