package org.example;

import lombok.Builder;
import lombok.Data;

import java.util.function.Function;
import java.util.function.Predicate;

@Data
@Builder
public class CondWrap<T, R> {
    private final Predicate<T> predicate;
    private final Function<T, R> operation;
}
