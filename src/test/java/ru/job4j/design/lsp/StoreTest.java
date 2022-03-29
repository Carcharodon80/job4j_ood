package ru.job4j.design.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

public class StoreTest {

    /**
     * продукт сделан 1 день назад, срок годности закончится через 10 дней, отправляется в Warehouse
     */
    @Test
    public void whenFoodInWarehouse() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        Food bread = new Bread("Borodinsky",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(1),
                30,
                50
                );
        controlQuality.check(bread);
        assertTrue(warehouse.getFoodList().contains(bread));
        assertFalse(shop.getFoodList().contains(bread));
        assertFalse(trash.getFoodList().contains(bread));
    }

    /**
     * продукт сделан 3 дня назад, срок годности закончится через 3 дня, отправляется в Shop
     */
    @Test
    public void whenFoodInShop() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        Food bread = new Bread("Borodinsky",
                LocalDate.now().plusDays(3),
                LocalDate.now().minusDays(3),
                30,
                50
        );
        controlQuality.check(bread);
        assertTrue(shop.getFoodList().contains(bread));
        assertFalse(warehouse.getFoodList().contains(bread));
        assertFalse(trash.getFoodList().contains(bread));
    }

    /**
     * продукт сделан 10 дней назад, срок годности закончится через 2 дня, отправляется в Shop со скидкой
     */
    @Test
    public void whenFoodInShopWithDiscount() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        Food bread = new Bread("Borodinsky",
                LocalDate.now().plusDays(2),
                LocalDate.now().minusDays(10),
                30,
                50
        );
        controlQuality.check(bread);
        assertTrue(shop.getFoodList().contains(bread));
        assertEquals(15, shop.getFoodList().get(0).getPrice(), 0.0);
        assertFalse(warehouse.getFoodList().contains(bread));
        assertFalse(trash.getFoodList().contains(bread));
    }

    /**
     * продукт сделан 3 дня назад, срок годности закончился сегодня, отправляется в Trash
     */
    @Test
    public void whenFoodInTrash() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        Food bread = new Bread("Borodinsky",
                LocalDate.now(),
                LocalDate.now().minusDays(3),
                30,
                50
        );
        controlQuality.check(bread);
        assertTrue(trash.getFoodList().contains(bread));
        assertFalse(warehouse.getFoodList().contains(bread));
        assertFalse(shop.getFoodList().contains(bread));
    }
}