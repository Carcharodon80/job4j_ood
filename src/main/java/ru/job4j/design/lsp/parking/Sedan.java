package ru.job4j.design.lsp.parking;

public class Sedan implements Car{
    private static final int SIZE = 1;
    private int id;

    public int getSize() {
        return SIZE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
