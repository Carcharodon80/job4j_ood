package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage{
    private final List<Food> foodList = new ArrayList<>();

    @Override
    public boolean check(Food food) {
        return food.getExpiryDate().isBefore(LocalDate.now()) || food.getExpiryDate().isEqual(LocalDate.now());
    }

    public void add(Food food) {
        foodList.add(food);
    }

    public List<Food> getFoodList() {
        return foodList;
    }
}
