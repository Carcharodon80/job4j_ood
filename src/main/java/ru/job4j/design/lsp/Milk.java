package ru.job4j.design.lsp;

import java.time.LocalDate;

public class Milk extends Food{
    public Milk(String name, LocalDate expiryDate, LocalDate createDate, double price, int dicsount) {
        super(name, expiryDate, createDate, price, dicsount);
    }
}
