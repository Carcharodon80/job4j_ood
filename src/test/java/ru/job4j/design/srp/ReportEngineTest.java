package ru.job4j.design.srp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.*;

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

    /**
     * Проверка JSONReport.
     * Создаем список, сериализируем (с помощью JSONReport.generate()), сравниваем.
     */
    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Employee worker1 = new Employee("Ivan",
                new GregorianCalendar(2022, Calendar.MARCH, 2),
                new GregorianCalendar(2022, Calendar.APRIL, 2),
                100);
        Employee worker2 = new Employee("Petr",
                new GregorianCalendar(2020, Calendar.JANUARY, 1),
                new GregorianCalendar(2021, Calendar.DECEMBER, 31),
                355);
        store.add(worker1);
        store.add(worker2);

        Report engine = new JSONReport(store);
        String reportJSON = engine.generate(em -> true);

        StringBuilder expect = new StringBuilder()
                .append("[")
                .append("{\"name\":\"").append(worker1.getName()).append("\",")
                .append("\"hired\":")
                .append("{\"year\":").append(worker1.getHired().get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker1.getHired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker1.getHired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker1.getHired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker1.getHired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker1.getHired().get(Calendar.SECOND)).append("},")
                .append("\"fired\":")
                .append("{\"year\":").append(worker1.getFired().get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker1.getFired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker1.getFired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker1.getFired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker1.getFired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker1.getFired().get(Calendar.SECOND)).append("},")
                .append("\"salary\":").append(worker1.getSalary()).append("},")
                .append("{\"name\":\"").append(worker2.getName()).append("\",")
                .append("\"hired\":")
                .append("{\"year\":").append(worker2.getHired().get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker2.getHired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker2.getHired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker2.getHired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker2.getHired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker2.getHired().get(Calendar.SECOND)).append("},")
                .append("\"fired\":")
                .append("{\"year\":").append(worker2.getFired().get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker2.getFired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker2.getFired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker2.getFired().get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker2.getFired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker2.getFired().get(Calendar.SECOND)).append("},")
                .append("\"salary\":").append(worker2.getSalary()).append("}")
                .append("]");
        assertEquals(expect.toString(), reportJSON);
    }

    /**
     * Проверка XMLReport.
     * Создаем список, маршализируем (с помощью XMLReport.generate()), сравниваем.
     * Для маршализации создали класс EmployeeList (просто список марш-ть не получилось).
     */
    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.UK);
        Calendar worker1Hired = new GregorianCalendar(2022, Calendar.MARCH, 2);
        Calendar worker1Fired = new GregorianCalendar(2022, Calendar.APRIL, 2);
        /*worker1Hired.setTimeZone(TimeZone.getTimeZone(ZoneOffset.of("+1")));
        worker1Fired.setTimeZone(TimeZone.getTimeZone(ZoneOffset.of("+1")));*/
        Employee worker1 = new Employee("Ivan", worker1Hired, worker1Fired, 100);
        store.add(worker1);
        Report engine = new XMLReport(store);
        String reportXML = engine.generate(em -> true);
        StringBuilder expected = new StringBuilder().
                append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("<employeelist>")
                .append("<employee>")
                .append("<fired>").append(sdf.format(worker1Fired.getTime()))
                .append("</fired>")
                .append("<hired>").append(sdf.format(worker1Hired.getTime()))
                .append("</hired>")
                .append("<name>").append(worker1.getName()).append("</name>")
                .append("<salary>").append(worker1.getSalary()).append("</salary>")
                .append("</employee>")
                .append("</employeelist>");
        assertEquals(expected.toString(), reportXML);
    }
}