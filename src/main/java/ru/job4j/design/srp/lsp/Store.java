package ru.job4j.design.srp.lsp;

import java.time.LocalDate;

public class Store {
    public static void main(String[] args) {
        Food bread = new Bread("Bread1", LocalDate.of(2022, 12, 3),
                LocalDate.now(), 4.95, 25);
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        Storage trash = new Trash();
        ControlQuality controlQuality = new ControlQuality();
        Storage storage = controlQuality.check(bread);
        storage.add(bread);
        System.out.println(shop);
    }
}
