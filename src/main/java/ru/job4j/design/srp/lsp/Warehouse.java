package ru.job4j.design.srp.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage{
    List<Food> foodList = new ArrayList<>();

    @Override
    public void add(Food food) {

    }

    @Override
    public List<Food> getFoodList() {
        return null;
    }
}
