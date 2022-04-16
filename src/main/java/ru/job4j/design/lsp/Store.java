package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Store {
    public static void main(String[] args) {
        Food bread = new Bread("Bread",
                LocalDate.of(2022, 3, 30),
                LocalDate.of(2022, 3, 26),
                4.95,
                27);

        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        Storage trash = new Trash();
        List<Storage> storages = Arrays.asList(shop, warehouse, trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.distribute(bread);
        System.out.println();
    }
}
