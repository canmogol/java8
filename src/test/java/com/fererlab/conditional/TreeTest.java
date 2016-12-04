package com.fererlab.conditional;

import org.junit.Test;

public class TreeTest {

    @Test
    public void condTreeTest() {
        Cond.tree()
                .error((e) -> System.out.println("got exception: " + e))
                .check(() -> "123".startsWith("a"),
                        () -> System.out.println("first check, should pass"))
                .check(() -> "-*/".startsWith("a"),
                        () -> System.out.println("second check, should pass"))
                .check(() -> "abc".startsWith("a"),
                        () -> System.out.println("starts with a!"))
                .otherwise(() -> System.out.println("something else"))
                .eval();
    }

    @Test
    public void condTreeAllTrueTest() {
        Cond.tree()
                .error((e) -> System.out.println("got exception: " + e))
                .check(() -> "123".startsWith("123"),
                        () -> System.out.println("found 123, THIS SHOULD PRINT"))
                .check(() -> "-*/".startsWith("-*/"),
                        () -> System.out.println("found -*/, this should not print"))
                .check(() -> "abc".startsWith("abc"),
                        () -> System.out.println("found abc, this should not print"))
                .otherwise(() -> System.out.println("something else, this should not print"))
                .eval();
    }

    @Test
    public void condHashTest() {
        int ninetyNine = 99;
        Cond.hash()
                .check("AAA",
                        () -> System.out.println("string with three A chars"))
                .check(Boolean.FALSE,
                        () -> System.out.println("it is a False"))
                .check(99,
                        () -> System.out.println("that is 99"))
                .otherwise(() -> System.out.println("something else"))
                .error((e) -> System.out.println("got exception: " + e))
                .eval(ninetyNine);
    }

    @Test
    public void condTreeNullTest() {
        String nullString = null;
        Cond.tree()
                .check(() -> nullString.startsWith("NULL String"),
                        () -> System.out.println("this should throw a null pointer"))
                .check(() -> "a".startsWith("a"),
                        () -> System.out.println("should not execute"))
                .check(() -> "b".startsWith("b"),
                        () -> System.out.println("should not execute either"))
                .otherwise(() -> System.out.println("will not get here"))
                .error((e) -> System.out.println("got NULL Pointer exception: " + e.getMessage()))
                .eval();

        // positions of otherwise, error and check methods is not important
        Cond.tree()
                .otherwise(() -> System.out.println("will not get here"))
                .error((e) -> System.out.println("got NULL Pointer exception: " + e.getMessage()))
                .check(() -> nullString.startsWith("NULL String"),
                        () -> System.out.println("this should throw a null pointer"))
                .check(() -> "a".startsWith("a"),
                        () -> System.out.println("should not execute"))
                .check(() -> "b".startsWith("b"),
                        () -> System.out.println("should not execute either"))
                .eval();
    }

    @Test
    public void condHashMultiEvalTest() {
        String tripleA = "AAA";
        boolean aFalse = false;
        int ninetyNine = 99;
        Cond.Hash cond = Cond.hash()
                .check("AAA",
                        () -> System.out.println("string with three A chars"))
                .check(Boolean.FALSE,
                        () -> System.out.println("it is a False"))
                .check(99,
                        () -> System.out.println("that is 99"))
                .otherwise(() -> System.out.println("something else"))
                .error((e) -> System.out.println("got exception: " + e));
        cond.eval(tripleA);
        cond.eval(aFalse);
        cond.eval(ninetyNine);
    }

}
