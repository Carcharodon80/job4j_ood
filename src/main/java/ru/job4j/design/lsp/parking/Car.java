package ru.job4j.design.lsp.parking;

public interface Car {
    boolean isTruck();
    int getSize();
    void setId(int id);
    int getId();
}
