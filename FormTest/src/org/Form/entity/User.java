package org.Form.entity;

public class User {
    private String username;
    private String sex;
    private int age;

    public User(){}

    public User(String username,String sex,int age){
        this.setUsername(username);
        this.setSex(sex);
        this.setAge(age);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
