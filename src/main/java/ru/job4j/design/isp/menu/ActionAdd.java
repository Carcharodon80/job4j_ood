package ru.job4j.design.isp.menu;

public class ActionAdd implements ActionDelegate {
    @Override
    public void delegate() {
        System.out.println("Новая задача.");
    }
}
