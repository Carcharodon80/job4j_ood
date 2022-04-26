package ru.job4j.design.isp.menu;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    Menu menu;

    @Before
    public void setUp() {
        menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
    }

    @Test
    public void whenAddThenReturnSame() {
        assertEquals(
                new Menu.MenuItemInfo(
                        "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."
                ),
                menu.select("Сходить в магазин").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."
                ),
                menu.select("Купить продукты").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2."
                ),
                menu.select("Покормить собаку").get()
        );
    }

    @Test
    public void whenMenuPrint() {
        PrintStream systemOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        MenuPrinter menuPrinter = new MenuPrinterForm();
        menuPrinter.print(menu);
        String expected = "1.Сходить в магазин" + System.lineSeparator()
                + "----1.1.Купить продукты" + System.lineSeparator()
                + "--------1.1.1.Купить хлеб" + System.lineSeparator()
                + "--------1.1.2.Купить молоко" + System.lineSeparator()
                + "2.Покормить собаку" + System.lineSeparator();
        assertEquals(expected, outputStream.toString());
        System.setOut(systemOut);
    }
}