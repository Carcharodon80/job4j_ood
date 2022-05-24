package ru.job4j.design.srp.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {
    List<Food> foodList = new ArrayList<>();

    @Override
    public void add(Food food) {
        foodList.add(food);
    }

    @Override
    public List<Food> getFoodList() {
        return foodList;
    }
}
