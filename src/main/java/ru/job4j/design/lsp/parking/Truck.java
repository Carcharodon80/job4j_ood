package ru.job4j.design.lsp.parking;

public class Truck implements Car{
    private int id;
    private final int size;

    public Truck(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Неверный размер машины.");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isTruck() {
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
