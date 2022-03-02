package ru.job4j.condition;

public class SqMax {
    public static int max(int left, int right) {
        return left > right ? left : right;
    }
    public static int max(int first, int second, int third, int forth) {
        return max(
                max(first, second),
                max(third, forth)
        );
    }
}
