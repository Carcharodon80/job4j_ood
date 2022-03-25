package ru.job4j.design.srp.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    List<Food> foodList = new ArrayList<>();

    @Override
    public void add(Food food) {
        foodList.add(food);
    }

    @Override
    public List<Food> getFoodList() {
        return null;
    }
}
