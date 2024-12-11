package org.example;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Functional {
    public static <T, R> Function<T, R> ifElse(boolean real, Function<T, R> fn1, Function<T, R> fn2) {
        return value -> real ? fn1.apply(value) : fn2.apply(value);
    }

    public static <T, R> Function<T, R> ifElse(Predicate<T> condition, Function<T, R> fn1, Function<T, R> fn2) {
        return value -> condition.test(value) ? fn1.apply(value) : fn2.apply(value);
    }

    public static <T, R> Function<T, R> pipe(Function... fns) {
        return value -> Stream.of(fns).reduce((acc, cur) -> s -> cur.apply(acc.apply(s))).map(fn -> ((Function<T, R>) fn).apply(value)).get();
    }

    public static <T, R> Function<T, R> cond(List<CondWrap<T, R>> pairs) {
        return value -> {
            for (CondWrap<T, R> pair : pairs) {
                Predicate<T> condition = pair.getPredicate();
                Function<T, R> operation = pair.getOperation();
                if (condition.test(value)) {
                    return operation.apply(value);
                }
            }
            return null;
        };
    }

    public static <T, R> Function<T, R> series(Function<T, R>... fns) {
        return value -> {
            for (Function<T, R> fn : fns) {
                fn.apply(value);
            }
            return null;
        };
    }

    public static <T, R> Function<T, R> then(boolean real, Function<T, R> operation) {
        return value -> {
            if (real) {
                return operation.apply(value);
            }
            return null;
        };
    }

    public static <T, R> Function<T, R> then(Predicate<T> condition, Function<T, R> operation) {
        return value -> {
            if (condition.test(value)) {
                return operation.apply(value);
            }
            return null;
        };
    }
}
