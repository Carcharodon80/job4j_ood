package ru.job4j.design.srp;

import java.util.function.Predicate;

public class HTMLReport implements Report {
    private Store store;

    public HTMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<body>")
                .append("<table>")
                .append("<tr>").append("<th>")
                .append("Name; Hired; Fired; Salary")
                .append("</tr>").append("</th>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>").append("<th>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary())
                    .append("</tr>").append("</th>");
        }
        text.append("</table>")
                .append("</body>")
                .append("</html>");
        return text.toString();
    }
}
