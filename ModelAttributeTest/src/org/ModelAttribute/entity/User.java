package org.ModelAttribute.entity;

public class User {
    private String loginname;
    private String password;
    private String username;

    public User(){}

    public User(String loginname,String password){
        this.setLoginname(loginname);
        this.setPassword(password);
    }
    public User(String loginname,String password,String username){
        this.setLoginname(loginname);
        this.setPassword(password);
        this.setUsername(username);
    }
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
