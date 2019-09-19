package org.CheckBox.entity;

import java.util.List;

public class Employee {
    private List<Dept> depts;

    public List<Dept> getDepts() {
        return depts;
    }

    public void setDepts(List<Dept> depts) {
        this.depts = depts;
    }
}
