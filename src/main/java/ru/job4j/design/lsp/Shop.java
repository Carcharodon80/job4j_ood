package ru.job4j.design.lsp;


import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private final List<Food> foodList = new ArrayList<>();

    @Override
    public boolean check(Food food) {
        double percentageTime = getPercentageTime(food);
        return percentageTime >= 25 && percentageTime < 100;
    }

    @Override
    public void add(Food food) {
        double percentageTime = getPercentageTime(food);
        if (percentageTime >= 25 && percentageTime < 75) {
            foodList.add(food);
        } else if (percentageTime >= 75 && percentageTime < 100) {
            food.setPrice(food.getPrice() * (100 - food.getDicsount()) / 100);
            foodList.add(food);
        }
    }

    public List<Food> getFoodList() {
        return foodList;
    }
}
