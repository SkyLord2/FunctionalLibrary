package org.example;

import java.util.function.Function;
import java.util.stream.Stream;

public class Functional {
    public static Function ifElse(boolean real, Function fn1, Function fn2) {
        return value -> real ? fn1.apply(value) : fn2.apply(value);
    }

    public static Function ifElse(Function condition, Function fn1, Function fn2) {
        return value -> (Boolean) condition.apply(value) ? fn1.apply(value) : fn2.apply(value);
    }

    public static Function pipe(Function... fns) {
        return value -> {
            return Stream.of(fns).reduce((acc, cur) -> s -> cur.apply(acc.apply(s))).map(fn -> fn.apply(value)).get();
        };
    }

    public static Function cond(Function[]... pairs) {
        return value -> {
            for (Function[] pair : pairs) {
                Function condition = pair[0];
                Function operation = pair[1];
                if ((Boolean)condition.apply(value)) {
                    return operation.apply(value);
                }
            }
            return null;
        };
    }

    public static Function series(Function... fns) {
        return value -> {
            for (Function fn : fns) {
                fn.apply(value);
            }
            return null;
        };
    }

    public static Function then(boolean real, Function operation) {
        return value -> {
            if (real) {
                return operation.apply(value);
            }
            return null;
        };
    }

    public static Function then(Function condition, Function operation) {
        return value -> {
            if ((Boolean) condition.apply(value)) {
                return operation.apply(value);
            }
            return null;
        };
    }
}
