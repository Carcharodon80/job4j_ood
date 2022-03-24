package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
     * Создаем список, сериализируем (с помощью JSONReport.generate()) и десериализируем, сравниваем.
     * ? - если в Employee.equals() добавить все поля - тест не проходит (из-за Calendar)
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
        List<Employee> oldList = store.findBy(em -> true);

        Report engine = new JSONReport(store);
        String reportJSON = engine.generate(em -> true);

        Gson gson = new GsonBuilder().create();
        Type typeList = new TypeToken<List<Employee>>() {}.getType();
        List<Employee> newList = gson.fromJson(reportJSON, typeList);

        assertEquals(oldList, newList);
    }

    /**
     * Проверка XMLReport.
     * Создаем список, маршализируем (с помощью XMLReport.generate()) и демаршализируем, сравниваем.
     * Для маршализации создали класс EmployeeList (просто список марш-ть не получилось).
     * ? - Пришлось в Employee добавить default Constructor.
     * ? - если в Employee.equals() добавить все поля - тест не проходит (из-за Calendar)
     */
    @Test
    public void whenXMLGenerated() {
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
        List<Employee> oldList = store.findBy(em -> true);

        Report engine = new XMLReport(store);
        String reportXML = engine.generate(em -> true);

        StringReader reader = new StringReader(reportXML);
        List<Employee> newList = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(EmlpoyeeList.class, Employee.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            EmlpoyeeList employees = (EmlpoyeeList) unmarshaller.unmarshal(reader);
            newList = employees.getList();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        assertEquals(oldList, newList);
    }
}