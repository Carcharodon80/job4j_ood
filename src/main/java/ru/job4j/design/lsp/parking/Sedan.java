package ru.job4j.design.lsp.parking;

public class Sedan implements Car{
    private static final int SIZE = 1;

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public boolean isTruck() {
        return false;
    }
}
