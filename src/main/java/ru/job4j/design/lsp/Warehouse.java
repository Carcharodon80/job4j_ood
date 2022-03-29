package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage{
    private final List<Food> foodList = new ArrayList<>();

    @Override
    public boolean check(Food food) {
        return getPercentageTime(food) < 25;
    }

    public void add(Food food) {
        foodList.add(food);
    }

    public List<Food> getFoodList() {
        return foodList;
    }
}
