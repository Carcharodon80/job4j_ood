package ru.job4j.design.isp.menu;

import java.util.List;

public class MenuPrinterForm implements MenuPrinter{
    private int index = 0;

    @Override
    public void print(Menu menu) {
        Menu.MenuItemInfo children = menu.select(null).get();
        printChildren(children, menu);

    }

    private void printChildren(Menu.MenuItemInfo menuItemInfo, Menu menu) {
        index++;
        String dashes = "";
        for (int i = 0; i <= index; i++) {
            dashes += "----";
        }

        System.out.println(dashes + menuItemInfo.getNumber() + menuItemInfo.getName());
        List<String> namesOfChildren = menuItemInfo.getChildren();
        for (String name : namesOfChildren) {
            Menu.MenuItemInfo child = menu.select(name).get();
            printChildren(child, menu);
        }
    }
}
