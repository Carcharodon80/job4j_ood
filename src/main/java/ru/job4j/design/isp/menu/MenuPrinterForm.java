package ru.job4j.design.isp.menu;

public class MenuPrinterForm implements MenuPrinter {
    private static final String DASHES = "----";

    /**
     * Метод выводит каждый член меню, добавляя ---- перед каждым подпунктом (зависит от кол-во точек в номере)
     */
    @Override
    public void print(Menu menu) {
        menu.forEach(i -> System.out.println(
                DASHES.repeat((int) i.getNumber().chars().filter(ch -> ch == '.').count() - 1)
                        + i.getNumber()
                        + i.getName()));
    }
}
