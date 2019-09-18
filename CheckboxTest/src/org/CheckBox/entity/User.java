package org.CheckBox.entity;

import java.util.List;

public class User {
    private boolean reader;
    private List<String> coursees;
    public boolean isReader(){
        return reader;
    }
    public User(){}

    public User(List<String> coursees){
        this.setCoursees(coursees);
    }
    public void setReader(boolean reader) {
        this.reader = reader;
    }

    public List<String> getCoursees() {
        return coursees;
    }

    public void setCoursees(List<String> coursees) {
        this.coursees = coursees;
    }
}
