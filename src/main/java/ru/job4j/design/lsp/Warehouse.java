package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private final List<Food> foodList = new ArrayList<>();

    @Override
    public boolean check(Food food) {
        return getPercentageTime(food) < 25;
    }

    public boolean add(Food food) {
        boolean rsl = false;
        if (check(food)) {
            foodList.add(food);
            rsl = true;
        }
        return rsl;
    }

    /**
     * возвращает копию, чтобы нельзя было изменить напрямую
     */
    public List<Food> getFoodList() {
        return List.copyOf(foodList);    }

    public void removeAllFoods() {
        foodList.clear();
    }
}
