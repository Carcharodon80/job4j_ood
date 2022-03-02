package ru.job4j.kiss;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * 1. Принципы Kiss, Dry и Yagni [#238813].
 */
public class MaxMinTest {

    @org.junit.Test
    public void maxTest() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = Arrays.asList(1, 5, 90, 23, 3, 5, 89, -7);
        int max = maxMin.max(list, Integer::compareTo);
        assertThat(max, is(90));
    }

    @org.junit.Test
    public void minTest() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = Arrays.asList(1, 5, 90, 23, 3, 5, 89, -7);
        int min = maxMin.min(list, Integer::compareTo);
        assertThat(min, is(-7));
    }
}