package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 300);
        store.add(worker1);
        store.add(worker2);
        Report engine = new HTMLReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html>")
                .append("<body>")
                .append("<table>")
                .append("<tr>").append("<th>")
                .append("Name; Hired; Fired; Salary")
                .append("</tr>").append("</th>")
                .append("<tr>").append("<th>")
                .append(worker1.getName()).append(";")
                .append(worker1.getHired()).append(";")
                .append(worker1.getFired()).append(";")
                .append(worker1.getSalary())
                .append("</tr>").append("</th>")
                .append("<tr>").append("<th>")
                .append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(worker2.getSalary())
                .append("</tr>").append("</th>")
                .append("</table>")
                .append("</body>")
                .append("</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenDollarsGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 115);
        Employee worker2 = new Employee("Petr", now, now, 355);
        store.add(worker1);
        store.add(worker2);
        Report engine = new DollarsReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getHired()).append(";")
                .append(worker1.getFired()).append(";")
                .append(worker1.getSalary() / DollarsReport.dollar).append("$")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(worker2.getSalary() / DollarsReport.dollar).append("$")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 115);
        Employee worker2 = new Employee("Petr", now, now, 355);
        Employee worker3 = new Employee("Alex", now, now, 60);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new HRReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary())
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}