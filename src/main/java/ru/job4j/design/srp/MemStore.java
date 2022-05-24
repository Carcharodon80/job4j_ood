package ru.job4j.design.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStore implements Store {
    private final List<Employee> employees = new ArrayList<>();

    public void add(Employee em) {
        employees.add(em);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemStore memStore = (MemStore) o;
        return Objects.equals(employees, memStore.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }


}
