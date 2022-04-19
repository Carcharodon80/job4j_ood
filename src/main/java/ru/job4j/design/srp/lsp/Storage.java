package ru.job4j.design.srp.lsp;

import java.util.List;

public interface Storage {

    public void add(Food food);

    public List<Food> getFoodList();
}
