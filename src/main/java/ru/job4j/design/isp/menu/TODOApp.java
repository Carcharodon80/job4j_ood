package ru.job4j.design.isp.menu;

import java.util.Scanner;

public class TODOApp {

    public static void main(String[] args) {
        TODOApp app = new TODOApp();
        app.createMenu();
    }

    /**
     * Метод для добавления задач.
     * Если название новой задачи есть в списке - в нее добавляется дочерняя задача,
     * иначе - задача добавляется в корень списка.
     * Имена задач должны быть уникальными (иначе не добавится)
     */
    private void createMenu() {
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new MenuPrinterForm();
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Для добавления новой задачи введите название новой задачи,");
            System.out.println("для добавления дочерней задачи - введите название родительской задачи,");
            System.out.println("для выхода введите \"exit\"");
            String newTask = in.next();
            if ("exit".equalsIgnoreCase(newTask)) {
                break;
            } else if (menu.select(newTask).isPresent()) {
                System.out.println("Введите название дочерней задачи:");
                String childNewTask = in.next();
                menu.add(newTask, childNewTask, new ActionAdd());
            } else {
                menu.add(Menu.ROOT, newTask, new ActionAdd());
            }
            menuPrinter.print(menu);
        }
    }
}


