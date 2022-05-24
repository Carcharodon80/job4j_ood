package ru.job4j.design.lsp.parking;

public class Truck implements Car {
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
}
