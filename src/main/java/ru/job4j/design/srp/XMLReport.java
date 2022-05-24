package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XMLReport implements Report {
    private final Store store;

    public XMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringWriter writer = new StringWriter();
        EmlpoyeeList employees = new EmlpoyeeList(store.findBy(filter));
        try {
            JAXBContext context = JAXBContext.newInstance(EmlpoyeeList.class, Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            marshaller.marshal(employees, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
