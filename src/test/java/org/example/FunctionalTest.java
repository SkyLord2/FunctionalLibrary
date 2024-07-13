package org.example;

import org.junit.Test;

import java.util.function.Function;

public class FunctionalTest {
    @Test
    public void ifElse() {
        Function fn1 = value -> {
            System.out.println("fn1 print value: " + value);
            return value;
        };

        Function fn2 = value -> {
            System.out.println("fn2 print value: " + value);
            return value;
        };
        Functional.ifElse(true, fn1, fn2).apply("hello world");
    }

    @Test
    public void testIfElse() {
        Function fn1 = value -> {
            System.out.println("cd1 is matched: " + value);
            return value;
        };

        Function fn2 = value -> {
            System.out.println("cd2 is matched: " + value);
            return value;
        };

        Function cd = value -> {
            return ((String)value).startsWith("h");
        };
        Functional.ifElse(cd, fn1, fn2).apply("hello world");
    }

    @Test
    public void pipe() {
        Function fn1 = value -> {
            System.out.println("fn1 print value: " + value);
            value = "fuck world";
            return value;
        };

        Function fn2 = value -> {
            System.out.println("fn2 print value: " + value);
            return value;
        };

        Functional.pipe(fn1, fn2).apply("hello world");
    }

    @Test
    public void cond() {
        Function fn1 = value -> {
            System.out.println("cd1 is matched: " + value);
            return value;
        };

        Function fn2 = value -> {
            System.out.println("cd2 is matched: " + value);
            return value;
        };

        Function cd1 = value -> {
            return ((String)value).startsWith("h");
        };

        Function cd2 = value -> {
            return ((String)value).startsWith("f");
        };

        Function[] pair1 = {cd1, fn1};
        Function[] pair2 = {cd2, fn2};
        Functional.cond(pair1, pair2).apply("fuck world");
    }

    @Test
    public void series() {
        Function fn1 = value -> {
            System.out.println("fn1 print value: " + value);
            return value;
        };

        Function fn2 = value -> {
            System.out.println("fn2 print value: " + value);
            return value;
        };
        Functional.series(fn1, fn2).apply("hello world");
    }

    @Test
    public void then() {
        Function fn1 = value -> {
            System.out.println("fn1 print value: " + value);
            return value;
        };
        Functional.then(
            false,
            fn1
        ).apply("hello world");
    }

    @Test
    public void testThen() {
        Function fn1 = value -> {
            System.out.println("cd1 is matched: " + value);
            return value;
        };

        Function cd1 = value -> {
            return ((String)value).startsWith("f");
        };
        Functional.then(cd1, fn1).apply("hello world");
    }
}
