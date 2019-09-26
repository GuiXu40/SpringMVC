package org.Mybatis.entity;

public class User {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;

    public User(){}

    public User(String name,String sex,Integer age){
        this.setName(name);
        this.setSex(sex);
        this.setAge(age);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "id:"+id+"name:"+name+"sex:"+sex+"age:"+age;
    }
}
