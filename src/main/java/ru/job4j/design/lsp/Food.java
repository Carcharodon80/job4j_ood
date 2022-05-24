package ru.job4j.design.lsp;

import java.time.LocalDate;

public abstract class Food {
    private String name;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private double price;
    private int dicsount;

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, int dicsount) {
        if (expiryDate.isBefore(createDate) || (createDate.isAfter(LocalDate.now()))) {
            throw new IllegalArgumentException();
        }
        name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.dicsount = dicsount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDicsount() {
        return dicsount;
    }

    public void setDicsount(int dicsount) {
        this.dicsount = dicsount;
    }
}
