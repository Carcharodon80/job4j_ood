package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class JSONReport implements Report{
    private Store store;

    public JSONReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Gson gson = new GsonBuilder().create();
        //List<Employee> employees = store.findBy(filter);
        String report = gson.toJson(store.findBy(filter));
        System.out.println(report);
        return report;
    }
}
