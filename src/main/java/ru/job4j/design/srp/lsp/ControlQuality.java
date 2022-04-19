package ru.job4j.design.srp.lsp;

public class ControlQuality {
    public Storage check(Food food) {
        Storage storage = null;
        if (food.getName().equals("Bread1")) {
            storage = new Shop();
        }
        return storage;
    }
}
