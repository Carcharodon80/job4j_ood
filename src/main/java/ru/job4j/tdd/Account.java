package ru.job4j.tdd;

import java.util.ArrayList;
import java.util.List;

public interface Account {
    List<Ticket> TICKETS = new ArrayList<>();

    void someMethod();
}
