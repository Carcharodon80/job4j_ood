package ru.job4j.design.lsp;

import java.time.LocalDate;

public class Meat extends Food{
    public Meat(String name, LocalDate expiryDate, LocalDate createDate, double price, int dicsount) {
        super(name, expiryDate, createDate, price, dicsount);
    }
}
