package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Storage {

    boolean check(Food food);

    void add(Food food);

    List<Food> getFoodList();

    /**
     * метод проверяет, сколько процентов срока годности прошло
     * @return в процентах, сколько прошло срока годности (весь срок - 4 дня, прошло 3 дня, возвращает 75)
     */
    default double getPercentageTime(Food food) {
        long allTime = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long lastedTime = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        return (double) lastedTime / allTime * 100;
    }
}
