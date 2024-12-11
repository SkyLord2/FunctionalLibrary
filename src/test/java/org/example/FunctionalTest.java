package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalTest {
    @Test
    public void ifElse() {
        Function<String, String> fn1 = value -> {
            System.out.println("fn1 print value: " + value);
            return value;
        };

        Function<String, String> fn2 = value -> {
            System.out.println("fn2 print value: " + value);
            return value;
        };
        Functional.ifElse(true, fn1, fn2).apply("hello world");
    }

    @Test
    public void testIfElse() {
        Function<String, String> fn1 = value -> {
            System.out.println("cd1 is matched: " + value);
            return value;
        };

        Function<String, String> fn2 = value -> {
            System.out.println("cd2 is matched: " + value);
            return value;
        };

        Predicate<String> cd = value -> value.startsWith("h");
        Functional.ifElse(cd, fn1, fn2).apply("hello world");
    }

    @Test
    public void pipe() {
        Function<String, String> fn1 = value -> {
            System.out.println("fn1 print value: " + value);
            value = "fuck world";
            return value;
        };

        Function<String, String> fn2 = value -> {
            System.out.println("fn2 print value: " + value);
            return value;
        };

        Functional.<String, String>pipe(fn1, fn2).apply("hello world");
    }

    @Test
    public void cond() {
        Function<String, String> fn1 = value -> {
            System.out.println("cd1 is matched: " + value);
            return value;
        };

        Function<String, String> fn2 = value -> {
            System.out.println("cd2 is matched: " + value);
            return value;
        };

        Predicate<String> cd1 = value -> value.startsWith("h");

        Predicate<String> cd2 = value -> value.startsWith("f");

        List<CondWrap<String, String>> pairs = new ArrayList<>();
        pairs.add(CondWrap.<String, String>builder().predicate(cd1).operation(fn1).build());
        pairs.add(CondWrap.<String, String>builder().predicate(cd2).operation(fn2).build());

        Functional.cond(pairs).apply("fuck world");
    }

    @Test
    public void series() {
        Function<String, String> fn1 = value -> {
            System.out.println("fn1 print value: " + value);
            return value;
        };

        Function<String, String> fn2 = value -> {
            System.out.println("fn2 print value: " + value);
            return value;
        };
        Functional.series(fn1, fn2).apply("hello world");
    }

    @Test
    public void then() {
        Function<String, String> fn1 = value -> {
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
        Function<String, String> fn1 = value -> {
            System.out.println("cd1 is matched: " + value);
            return value;
        };

        Predicate<String> cd1 = value -> value.startsWith("f");
        Functional.then(cd1, fn1).apply("hello world");
    }
}
