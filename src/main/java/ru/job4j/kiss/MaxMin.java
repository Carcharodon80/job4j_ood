package ru.job4j.kiss;

import java.util.*;

/**
 * 1. Принципы Kiss, Dry и Yagni [#238813]
 */
public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findMax(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findMax(value, comparator.reversed());
    }

    private <T> T findMax(List<T> value, Comparator<T> comparator) {
        T max = value.get(0);
        for (T t : value) {
            max = (comparator.compare(t, max) > 0) ? t : max;
        }
        return max;
    }
}
