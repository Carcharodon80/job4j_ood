package ru.job4j.design.srp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employeelist")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EmlpoyeeList {

    private List<Employee> list;

    public EmlpoyeeList(List<Employee> list) {
        this.list = list;
    }

    public EmlpoyeeList() {
    }

    @XmlElement(name="employee")
    public List<Employee> getList() {
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }
}
