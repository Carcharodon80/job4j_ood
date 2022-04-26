package ru.job4j.design.isp.menu;

public class MenuPrinterForm implements MenuPrinter {

    /**
     * Метод выводит каждый член меню, добавляя ---- перед каждым подпунктом (зависит от кол-во точек в номере)
     */
    @Override
    public void print(Menu menu) {
        menu.forEach(i -> System.out.println(
                "----".repeat((int) i.getNumber().chars().filter(ch -> ch == '.').count() - 1)
                        + i.getNumber()
                        + i.getName()));
    }
}
